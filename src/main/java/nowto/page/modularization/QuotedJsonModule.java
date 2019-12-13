package nowto.page.modularization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * entity需要被引号括起来的module.
 *
 * @see JsonModules
 * @see QuotedJsonModules
 *
 * @author liweibo
 */
class QuotedJsonModule implements JsonModule {
    /**
     * 模块名
     */
    private final String name;

    /**
     * 模块实体
     */
    @JsonSerialize(using = EntityJsonSerializer.class)
    @Nullable private Object entity;

    public QuotedJsonModule(String name) {
        Assert.notNull(name, "模块名不能为null");
        this.name = name;
        this.entity = null;
    }

    public QuotedJsonModule(String name, @Nullable Object entity) {
        Assert.notNull(name, "模块名不能为null");
        this.name = name;
        this.entity = entity;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public Object getEntity() {
        return entity;
    }

    @Override
    public void setEntity(@Nullable Object entity) {
        this.entity = entity;
    }

    public static class EntityJsonSerializer extends StdSerializer<Object> {

        public EntityJsonSerializer() {
            super(Object.class);
        }


        @Override
        public boolean isEmpty(SerializerProvider prov, Object value) {
            return value.toString().isEmpty();
        }

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            ObjectMapper mapper = (ObjectMapper) gen.getCodec();
            gen.writeString(mapper.writeValueAsString(value));
        }
    }

}
