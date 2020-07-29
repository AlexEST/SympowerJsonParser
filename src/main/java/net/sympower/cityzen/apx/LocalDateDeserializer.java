/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sympower.cityzen.apx;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 * @author AlexEST
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDateTime> {
    protected LocalDateDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        LocalDateTime ld = Instant.ofEpochMilli(parser.readValueAs(Long.class)).atZone(ZoneId.of("Europe/Amsterdam")).toLocalDateTime();
        return ld;
    }
}