package fr.mael;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTransient {

    static class ClassWithTransientProperty {
        private final String property;
        @JsonIgnore
        private final transient String transientProperty;

        public ClassWithTransientProperty(String property, String transientProperty) {
            this.property = property;
            this.transientProperty = transientProperty;
        }

        public String getProperty() {
            return property;
        }

        public String getTransientProperty() {
            return transientProperty;
        }
    }

    @Test
    void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(new ClassWithTransientProperty("value1", "value2"));
        Assertions.assertFalse(result.contains("value2"));
    }
}
