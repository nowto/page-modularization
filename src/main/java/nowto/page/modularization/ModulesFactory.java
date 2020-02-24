package nowto.page.modularization;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * modules工厂
 * @author liweibo
 */
public class ModulesFactory {
    private List<ModuleFactory> moduleFactories = new ArrayList<>();

    public ModulesFactory() {
    }

    public void addModuleFactory(ModuleFactory moduleFactory) {
        Assert.notNull(moduleFactory, "模块工厂不能为null");
        moduleFactories.add(moduleFactory);
    }

    public void addModuleFactories(List<ModuleFactory> moduleFactories) {
        for (ModuleFactory factory: moduleFactories) {
            addModuleFactory(factory);
        }
    }

    /**
     * 该类核心方法
     * @return
     */
    public List<Module> getModules(Map<String, Object> context) {
        if (context == null) {
            context = new HashMap<>();
        }
        List<Module> modules = new ArrayList<>();
        for (ModuleFactory factory: moduleFactories) {
            Module module = factory.getModule(this, context);
            if (module != null) {
                modules.add(module);
            }
        }
        return modules;
    }

    public List<ModuleFactory> getModuleFactories() {
        return moduleFactories;
    }

}
