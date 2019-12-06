package nowto.page.modularization;

/**
 * 代表前端一个页面当中的一个模块.
 *
 * <p>被用来做json序列化，需要使用Jackson</p>
 */
public interface JsonModule {
    String getName();
    Object getEntity();
    void setEntity(Object entity);
}
