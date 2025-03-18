package lin.cms.dto.news;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * created by Xu on 2024/3/25 10:08.
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateNewsDTO {
    @NotEmpty(message = "${news.title.not-empty}")
    @Length(max = 100, message = "${news.title.length}")
    private String title;

    @NotEmpty(message = "${news.author.not-empty}")
    @Length(max = 50, message = "${news.author.length}")
    private String author;

    @NotEmpty(message = "${news.summary.not-empty}")
    @Length(max = 1000, message = "${news.summary.length}")
    private String summary;

    @Length(max = 100, message = "${news.image.length}")
    private String image;
}
