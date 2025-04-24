package lin.cms.contoller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.Logger;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lin.cms.common.util.PageUtil;
import lin.cms.dto.news.CreateOrUpdateNewsDTO;
import lin.cms.dto.news.NewsDTO;
import lin.cms.model.NewsDO;
import lin.cms.service.NewsService;
import lin.cms.vo.CreatedVo;
import lin.cms.vo.PageResponseVO;
import lin.cms.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * created by Xu on 2024/3/18 8:58.
 */
@Api("新闻")
@RestController
@RequestMapping("/v1/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation("查看新闻详情")
    @GetMapping("/{id}")
    public NewsDO getBook(@PathVariable(value = "id") Integer id) {
        NewsDO book = newsService.getById(id);
        return book;
    }

    @ApiOperation("查看新闻列表")
    @GetMapping("")
    public PageResponseVO<NewsDTO> getNews(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "status", required = false, defaultValue = "1") Integer status) {
        // 默认查询已发布的数据
        IPage<NewsDTO> iPage = newsService.getNewsByKeyword(count, page, null, country, status);
        return PageUtil.build(iPage);
    }

    @PostMapping
    @LoginRequired
    @ApiOperation(value = "创建新闻")
    @PermissionMeta(value = "创建新闻", module = "新闻")
    @Logger(template = "创建新闻")
    public CreatedVo createNew(@RequestBody CreateOrUpdateNewsDTO book) {
        newsService.createNews(book);
        return new CreatedVo(12);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索新闻")
    public PageResponseVO<NewsDTO> searchBook(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page,
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "status", required = false, defaultValue = "1") Integer status) {
        return PageUtil.build(newsService.getNewsByKeyword(count, page, title, null, status));
    }

    @LoginRequired
    @PutMapping("/{id}")
    @ApiOperation(value = "更新新闻")
    @PermissionMeta(value = "更新新闻", module = "新闻")
    @Logger(template = "更新新闻")
    public UpdatedVO updateBook(@PathVariable("id") @Positive(message = "{id.positive}") Integer id,
                                @RequestBody @Validated CreateOrUpdateNewsDTO validator) {
        NewsDO book = newsService.getById(id);
        if (book == null) {
            throw new NotFoundException(10022);
        }
        newsService.updateNews(book, validator);
        return new UpdatedVO(13);
    }

    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除新闻", module = "新闻")
    @ApiOperation(value = "删除新闻")
    @Logger(template = "删除新闻")
    public UpdatedVO deleteBook(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        NewsDO book = newsService.getById(id);
        if (book == null) {
            throw new NotFoundException(10022);
        }
        newsService.deleteById(book.getId());
        return new UpdatedVO(14);
    }


}
