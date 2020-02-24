package nowto.page.modularization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.function.BooleanSupplier;

class EntityQuoteJsonSerializer extends StdSerializer<Module> {

    private BooleanSupplier quoteCondition = () -> false;
    public EntityQuoteJsonSerializer() {
        super(Module.class);
    }

    public EntityQuoteJsonSerializer(BooleanSupplier quoteCondition) {
        super(Module.class);
        this.quoteCondition = quoteCondition;
    }


    @Override
    public boolean isEmpty(SerializerProvider prov, Module value) {
        return value.toString().isEmpty();
    }

    @Override
    public void serialize(Module value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        if (quoteCondition.getAsBoolean()) {
            ObjectMapper mapper = (ObjectMapper) gen.getCodec();
            String valueString = mapper.writeValueAsString(value.getEntity());
            gen.writeObjectField("entity", valueString);
        } else {
            gen.writeObjectField("entity", value.getEntity());
        }
        gen.writeEndObject();
    }

    public static void main(String[] args) throws IOException {
        Module m = new Module("name", "liweibo");
        ObjectMapper om = new ObjectMapper();
        String a = om.writeValueAsString(m);
        System.out.println(a);
    }
}
