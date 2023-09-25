package org.example;

import org.example.domain.Card;
import org.example.domain.Player;
import org.example.domain.Symbol;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Service {
    static Integer Score=1000;
    public List<Card> build() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Symbol.values()).forEach(symbol -> {
            for (int i = 0; i < 13; i++) {
                Card card = new Card(i + 1, symbol);
                cards.add(card);
            }
        });
        return cards;
    }

    public HashMap<Integer, List<Card>> extraire_h_card(List<Card> cards, Integer indice) {

        var cardsList = new HashMap<Integer, List<Card>>();

        Card remove = cards.remove(indice.intValue());
        cardsList.put(2, cards);
        cardsList.put(1, List.of(remove));
        return cardsList;
    }

    public HashMap<Integer, List<Card>> draw_a_card(List<Card> cards) {
        Integer random = new Random().nextInt(cards.size() - 1);
        return extraire_h_card(cards, random);
    }

    public List<Card> mixer_cards(List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>();
        while(cards.isEmpty()) {
            HashMap<Integer, List<Card>> restOfCards = draw_a_card(cards);
            shuffledCards.add(restOfCards.get(1).get(0));
            cards=restOfCards.get(2);
        }
        return shuffledCards;
    }

    public HashMap<Integer,List<Card>> splitCard(List<Card> cards){
        int n = new Random().nextInt(cards.size() / 3, cards.size() / 2);
        List<Card> firstHalf = new ArrayList<>();
        List<Card> secondHalf = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            if (i < n) {
                firstHalf.add(cards.get(i));
            } else {
                secondHalf.add(cards.get(i));
            }
        }

        HashMap<Integer, List<Card>> result = new HashMap<>();
        result.put(1, firstHalf);
        result.put(2, secondHalf);

        return result;

    }

    public List<Card> draw_n_card(List<Card> cards,int n){
    List<Card> playCards=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            playCards.add(cards.remove(0));
        }
     return playCards;
    }
    public HashMap<Integer,List<Card>> StartRound(List<Card> cards){
        List<Card> botCards=draw_n_card(cards,2);
        botCards.stream()
                .forEach(card -> {cards.remove(card);});
        List<Card> playerCards=draw_n_card(cards,2);
        playerCards.stream()
                .forEach(card -> {cards.remove(card);});
        var list=new HashMap<Integer,List<Card>>();
        list.put(1,cards);
        list.put(2,botCards);
        list.put(3,playerCards);
        return list;
    }

    public static String getSuitIcon(Symbol suit){
        return switch (suit.toString()) {
            case "DIAMONDS" -> "♦";
            case "HEARTS" -> "♥";
            case "SPADES" -> "♠";
            case "CLUBS" -> "♣";
            default -> throw new IllegalStateException("Unexpected value: " + suit);
        };
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

    public void displayCard(List<Card> cards){
        System.out.print("{");
        cards.stream()
                .forEach(card->{System.out.print("("+getCardValue(card.getValue())+","+getSuitIcon(card.getSymbol())+")");});
        System.out.print("}");
    }

    public void startGame(List<Card> cards){
        var list=StartRound(cards);

        cards=list.get(1);
        Player bot=new Player(Score,list.get(2));
        Player player=new Player(Score,list.get(3));
        System.out.println("\uD83C\uDFB0");
        displayCard(player.getCards());
        System.out.println("\uD83E\uDD16");
        System.out.print("("+getCardValue(bot.getCards().get(0).getValue())+","+getSuitIcon(bot.getCards().get(0).getSymbol())+")");


    }
}
