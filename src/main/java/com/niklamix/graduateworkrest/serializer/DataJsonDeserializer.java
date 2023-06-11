package com.niklamix.graduateworkrest.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DataJsonDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        if (StringUtils.isBlank(date)) {
            return null;
        }

        ZonedDateTime userDateTime = ZonedDateTime.parse(date);
        ZonedDateTime serverTime = userDateTime.withZoneSameInstant(ZoneId.systemDefault());
        return serverTime.toLocalDateTime();

    }
}
