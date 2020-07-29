package net.sympower.cityzen.apx;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.jayway.jsonpath.JsonPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


public class ApxDataLoaderTest {

    @Test
    public void load() throws Exception {
        ApxDataLoader sut = new ApxDataLoader();
        sut.url = getClass().getResource("apx-data.json");
        QuoteResponse response = sut.load();
        //TODO and more asserts
        assertNotNull(response == null);
        assertNotNull(response.getQuoteResponse().isEmpty());
        Assert.assertTrue(response.getQuoteResponse().size() > 0);
    }
    
    @Test
    public void jsonPathTest() throws IOException {
        ApxDataLoader sut = new ApxDataLoader();
        sut.url = getClass().getResource("apx-data.json");
        QuoteResponse response = sut.load();
        BufferedReader in = new BufferedReader(new InputStreamReader(sut.url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        String json = stringBuilder.toString().replaceAll("  ", "");
        for (int i = 0; i < response.getQuoteResponse().size(); i++) {
            String market = JsonPath.read(json, "$.quote["+i+"].market");
            Long dateApp = JsonPath.read(json, "$.quote["+i+"].date_applied");
            LocalDateTime dateApplied = Instant.ofEpochMilli(dateApp)
                    .atZone(ZoneId.of("Europe/Amsterdam")).toLocalDateTime();
            assertEquals(market, response.getQuoteResponse().get(i).getMarket());
            assertEquals(dateApplied, response.getQuoteResponse().get(i).getDate_applied());
            for (int j = 0; j < response.getQuoteResponse().get(i).getValues().size(); j++) {
                String tLabel = JsonPath.read(json, "$.quote["+i+"].values["+j+"].tLabel");
                String cLabel = JsonPath.read(json, "$.quote["+i+"].values["+j+"].cLabel");
                Float value = Float.parseFloat((String) JsonPath.read(json, "$.quote["+i+"].values["+j+"].value"));
                String unit = JsonPath.read(json, "$.quote["+i+"].values["+j+"].unit");
                int position = JsonPath.read(json, "$.quote["+i+"].values["+j+"].position");
                boolean chartShow = JsonPath.read(json, "$.quote["+i+"].values["+j+"].chartShow");
                String chartType = JsonPath.read(json, "$.quote["+i+"].values["+j+"].chartType");
                String color = JsonPath.read(json, "$.quote["+i+"].values["+j+"].color");
                int precision = JsonPath.read(json, "$.quote["+i+"].values["+j+"].precision");
                assertEquals(tLabel, response.getQuoteResponse().get(i).getValues().get(j).gettLabel());
                assertEquals(cLabel, response.getQuoteResponse().get(i).getValues().get(j).getcLabel());
                assertEquals(value, response.getQuoteResponse().get(i).getValues().get(j).getValue());
                assertEquals(unit, response.getQuoteResponse().get(i).getValues().get(j).getUnit());
                assertEquals(position, response.getQuoteResponse().get(i).getValues().get(j).getPosition());
                assertEquals(chartShow, response.getQuoteResponse().get(i).getValues().get(j).isChartShow());
                assertEquals(chartType, response.getQuoteResponse().get(i).getValues().get(j).getChartType());
                assertEquals(color, response.getQuoteResponse().get(i).getValues().get(j).getColor());
                assertEquals(precision, response.getQuoteResponse().get(i).getValues().get(j).getPrecision());
            }
        }
        System.out.println(response);
    }
    
    @Test
    public void jacksonTest() throws IOException {
        ApxDataLoader sut = new ApxDataLoader();
        sut.url = getClass().getResource("apx-data.json");
        QuoteResponse response = sut.load();
        System.out.println(response);
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(sut.url);
        Quote quote = new Quote();
        QuoteValue quoteValue = new QuoteValue();
        int counter = 0;
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            if (jsonToken == null) {
                break;
            }
            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                jsonToken = parser.nextToken();

                if ("market".equals(fieldName)) {
                    quote.setMarket(parser.getValueAsString());
                    assertEquals("market", fieldName);
                    assertEquals(quote.getMarket(), response.getQuoteResponse()
                            .get(counter).getMarket());
                } else if ("date_applied".equals(fieldName)) {
                    quote.setDate_applied(Instant.ofEpochMilli(parser.getValueAsLong())
                            .atZone(ZoneId.of("Europe/Amsterdam")).toLocalDateTime());
                    assertEquals("date_applied", fieldName);
                    assertEquals(quote.getDate_applied(), response.getQuoteResponse()
                            .get(counter).getDate_applied());
                } else if ("values".equals(fieldName)) {
                    assertEquals("values", fieldName);
                    int valCounter = 0;
                    while (!JsonToken.END_ARRAY.equals(jsonToken)) {
                        jsonToken = parser.nextToken();
                        if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                            fieldName = parser.getCurrentName();
                            jsonToken = parser.nextToken();
                            if ("tLabel".equals(fieldName)) {
                                quoteValue.settLabel(parser.getValueAsString());
                                assertEquals(quoteValue.gettLabel(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).gettLabel());
                                assertEquals("tLabel", fieldName);
                            } else if ("cLabel".equals(fieldName)) {
                                quoteValue.setcLabel(parser.getValueAsString());
                                assertEquals(quoteValue.getcLabel(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).getcLabel());
                                assertEquals("cLabel", fieldName);
                            } else if ("value".equals(fieldName)) {
                                quoteValue.setValue(Float.parseFloat(parser
                                        .getValueAsString()));
                                assertEquals(quoteValue.getValue(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).getValue());
                                assertEquals("value", fieldName);
                            } else if ("unit".equals(fieldName)) {
                                quoteValue.setUnit(parser.getValueAsString());
                                assertEquals(quoteValue.getUnit(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).getUnit());
                                assertEquals("unit", fieldName);
                            } else if ("position".equals(fieldName)) {
                                quoteValue.setPosition(Integer.parseInt(parser
                                        .getValueAsString()));
                                assertEquals(quoteValue.getPosition(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).getPosition());
                                assertEquals("position", fieldName);
                            } else if ("chartShow".equals(fieldName)) {
                                quoteValue.setChartShow(Boolean.parseBoolean(parser
                                        .getValueAsString()));
                                assertEquals(quoteValue.isChartShow(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).isChartShow());
                                assertEquals("chartShow", fieldName);
                            } else if ("chartType".equals(fieldName)) {
                                quoteValue.setChartType(parser.getValueAsString());
                                assertEquals(quoteValue.getChartType(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).getChartType());
                                assertEquals("chartType", fieldName);
                            } else if ("color".equals(fieldName)) {
                                quoteValue.setColor(parser.getValueAsString());
                                assertEquals(quoteValue.getColor(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).getColor());
                                assertEquals("color", fieldName);
                            } else if ("precision".equals(fieldName)) {
                                quoteValue.setPrecision(Integer
                                        .parseInt(parser.getValueAsString()));
                                assertEquals(quoteValue.getPrecision(), 
                                        response.getQuoteResponse()
                                                .get(counter).getValues()
                                                .get(valCounter).getPrecision());
                                assertEquals("precision", fieldName);
                                valCounter++;
                            }
                        }
                    }
                    counter++;
                }
            }
        }     
    }    
}