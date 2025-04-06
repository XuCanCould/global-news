package lin.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.model.UserDO;
import lin.cms.mapper.CommentsMapper;
import lin.cms.model.CommentsDO;
import lin.cms.service.CommentsService;
import lin.cms.vo.UserCommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Xu
 * @time 2025/4/4
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, CommentsDO> implements CommentsService {
    @Autowired
    public List<UserCommentsVO> getMyComments() {
        UserDO localUser = LocalUser.getLocalUser();
        if (Objects.isNull(localUser)) {
            return null;
        }
        return this.getBaseMapper().getMyComments(localUser.getId());
    }

    @Override
    public void saveComments(UserCommentsVO comments) {
        UserDO localUser = LocalUser.getLocalUser();

    }
}
