package org.example.domain;

import java.util.List;

public class Player {

    private Integer score;
    private Integer bank;
    private List<Card> cards;

    public Player(Integer bank, List<Card> cards) {
        this.bank = bank;
        this.cards = cards;
    }

    public Integer getBank() {
        return bank;
    }

    public Player(Integer score, Integer bank, List<Card> cards) {
        this.score = score;
        this.bank = bank;
        this.cards = cards;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
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

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", bank=" + bank +
                '}';
    }
}
