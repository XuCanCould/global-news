package lin.cms.contoller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import lin.cms.common.enumeration.SettingEnum;
import lin.cms.model.SettingDO;
import lin.cms.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Xu
 * @time 2025/4/5
 */
@Api("设置")
@RestController
@RequestMapping("/v1/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @GetMapping("/{type}")
    public List<SettingDO> getSetting(@PathVariable Integer type) {
        if (type == null) {
            return settingService.list();
        }
        if (Arrays.asList(SettingEnum.values()).contains(SettingEnum.getByCode(type))) {
            return null;
        }
        return settingService.list(Wrappers.<SettingDO>lambdaQuery().eq(SettingDO::getType, type));
    }
}
