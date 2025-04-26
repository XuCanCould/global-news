package lin.cms.dto.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Xu
 * @time 2025/4/26
 */
@ApiModel("基本数据统计")
@Data
public class BasicData implements Serializable {

    private static final long serialVersionUID = 8611617504568287111L;

    @ApiModelProperty("新闻数量")
    private Integer newsCount;

    @ApiModelProperty("浏览量数量")
    private Integer viewsCount;

    @ApiModelProperty("用户数量")
    private Integer userCount;
}
