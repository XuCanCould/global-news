package lin.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * created by Xu on 2024/3/28 21:35.
 */
@Data
@TableName("lin_group_permission")
public class GroupPermissionDO implements Serializable {

    private static final long serialVersionUID = -358487811336536495L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分组id
     */
    @ApiModelProperty(value = "分组id")
    private Integer groupId;

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Integer permissionId;

    public GroupPermissionDO(Integer groupId, Integer permissionId) {
        this.groupId = groupId;
        this.permissionId = permissionId;
    }

}
