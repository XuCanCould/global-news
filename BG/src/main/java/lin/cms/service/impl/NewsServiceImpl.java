package lin.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lin.cms.dto.convert.NewsConvert;
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
    public boolean createNews(CreateOrUpdateNewsDTO validator) {
        NewsDO book = NewsConvert.INSTANCE.toDO(validator);
        return this.newsMapper.insert(book) > 0;
    }

    @Override
    public IPage<NewsDO> getNewsByKeyword(Integer count, Integer page, String q) {
        Page<NewsDO> pager = new Page<>(page, count);
        return this.newsMapper.getNewsPage(pager, q);
    }

    @Override
    public boolean updateNews(NewsDO book, CreateOrUpdateNewsDTO validator) {
        book.setTitle(validator.getTitle());
        book.setSource(validator.getSource());
        book.setCategory(validator.getCategory());
        book.setContent(validator.getContent());
        return this.newsMapper.updateById(book) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.newsMapper.deleteById(id) > 0;
    }
}
