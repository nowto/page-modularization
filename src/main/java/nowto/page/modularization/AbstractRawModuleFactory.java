package nowto.page.modularization;

import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 抽象ModuleFacoty，方便子类实现ModuleFacoty
 */
public abstract class AbstractRawModuleFactory implements RawModuleFactory {

    /**
     * 获取实体
     * @param jsonModulesFactory
     * @param context
     * @return
     */
    public abstract Object getEntity(JsonModulesFactory jsonModulesFactory, Map<String, Object> context);

    /**
     * 实现父接口，模板方法
     * @param jsonModulesFactory modules工厂
     * @param context 上下文
     * @return 模块
     */
    @Override
    public RawModule getModule(JsonModulesFactory jsonModulesFactory, Map<String, Object> context) {
        Object entity = getEntity(jsonModulesFactory, context);
        if (entity == null) {
            return null;
        }
        if (entity instanceof Collection) {
            Collection<?> c = (Collection<?>) entity;
            if (c.isEmpty()) {
                return null;
            }
        }
        if (entity.getClass().isArray()) {
            if (Array.getLength(entity) == 0) {
                return null;
            }
        }
        if (entity instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) entity;
            if (map.isEmpty()) {
                return null;
            }
        }
        if (entity instanceof String) {
            String str = (String) entity;
            if (!StringUtils.hasText(str)) {
                return null;
            }
        }
        return new RawModule(getModuleName(), entity);
    }
}
