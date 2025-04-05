package lin.cms.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author Xu
 * @time 2025/4/4
 */
@Data
@ApiOperation(value = "用户评论")
public class UserCommentsVO {
    @ApiModelProperty(value = "评论id")
    private Integer id;

    @ApiModelProperty(value = "评论内容")
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "评论文章id")
    private String newsId;

    @ApiModelProperty(value = "评论时间")
    private Date commentTime;

    @ApiModelProperty(value = "被评论新闻标题")
    private String newsTitle;
}
