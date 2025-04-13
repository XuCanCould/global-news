package lin.cms.contoller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.talelin.core.annotation.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lin.cms.model.CommentsDO;
import lin.cms.service.CommentsService;
import lin.cms.vo.CreatedVo;
import lin.cms.vo.DeletedVO;
import lin.cms.vo.UserCommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xu
 * @time 2025/4/4
 */
@Api("评论")
@RestController
@RequestMapping("/v1/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;


    @LoginRequired
    @ApiOperation("获取我的评论")
    @GetMapping("/myComments")
    public List<UserCommentsVO> getMyComments() {
        return commentsService.getMyComments();
    }

    @ApiOperation("获取新闻评论")
    @GetMapping("/newsComments/{newsId}")
    public List<CommentsDO> getNewsComments(@PathVariable Integer newsId) {
        return commentsService.list(Wrappers.<CommentsDO>lambdaQuery().
                eq(CommentsDO::getNewsId, newsId).orderByDesc(CommentsDO::getCreateTime));
    }


    @LoginRequired
    @ApiOperation("创建评论")
    @PostMapping("")
    public CreatedVo createComments(@RequestBody UserCommentsVO comments) {
        commentsService.saveComments(comments);
        return new CreatedVo(16);
    }


    @LoginRequired
    @ApiOperation("删除评论")
    @DeleteMapping("/{id}")
    public DeletedVO deleteComments(@PathVariable Integer id) {
        commentsService.removeById(id);
        return new DeletedVO(17);
    }

}
