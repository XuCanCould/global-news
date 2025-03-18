package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * created by Xu on 2024/3/23 11:39.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lin_log")
@EqualsAndHashCode(callSuper = true)
public class LogDO extends BaseModel implements Serializable {

    private static final long serialVersionUID = -7471474245124682011L;

    @ApiModelProperty(value = "日志内容")
    private String message;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "请求状态码")
    private Integer statusCode;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "请求路径")
    private String path;

    @ApiModelProperty(value = "权限")
    private String permission;

}
