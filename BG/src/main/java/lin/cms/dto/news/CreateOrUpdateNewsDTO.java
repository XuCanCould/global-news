package lin.cms.dto.news;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * created by Xu on 2024/3/25 10:08.
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateNewsDTO {
    @NotEmpty(message = "${news.title.not-empty}")
    @Length(max = 100, message = "${news.title.length}")
    private String title;

    @NotEmpty(message = "${news.source.not-empty}")
    @Length(max = 30, message = "${news.source.length}")
    private String source;

    @NotEmpty(message = "${news.country.not-empty}")
    @Length(max = 30, message = "${news.country.length}")
    private String country;

    @NotEmpty(message = "${news.category.not-empty}")
    @Length(max = 30, message = "${news.category.length}")
    private String category;

    @NotEmpty(message = "${news.content.not-empty}")
    @Length(max = 2000, message = "${news.content.length}")
    private String content;

    @ApiModelProperty(value = "新闻状态")
    private Integer state;

}
