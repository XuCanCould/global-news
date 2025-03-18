package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lin.cms.common.enumeration.GroupLevelEnum;
import lombok.*;

import java.io.Serializable;

/**
 * created by Xu on 2024/3/21 20:38.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("lin_group")
@EqualsAndHashCode(callSuper = true)
public class GroupDO extends BaseModel implements Serializable {
    private static final long serialVersionUID = -8994898895671436007L;

    @ApiModelProperty(value = "组名")
    private String name;

    @ApiModelProperty(value = "组描述")
    private String info;

    @TableField(value = "`level`")
    private GroupLevelEnum level;

}
