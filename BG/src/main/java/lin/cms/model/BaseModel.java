package lin.cms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Juzi@TaleLin
 * 基础数据模型类
 */
@Data
public class BaseModel {
    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;

    @ApiModelProperty(value = "创建时间")
    @JsonIgnore
    protected Date createTime;

    @ApiModelProperty(value = "更新时间")
    protected Date updateTime;

    @ApiModelProperty(value = "删除时间")
    @JsonIgnore
    protected Date deleteTime;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    @JsonIgnore
    protected Boolean isDeleted;
}
