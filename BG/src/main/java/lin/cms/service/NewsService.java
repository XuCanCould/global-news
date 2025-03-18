package lin.cms.service;

import lin.cms.dto.news.CreateOrUpdateNewsDTO;
import lin.cms.model.NewsDO;

import java.util.List;

/**
 * created by Xu on 2024/3/18 9:27.
 */
public interface NewsService {
    NewsDO getById(Integer id);

    List<NewsDO> findAll();

    boolean createNews(CreateOrUpdateNewsDTO bookDO);

    List<NewsDO> getNewsByKeyword(String q);

    boolean updateNews(NewsDO book, CreateOrUpdateNewsDTO validator);

    boolean deleteById(Integer id);
}
