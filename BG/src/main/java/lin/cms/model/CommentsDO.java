package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Xu
 * @time 2025/4/4
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("news")
@EqualsAndHashCode(callSuper = true)
public class CommentsDO  extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1747045579689298704L;

    @ApiModelProperty(value = "评论id")
    private Integer id;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论人id")
    private Integer userId;

    @ApiModelProperty(value = "评论人昵称")
    private String nickname;

    @ApiModelProperty(value = "被评论新闻id")
    private Integer newsId;
}
