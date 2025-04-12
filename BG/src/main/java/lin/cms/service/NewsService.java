package lin.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.model.UserDO;
import lin.cms.dto.news.CreateOrUpdateNewsDTO;
import lin.cms.dto.news.NewsDTO;
import lin.cms.model.NewsDO;

import java.util.List;

/**
 * created by Xu on 2024/3/18 9:27.
 */
public interface NewsService {
    NewsDO getById(Integer id);

    boolean createNews(CreateOrUpdateNewsDTO bookDO);

    IPage<NewsDTO> getNewsByKeyword(Integer count, Integer page, String keyword, String countryName);

    boolean updateNews(NewsDO book, CreateOrUpdateNewsDTO validator);

    boolean deleteById(Integer id);
}
