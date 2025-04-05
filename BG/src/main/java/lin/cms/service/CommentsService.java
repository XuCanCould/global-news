package lin.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import lin.cms.model.CommentsDO;
import lin.cms.vo.UserCommentsVO;

import java.util.List;

/**
 * @author Xu
 * @time 2025/4/4
 */
public interface CommentsService extends IService<CommentsDO> {
    List<UserCommentsVO> getMyComments();

    void saveComments(UserCommentsVO comments);
}
