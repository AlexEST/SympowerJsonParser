package net.sympower.cityzen.apx;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class QuoteResponse {
    @JsonProperty("quote")
    private ArrayList<Quote> QuoteResponse = new ArrayList<Quote>();

    public QuoteResponse() {
    }

    public ArrayList<Quote> getQuoteResponse() {
        return QuoteResponse;
    }

    public void setQuoteResponse(ArrayList<Quote> QuoteResponse) {
        this.QuoteResponse = QuoteResponse;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
		sb.append("Quote:");
		sb.append("  ").append(getQuoteResponse()).append("\n");
        return sb.toString();
    }
}

