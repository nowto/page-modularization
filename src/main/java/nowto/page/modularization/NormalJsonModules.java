package nowto.page.modularization;

class NormalJsonModules extends JsonModules<NormalJsonModule> {
    @Override
    protected NormalJsonModule newModule(String moduleName) {
        return new NormalJsonModule(moduleName);
    }

    @Override
    protected NormalJsonModule newModule(String name, Object moduleEntity) {
        return new NormalJsonModule(name, moduleEntity);
    }

    @Override
    protected NormalJsonModule newModule(RawModule rawModule) {
        return new NormalJsonModule(rawModule.getName(), rawModule.getEntity());
    }
}
