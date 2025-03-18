package lin.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lin.cms.model.NewsDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by Xu on 2024/3/18 9:08.
 */
@Repository
public interface NewsMapper extends BaseMapper<NewsDO> {
    List<NewsDO> selectByTitleLikeKeyword(String q);
}
