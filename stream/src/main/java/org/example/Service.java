package org.example;

import org.example.domain.Card;
import org.example.domain.Symbol;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Service {

    public List<Card> build(){
        List<Card> cards=new ArrayList<>();
        Arrays.stream(Symbol.values())
                .forEach(symbol->{
                    for (int i = 0; i < 13; i++) {
                        Card card=new Card(i+1,symbol);
                        cards.add(card);
                    }
                });
        return cards;
    }

    public HashMap<Integer,List<Card>> extraire_h_card(List<Card> cards, Integer indice){

        var cardsList=new HashMap<Integer,List<Card>>();

        Card remove = cards.remove(indice.intValue());
        cardsList.put(2,cards);
        cardsList.put(1,List.of(remove));
        return cardsList;
    }

    public HashMap<Integer,List<Card>> draw_a_card(List<Card> cards){
        Integer random=new Random().nextInt(cards.size()-1);
        return extraire_h_card(cards,random);
    }

    public List<Card> mixer_cards(List<Card> cards){

    }
}
