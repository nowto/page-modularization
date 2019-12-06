package nowto.page.modularization;

import org.springframework.core.Conventions;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * JsonModule的集合，用于接口返回使用.
 *
 *
 * @author liweibo
 */
public abstract class JsonModules<M extends JsonModule> {
    private List<M> modules = new ArrayList<>();

    public JsonModules() {
    }

    /**
     * 工厂方法.
     *
     * <p>entity两端需要加引号</p>
     * @return
     */
    public static final JsonModules quotedModules() {
        return new QuotedJsonModules();
    }

    /**
     * 工厂方法.
     *
     * <p>entity两端不需要加引号</p>
     * @return
     */
    public static final JsonModules normalModules() {
        return new NormalJsonModules();
    }

    /**
     * 构建一个新JsonModule
     * @param moduleName 模块名
     * @return 新的JsonModule
     */
    protected abstract M newModule(String moduleName);

    /**
     * 构建一个新JsonModule
     * @param moduleName 模块名
     * @param moduleEntity 模块实体
     * @return 新的JsonModule
     */
    protected abstract M newModule(String moduleName, Object moduleEntity);

    /**
     * 构建一个新JsonModule, 通过一个RawModule
     * @param rawModule
     * @return 新的JsonModule
     */
    protected abstract M newModule(RawModule rawModule);

    /**
     * 添加一个模块.
     * 不包含模块体
     * @param name
     */
    public void putModule(String name) {
        this.putModule(newModule(name));
    }

    /**
     * 添加一个模块.
     * 模块名根据entity的完全限定名推测
     * @param entity
     */
    public void putModule(@NonNull Object entity) {
        Assert.notNull(entity, "entity不能为null");
        String name = Conventions.getVariableName(entity);
        this.putModule(newModule(name, entity));
    }

    /**
     * 添加一个模块
     * @param entity
     */
    public void putModule(String name, Object entity) {
        this.putModule(newModule(name, entity));
    }

    /**
     * 添加一个模块
     * @param rawModule
     */
    public void putModule(RawModule rawModule) {
        Assert.notNull(rawModule, "模块不能为null");
        modules.add(newModule(rawModule));
    }

    private void putModule(M module) {
        this.modules.add(module);
    }

    /**
     * 核心方法，用来获取能够使用Jackson来序列化的一个页面的所有模块
     * @return
     */
    public List<? extends JsonModule> getJsonModules() {
        return modules;
    }
}
