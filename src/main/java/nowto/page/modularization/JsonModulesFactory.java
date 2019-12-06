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
public class JsonModulesFactory {
    private List<RawModuleFactory> moduleFactories = new ArrayList<>();

    public JsonModulesFactory() {
    }

    public void addModuleFactory(RawModuleFactory rawModuleFactory) {
        Assert.notNull(rawModuleFactory, "模块工厂不能为null");
        moduleFactories.add(rawModuleFactory);
    }

    public void addModuleFactories(List<RawModuleFactory> moduleFactories) {
        for (RawModuleFactory factory: moduleFactories) {
            addModuleFactory(factory);
        }
    }

    /**
     * 该类的核心方法
     * @return
     */
    public List<? extends JsonModule> getQuotedModules(Map<String, Object> context) {
        if (context == null) {
            context = new HashMap<>();
        }
        JsonModules odules = JsonModules.quotedModules();
        for (RawModuleFactory factory: moduleFactories) {
            RawModule rawModule = factory.getModule(this, context);
            if (rawModule != null) {
                odules.putModule(rawModule);
            }
        }
        return odules.getJsonModules();
    }

    /**
     * 该类核心方法
     * @return
     */
    public List<? extends JsonModule> getNormalModules(Map<String, Object> context) {
        if (context == null) {
            context = new HashMap<>();
        }
        JsonModules odules = JsonModules.normalModules();
        for (RawModuleFactory factory: moduleFactories) {
            RawModule rawModule = factory.getModule(this, context);
            if (rawModule != null) {
                odules.putModule(rawModule);
            }
        }
        return odules.getJsonModules();
    }

    public List<RawModuleFactory> getModuleFactories() {
        return moduleFactories;
    }

}
