package org.example.domain;

public class Card {
    private Integer value;
    private Symbol symbol;

    public Card(Integer value, Symbol symbol) {
        this.value = value;
        this.symbol = symbol;
    }
    public Card(){}
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", symbol=" + symbol +
                '}';
    }
}
