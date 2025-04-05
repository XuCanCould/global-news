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
 * @time 2025/4/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("setting")
@EqualsAndHashCode(callSuper = true)
public class SettingDO extends BaseModel implements Serializable {

    private static final long serialVersionUID = -4265831949013479182L;

    @ApiModelProperty(value = "设置类型")
    private Integer type;

    @ApiModelProperty(value = "设置名称")
    private String name;

    @ApiModelProperty(value = "设置值")
    private String value;

    @ApiModelProperty(value = "父设置id")
    private Integer parentId;

}
