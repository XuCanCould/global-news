package lin.cms.contoller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lin.cms.common.enumeration.SettingEnum;
import lin.cms.model.SettingDO;
import lin.cms.service.SettingService;
import lin.cms.vo.DeletedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiModelProperty(value = "获取设置")
    @GetMapping("/{type}")
    public List<SettingDO> getSetting(@PathVariable Integer type) {
        if (type == null) {
            return settingService.list();
        }
        if (!Arrays.asList(SettingEnum.values()).contains(SettingEnum.getByCode(type))) {
            return null;
        }
        return settingService.list(Wrappers.<SettingDO>lambdaQuery().eq(SettingDO::getType, type));
    }

    @ApiOperation(value = "删除设置")
    @DeleteMapping("/{id}")
    public DeletedVO deleteSetting(@PathVariable Integer id) {
        settingService.removeById(id);
        return new DeletedVO();
    }

    @ApiOperation(value = "更新设置")
    @PutMapping("/{id}")
    public DeletedVO updateSetting(@PathVariable Integer id, @RequestBody SettingDO setting) {
        setting.setId(id);
        settingService.updateById(setting);
        return new DeletedVO();
    }

    @ApiOperation(value = "创建设置")
    @PostMapping
    public DeletedVO createSetting(@RequestBody SettingDO setting) {
        settingService.save(setting);
        return new DeletedVO();
    }


}
