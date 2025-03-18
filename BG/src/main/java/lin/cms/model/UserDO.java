package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * created by Xu on 2024/3/18 10:17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lin_user")
@EqualsAndHashCode(callSuper = true)
public class UserDO extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1463999384554707735L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
