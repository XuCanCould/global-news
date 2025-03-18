package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * created by Xu on 2024/3/22 9:13.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lin_user_identity")
public class UserIdentityDO extends BaseModel implements Serializable {

    private static final long serialVersionUID = 456555840105356178L;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "认证类型，例如 username_password，用户名-密码认证")
    private String identityType;

    @ApiModelProperty(value = "认证，例如 用户名")
    private String identifier;

    @ApiModelProperty(value = "凭证，例如 密码")
    private String credential;

}
