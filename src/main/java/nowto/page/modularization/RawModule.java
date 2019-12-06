package nowto.page.modularization;

/**
 * raw module.
 *
 * <p>该Module只是代表了模块内容，没有格式，不会被用来做json格式化。被用来做json格式化的是{@link JsonModule JsonModule}</p>
 *
 * @see JsonModule
 * @author liweibo
 */
public class RawModule {
    private String name;
    private Object entity;

    public static final RawModule emptyEntityModule(String name) {
        return new RawModule(name);
    }

    public RawModule(String name) {
        this.name = name;
    }

    public RawModule(String name, Object entity) {
        this.name = name;
        this.entity = entity;
    }

    public String getName() {
        return this.name;
    }

    public Object getEntity() {
        return this.entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
