package nowto.page.modularization;

import java.util.Map;

/**
 * 模块工厂.
 *
 * <p>该工厂生产的模块只是代表了模块内容，暂无格式化要求.</p>
 * @author liweibo
 */
public interface RawModuleFactory {
    /**
     * 获取模块
     * @param jsonModulesFactory modules工厂
     * @param context 上下文, 如果工厂生产一个模块需要参数，从该上下文获取
     * @return 如果不需要前端展示该模块返回null。
     * 如果返回不为null，那么返回值{@link RawModule#getName()}应该与 {@link #getModuleName()}相同
     */
    RawModule getModule(JsonModulesFactory jsonModulesFactory, Map<String, Object> context);

    /**
     * 获取模块名称.
     * 不能返回null
     * @return
     */
    String getModuleName();
}
