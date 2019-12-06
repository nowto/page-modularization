package nowto.page.modularization;

/**
 * module的entity需要两端加引号的Modules.
 *
 * @author liweibo
 */
class QuotedJsonModules extends JsonModules<QuotedJsonModule> {
    @Override
    protected QuotedJsonModule newModule(String moduleName) {
        return new QuotedJsonModule(moduleName);
    }

    @Override
    protected QuotedJsonModule newModule(String name, Object moduleEntity) {
        return new QuotedJsonModule(name, moduleEntity);
    }

    @Override
    protected QuotedJsonModule newModule(RawModule rawModule) {
        return new QuotedJsonModule(rawModule.getName(), rawModule.getEntity());
    }
}
