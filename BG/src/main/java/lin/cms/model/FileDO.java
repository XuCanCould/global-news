package lin.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * created by Xu on 2024/3/28 23:32.
 */
@Data
@Builder
@TableName("lin_file")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileDO extends BaseModel implements Serializable {

    private static final long serialVersionUID = -3203293656352763490L;

    @ApiModelProperty(value = "文件路径")
    private String path;

    /**
     * LOCAL REMOTE
     */
    @ApiModelProperty(value = "文件存放类型")
    private String type;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件后缀")
    private String extension;

    @ApiModelProperty(value = "文件大小")
    private Integer size;

    @ApiModelProperty(value = "文件md5值")
    private String md5;
}

