package lin.cms.dto.news;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Xu
 * @time 2025/4/6
 */
@Data
@ApiOperation("新闻dto")
public class NewsDTO implements Serializable {
    private static final long serialVersionUID = 597862528912258450L;

    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分类")
    private Integer category;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "是否置顶")
    private Boolean top;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "修改人")
    private String updater;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "点赞量")
    private Integer likeCount;

    @ApiModelProperty(value = "评论量")
    private Integer comments;
}
