package nowto.page.modularization;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 页面中的一个模块
 * @author liweibo
 */
public class Module {
    private String name;
    private Object entity;

    public static final Module emptyEntityModule(String name) {
        return new Module(name);
    }

    public Module(String name) {
        this.name = name;
    }

    public Module(String name, Object entity) {
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
