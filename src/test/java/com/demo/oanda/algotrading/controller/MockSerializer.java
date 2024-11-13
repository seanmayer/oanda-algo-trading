package com.demo.oanda.algotrading.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MockSerializer<T> extends JsonSerializer<T> {

    private final Class<T> tClass;

    public MockSerializer(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("mockClass", tClass.getSimpleName());
        gen.writeEndObject();
    }
}