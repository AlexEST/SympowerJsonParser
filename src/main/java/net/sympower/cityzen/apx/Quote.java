/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sympower.cityzen.apx;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author AlexEST
 */
public class Quote {
    private String market;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime date_applied;
    @JsonProperty("values")
    private ArrayList<QuoteValue> values;

    public Quote() {
    }

    public Quote(String market, LocalDateTime date_applied, ArrayList<QuoteValue> values) {
        this.market = market;
        this.date_applied = date_applied;
        this.values = values;
    }

    public String getMarket() {
        return market;
    }

    public LocalDateTime getDate_applied() {
        return date_applied;
    }

    public ArrayList<QuoteValue> getValues() {
        return values;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public void setDate_applied(LocalDateTime date_applied) {
        this.date_applied = date_applied;
    }

    public void setValues(ArrayList<QuoteValue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = getDate_applied().format(formatter);
        StringBuilder sb = new StringBuilder();
		sb.append("\n").append(" {\n").append("   market: ").append(getMarket()).append("\n");
                sb.append("   date_applied: ").append(date).append("\n");
		sb.append("   values: ").append(getValues());
        return sb.toString();
    }
}
