package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * created by Xu on 2024/3/19 13:39.
 */
@Data
@Builder
@TableName("lin_permission")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PermissionDO extends BaseModel implements Serializable {
    private static final long serialVersionUID = -2400022443732120128L;

    @ApiModelProperty(value = "权限名称，例如：访问首页")
    private String name;

    @ApiModelProperty(value = "权限所属模块，例如：人员管理")
    private String module;

    @ApiModelProperty(value = "0：关闭  1：开启")
    private Boolean mount;

}
