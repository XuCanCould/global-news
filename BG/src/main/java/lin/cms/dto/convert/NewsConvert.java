package lin.cms.dto.convert;

import lin.cms.dto.news.CreateOrUpdateNewsDTO;
import lin.cms.model.NewsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Xu
 * @time 2025/3/23
 */
@Mapper
public interface NewsConvert {
    NewsConvert INSTANCE = Mappers.getMapper(NewsConvert.class);

    NewsDO toDO(CreateOrUpdateNewsDTO validator);
}
