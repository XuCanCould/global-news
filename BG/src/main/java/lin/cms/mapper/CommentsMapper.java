package lin.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lin.cms.model.CommentsDO;
import lin.cms.vo.UserCommentsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xu
 * @time 2025/4/4
 */
@Mapper
public interface CommentsMapper extends BaseMapper<CommentsDO> {
    List<UserCommentsVO> getMyComments(Integer userId);
}
