package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pedro@TaleLin
 * @author Juzi@TaleLin
 * 新闻主体数据对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("news")
@EqualsAndHashCode(callSuper = true)
// 对象比较时，默认不比较父类属性的值，而设置为true，父类字段会被进行比较
public class NewsDO extends BaseModel implements Serializable {

    private static final long serialVersionUID = 3531805912578317266L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分类")
    private String category;

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

    @ApiModelProperty(value = "国家")
    private String country;
}
