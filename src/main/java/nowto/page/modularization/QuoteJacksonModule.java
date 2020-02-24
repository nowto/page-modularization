package nowto.page.modularization;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.function.BooleanSupplier;

public class QuoteJacksonModule extends SimpleModule {
    public QuoteJacksonModule(BooleanSupplier quoteCondition) {
        super("1.5");
        addSerializer(Module.class, new EntityQuoteJsonSerializer(quoteCondition));
    }
}
