package com.fk.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;

@SuppressWarnings({ "deprecation", "serial" })
public class Issue205FixedUntypedObjectDeserializer extends UntypedObjectDeserializer {

    private static final Issue205FixedUntypedObjectDeserializer INSTANCE = new Issue205FixedUntypedObjectDeserializer();

    private Issue205FixedUntypedObjectDeserializer() {
    }

    public static Issue205FixedUntypedObjectDeserializer getInstance() {
        return INSTANCE;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Object mapObject(JsonParser parser, DeserializationContext context) throws IOException {

        String firstKey;
        JsonToken token = parser.getCurrentToken();
        if (token == JsonToken.START_OBJECT) {
            firstKey = parser.nextFieldName();
        } else if (token == JsonToken.FIELD_NAME) {
            firstKey = parser.getCurrentName();
        } else {
            if (token != JsonToken.END_OBJECT) {
                throw context.mappingException(handledType(), parser.getCurrentToken());
            }
            return new HashMap<>();
        }

        Map<String, Object> valueByKey = new LinkedHashMap<>();
        String nextKey = firstKey;
        do {

            parser.nextToken();
            Object nextValue = deserialize(parser, context);

            if (valueByKey.containsKey(nextKey)) {
                Object existingValue = valueByKey.get(nextKey);
                if (existingValue instanceof List) {
                    List<Object> values = (List<Object>) existingValue;
                    values.add(nextValue);
                } else {
                    List<Object> values = new ArrayList<>();
                    values.add(existingValue);
                    values.add(nextValue);
                    valueByKey.put(nextKey, values);
                }
            }

            else {
                valueByKey.put(nextKey, nextValue);
            }

        } while ((nextKey = parser.nextFieldName()) != null);

        return valueByKey;

    }

}
