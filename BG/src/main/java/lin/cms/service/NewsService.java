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

    IPage<NewsDTO> getNewsByKeyword(Integer count, Integer page, String keyword, String countryName, Integer status);

    boolean updateNews(NewsDO book, CreateOrUpdateNewsDTO validator);

    boolean deleteById(Integer id);

    void updateComments(Integer newsId, Integer comments);

    /**
     * 更新点赞数
     * @param newsId
     * @param likes 点赞 1，取消点赞 -1
     */
    void updateLikes(Integer newsId, Integer likes);
}
