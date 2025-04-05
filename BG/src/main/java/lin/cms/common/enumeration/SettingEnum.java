package lin.cms.common.enumeration;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;

/**
 * @author Xu
 * @time 2025/4/5
 */
@AllArgsConstructor
public enum SettingEnum  implements IEnum<Integer> {
    SOURCE(1, "来源"),
    CATEGORY(2, "分类"),
    ;
    private Integer code;
    private String name;

    @Override
    public Integer getValue() {
        return this.code;
    }

    public static SettingEnum getByCode(Integer code) {
        for (SettingEnum settingEnum : SettingEnum.values()) {
            if (settingEnum.code.equals(code)) {
                return settingEnum;
            }
        }
        return null;
    }
}
