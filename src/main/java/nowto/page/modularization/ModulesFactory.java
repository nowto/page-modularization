package nowto.page.modularization;

import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Supplier;

/**
 * modules工厂
 * @author liweibo
 */
public class ModulesFactory {
    private List<ModuleFactory> moduleFactories = new ArrayList<>();

    /**
     * 默认不保留
     */
    private boolean retainAdvise = false;

    public ModulesFactory() {
    }

    /**
     * 默认情况下，{@link AbstractModuleFactory#getModule(ModulesFactory, Map)}实现 entity为null,或者是list但size为0，或者是
     * array但length为0，或者为map但size为0时, 将返回null，最终生成的modules不保留那个模块。
     *
     * retainAdvise是一个建议，决定权在ModuleFactory。
     * 该参数为true，向ModuleFactory发出要保留那个module的建议，ModuleFactory可以理会也可以不理会。
     *
     * {@link AbstractModuleFactory}理会了这个建议
     *
     * @see AbstractModuleFactory
     *
     * @param retainAdvise
     */
    public ModulesFactory(boolean retainAdvise) {
        this.retainAdvise = retainAdvise;
    }

    public void addModuleFactory(ModuleFactory moduleFactory) {
        Assert.notNull(moduleFactory, "模块工厂不能为null");
        moduleFactories.add(moduleFactory);
    }

    public void addModuleFactory(String moduleName, Object  entity) {
        Assert.hasText(moduleName, "实体名称不能为空");
        Assert.notNull(entity, "实体工厂不能为null");
        moduleFactories.add(new ModuleFactoryAdapter(moduleName, () -> entity));
    }

    public void addModuleFactory(String moduleName, Supplier  entityFactory) {
        Assert.hasText(moduleName, "实体名称不能为空");
        Assert.notNull(entityFactory, "实体工厂不能为null");
        moduleFactories.add(new ModuleFactoryAdapter(moduleName, entityFactory));
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

    public boolean isRetainAdvise() {
        return retainAdvise;
    }

    public List<ModuleFactory> getModuleFactories() {
        return moduleFactories;
    }

}
