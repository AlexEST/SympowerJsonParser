/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sympower.cityzen.apx;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *
 * @author AlexEST
 */
public class QuoteValue {
    private String tLabel;
    private String cLabel;
    @JsonDeserialize(using = FloatDeserializer.class)
    private Float value;
    private String unit;
    private int position;
    private boolean chartShow;
    private String chartType;
    private String color;
    private int precision;

    public QuoteValue() {
    }

    public QuoteValue(String tLabel, String cLabel, Float value, String unit, int position, boolean chartShow, String chartType, int precision) {
        this.tLabel = tLabel;
        this.cLabel = cLabel;
        this.value = value;
        this.unit = unit;
        this.position = position;
        this.chartShow = chartShow;
        this.chartType = chartType;
        this.precision = precision;
    }

    public String gettLabel() {
        return tLabel;
    }

    public String getcLabel() {
        return cLabel;
    }

    public Float getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public int getPosition() {
        return position;
    }

    public boolean isChartShow() {
        return chartShow;
    }

    public String getChartType() {
        return chartType;
    }

    public String getColor() {
        return color;
    }

    public int getPrecision() {
        return precision;
    }

    public void settLabel(String tLabel) {
        this.tLabel = tLabel;
    }

    public void setcLabel(String cLabel) {
        this.cLabel = cLabel;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setChartShow(boolean chartShow) {
        this.chartShow = chartShow;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
		sb.append("\n").append("     {\n").append("       tLabel: ").append(gettLabel()).append("\n");
                sb.append("       cLabel: ").append(getcLabel()).append("\n");
                sb.append("       value: ").append(getValue()).append("\n");
                sb.append("       unit: ").append(getUnit()).append("\n");
                sb.append("       position: ").append(getPosition()).append("\n");
                sb.append("       tLabel: ").append(gettLabel()).append("\n");
                sb.append("       chartShow: ").append(isChartShow()).append("\n");
                sb.append("       chartType: ").append(getChartType()).append("\n");
		sb.append("       precision: ").append(getPrecision()).append("\n");
                sb.append("     }");
        return sb.toString();
    }
}
