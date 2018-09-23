package com.sanctaultras.encryptedchat.commons;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BaseEntitySerializer extends JsonSerializer<BaseEntity> {
    @Override
    public void serialize(BaseEntity baseEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(baseEntity.getId().toString());
    }
}