package nowto.page.modularization;

import java.util.Map;
import java.util.function.Supplier;

class ModuleFactoryAdapter extends AbstractModuleFactory {
    private String name;
    private Supplier entityFactory;

    public ModuleFactoryAdapter(String name, Supplier entityFactory) {
        this.name = name;
        this.entityFactory = entityFactory;
    }

    @Override
    public Object getEntity(ModulesFactory modulesFactory, Map<String, Object> context) {
        return entityFactory.get();
    }

    @Override
    public String getModuleName() {
        return this.name;
    }
}
