/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sympower.cityzen.apx;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/**
 *
 * @author AlexEST
 */
public class FloatDeserializer extends JsonDeserializer<Float>{

    @Override
    public Float deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {       
        return Float.parseFloat(jp.getValueAsString());        
    }
    
}
