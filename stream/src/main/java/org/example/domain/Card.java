package org.example.domain;

public class Card {
    private Integer value;
    private Symbol symbol;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
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

    public  String getSuitIcon(Symbol suit){
        return switch (suit.toString()) {
            case "DIAMOND" -> "♦";
            case "HEART" -> "♥";
            case "SPADE" -> "♠";
            case "CLUB" -> "♣";
            default -> throw new IllegalStateException("Unexpected value: " + suit);
        };
    }
    @Override
    public String toString() {
        String cardColor = (symbol.name()=="HEART" || symbol.name()=="SPADE") ? ANSI_YELLOW:ANSI_PURPLE  ;
        return cardColor +
                "\n╔══════════╗\n" +
                "║ " + getCardValue(value) + "        ║\n" +
                "║          ║\n" +
                "║    " + getSuitIcon(symbol) + "     ║\n" +
                "║          ║\n" +
                "║        " + getCardValue(value) + " ║\n" +
                "╚══════════╝" +
                ANSI_RESET;
    }

    public static String getCardValue(int cardNumber){
        return switch (cardNumber) {
            case 1 -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(cardNumber);
        };
    }
}
