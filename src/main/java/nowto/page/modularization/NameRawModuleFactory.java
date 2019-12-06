package nowto.page.modularization;

import org.springframework.util.Assert;

import java.util.Map;

/**
 * 仅包含name，没有entiey的ModuleFacoty.
 *
 * 比如有些模块， 需要二次请求接口，所以没有实体
 * @author liweibo
 */
public class NameRawModuleFactory implements RawModuleFactory {
    private String moduleName;

    public NameRawModuleFactory(String moduleName) {
        Assert.hasText(moduleName, "模块名不能为空");
        this.moduleName = moduleName;
    }

    @Override
    public RawModule getModule(JsonModulesFactory jsonModulesFactory, Map<String, Object> context) {
        return RawModule.emptyEntityModule(this.getModuleName());
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }
}
