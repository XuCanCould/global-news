package lin.cms.service.impl;

import lin.cms.dto.news.CreateOrUpdateNewsDTO;
import lin.cms.mapper.NewsMapper;
import lin.cms.model.NewsDO;
import lin.cms.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by Xu on 2024/3/18 9:27.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public NewsDO getById(Integer id) {
        return newsMapper.selectById(id);
    }

    @Override
    public List<NewsDO> findAll() {
        return newsMapper.selectList(null);
    }

    @Override
    public boolean createNews(CreateOrUpdateNewsDTO validator) {
        NewsDO newsDO = new NewsDO();
        newsDO.setTitle(validator.getTitle());
        newsDO.setSource(validator.getAuthor());
        newsDO.setSummary(validator.getSummary());
        newsDO.setImage(validator.getImage());
        return this.newsMapper.insert(newsDO) > 0;
    }

    @Override
    public List<NewsDO> getNewsByKeyword(String q) {
        return this.newsMapper.selectByTitleLikeKeyword(q);
    }

    @Override
    public boolean updateNews(NewsDO book, CreateOrUpdateNewsDTO validator) {
        book.setSource(validator.getAuthor());
        book.setTitle(validator.getTitle());
        book.setImage(validator.getImage());
        book.setSummary(validator.getSummary());
        return this.newsMapper.updateById(book) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.newsMapper.deleteById(id) > 0;
    }
}
