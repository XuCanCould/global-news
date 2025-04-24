package lin.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lin.cms.common.LocalUser;
import lin.cms.dto.convert.CommentsConvert;
import lin.cms.mapper.CommentsMapper;
import lin.cms.model.CommentsDO;
import lin.cms.model.UserDO;
import lin.cms.service.CommentsService;
import lin.cms.service.NewsService;
import lin.cms.vo.UserCommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author Xu
 * @time 2025/4/4
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, CommentsDO> implements CommentsService {

    @Autowired
    private NewsService newsService;

    @Autowired
    public List<UserCommentsVO> getMyComments() {
        UserDO localUser = LocalUser.getLocalUser();
        if (Objects.isNull(localUser)) {
            return null;
        }
        return this.getBaseMapper().getMyComments(localUser.getId());
    }

    @Override
    @Transactional
    public void saveComments(UserCommentsVO comments) {
        UserDO localUser = LocalUser.getLocalUser();
        CommentsDO commentsDO = CommentsConvert.INSTANCE.toDO(comments);
        commentsDO.setUserId(localUser.getId());
        commentsDO.setNickname(localUser.getNickname());
        newsService.updateComments(comments.getNewsId(), 1);
        this.save(commentsDO);
    }

    @Override
    @Transactional
    public void removeComment(Integer id) {
        CommentsDO commentsDO = this.getById(id);
        newsService.updateComments(commentsDO.getNewsId(), -1);
        this.removeById(id);
    }
}
