package nowto.page.modularization;

/**
 *  正常的module.
 *  entity两端不需要加引号.
 * @author liweibo
 */
class NormalJsonModule implements JsonModule {
    private String name;
    private Object entity;

    public NormalJsonModule(String name) {
        this.name = name;
    }

    public NormalJsonModule(String name, Object entity) {
        this.name = name;
        this.entity = entity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getEntity() {
        return this.entity;
    }

    @Override
    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
