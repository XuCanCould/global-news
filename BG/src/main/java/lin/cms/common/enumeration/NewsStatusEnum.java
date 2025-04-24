package lin.cms.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Xu
 * @time 2025/4/24
 */
@Getter
@AllArgsConstructor
public enum NewsStatusEnum {

    TO_DEAL(0, "待处理"),
    PUBLISHED(1, "已发布"),
//    REJECTED(2, "已拒绝"),
//    DELETED(3, "已删除"),
    ;
    private Integer code;
    private String name;
}
