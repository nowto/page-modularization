package nowto.page.modularization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.util.Map;


public class JsonModulesFactoryTest {

    @Test
    public void test() throws JsonProcessingException {
        JsonModulesFactory jsonModulesFactory = new JsonModulesFactory();
        jsonModulesFactory.addModuleFactory(new AbstractRawModuleFactory() {
            @Override
            public Object getEntity(JsonModulesFactory jsonModulesFactory, Map<String, Object> context) {
                return new Person("nowto", 28);
            }

            @Override
            public String getModuleName() {
                return "aGoodMan";
            }
        });

        jsonModulesFactory.addModuleFactory(new AbstractRawModuleFactory() {
            @Override
            public Object getEntity(JsonModulesFactory jsonModulesFactory, Map<String, Object> context) {
                return new SmartPhone("black", 100);
            }

            @Override
            public String getModuleName() {
                return "smartisan";
            }
        });

        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        {
            String result = om.writeValueAsString(jsonModulesFactory.getQuotedModules(null));
            System.out.println(result);
        }
        {
            String result = om.writeValueAsString(jsonModulesFactory.getNormalModules(null));
            System.out.println(result);
        }
    }

    public static class SmartPhone {
        private String color;
        private int screenOccupationRatio;

        public SmartPhone(String color, int screenOccupationRatio) {
            this.color = color;
            this.screenOccupationRatio = screenOccupationRatio;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getScreenOccupationRatio() {
            return screenOccupationRatio;
        }

        public void setScreenOccupationRatio(int screenOccupationRatio) {
            this.screenOccupationRatio = screenOccupationRatio;
        }
    }

    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}