package org.example.domain;

import java.util.List;

public class Player {

    private Integer score;
    private List<Card> cards;

    public Player(Integer score, List<Card> cards) {
        this.score = score;
        this.cards = cards;
    }

    public Player() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
