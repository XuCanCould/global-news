package lin.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lin.cms.common.LocalUser;
import lin.cms.model.UserDO;
import lin.cms.model.NewsDO;
import lin.cms.dto.convert.NewsConvert;
import lin.cms.dto.news.CreateOrUpdateNewsDTO;
import lin.cms.dto.news.NewsDTO;
import lin.cms.mapper.NewsMapper;
import lin.cms.service.NewsService;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by Xu on 2024/3/18 9:27.
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, NewsDO> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public NewsDO getById(Integer id) {
        NewsDO newsDO = newsMapper.selectById(id);
        if (newsDO != null) {
            newsDO.setViews(newsDO.getViews() + 1);
            newsMapper.updateById(newsDO);
        } else {
            throw new NotFoundException(20100);
        }
        return newsDO;
    }

    @Override
    public boolean createNews(CreateOrUpdateNewsDTO validator) {
        NewsDO book = NewsConvert.INSTANCE.toDO(validator);
        UserDO localUser = LocalUser.getLocalUser();
        book.setCreator(localUser.getUsername());
        book.setUpdater(localUser.getUsername());
        return this.newsMapper.insert(book) > 0;
    }

    @Override
    public IPage<NewsDTO> getNewsByKeyword(Integer count, Integer page, String keyword, String countryName) {
        Page<NewsDTO> pager = new Page<>(page, count);
        return this.newsMapper.getNewsPage(pager, keyword, countryName);
    }

    @Override
    public boolean updateNews(NewsDO book, CreateOrUpdateNewsDTO validator) {
        book.setTitle(validator.getTitle());
        book.setSource(validator.getSource());
        book.setCategory(validator.getCategory());
        book.setContent(validator.getContent());
        book.setCountry(validator.getCountry());
        UserDO localUser = LocalUser.getLocalUser();
        book.setUpdater(localUser.getUsername());
        return this.newsMapper.updateById(book) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.newsMapper.deleteById(id) > 0;
    }

    @Override
    public void updateComments(Integer newsId, Integer comments) {
        NewsDO newsDO = getById(newsId);
        newsDO.setComments(newsDO.getComments() + 1);
        this.newsMapper.updateById(newsDO);
    }

    @Override
    public void updateLikes(Integer newsId, Integer likes) {
        NewsDO newsDO = getById(newsId);
        newsDO.setLikeCount(newsDO.getLikeCount() + likes);
        this.newsMapper.updateById(newsDO);
    }
}
