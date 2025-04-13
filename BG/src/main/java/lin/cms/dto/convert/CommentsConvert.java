package lin.cms.dto.convert;

import lin.cms.model.CommentsDO;
import lin.cms.vo.UserCommentsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Xu
 * @time 2025/4/13
 */
@Mapper
public interface CommentsConvert {
    CommentsConvert INSTANCE = Mappers.getMapper(CommentsConvert.class);

    CommentsDO toDO(UserCommentsVO comments);
}
