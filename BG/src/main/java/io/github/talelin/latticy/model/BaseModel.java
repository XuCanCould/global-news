package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    protected Date createTime;

    @JsonIgnore
    protected Date updateTime;

    @JsonIgnore
    protected Date deleteTime;

    @TableLogic
    @JsonIgnore
    protected Boolean isDeleted;
}
