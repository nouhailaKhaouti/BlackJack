package org.example;

import org.example.domain.Card;
import org.example.domain.Player;
import org.example.domain.Symbol;

import java.util.*;

public class Service {
    static Scanner scanner=new Scanner(System.in);
    static Integer Bank=1000;
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

    public Card extract_h_card(List<Card> cards, Integer indice) {
        Card remove = cards.remove(indice.intValue());
        return remove;
    }

    public Card draw_a_card(List<Card> cards) {
        Integer random = new Random().nextInt(cards.size() - 1);
        return extract_h_card(cards, random);
    }

    public List<Card> mixer_cards(List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>();
        while(cards.isEmpty()) {
            shuffledCards.add(draw_a_card(cards));
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
        list.put(1,botCards);
        list.put(2,playerCards);
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

    public void displayAllCards(Player player){
        System.out.println("\uD83C\uDFB0");
        displayCard(player.getCards());
        System.out.println("\uD83E\uDD16");
    }

    public Integer calculeScore(List<Card> cards){
        final Integer[] score = {0};
        cards.stream()
                .filter(card->card.getValue()!=1)
                .forEach(card->{
                    score[0] +=  card.getValue();});
        cards.stream()
                .filter(card -> card.getValue()==1)
                .forEach(card -> {
                    if(score[0]<10){
                        score[0]+=11;
                    }else{
                        score[0]+=1;
                    }
                });
        return score[0];
    }

    public Integer hit(Player player,List<Card> cards){
        var hit=extract_h_card(cards,0);
        player.getCards().add(hit);
        Integer score=calculeScore(player.getCards());
        return score;
    }

    public Integer stand(Player bot,List<Card> cards){
        Integer score=0;
        while(!cards.isEmpty()&& score>16 ){
            score=hit(bot,cards);
        }
        return score;
    }

    public void winner(Player player,Player bot){
        if(player.getScore()> bot.getScore()&&player.getScore()<=21) {
            System.out.println(
                    "                              ██╗    ██╗██╗███╗   ██╗███╗   ██╗███████╗██████╗      ⠀⠀⠀⣦⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀ ⢀⣤⠀⠀⠀   \n" +
                    "                              ██║    ██║██║████╗  ██║████╗  ██║██╔════╝██╔══██╗     ⠀⠀⠀⢿⣷⣄⠀⠀⠀⠀⣴⠟⠛⠛⠉⠉⠛⠛⠻⣦⠀⠀⠀⠀⣠⣾⡿⠀⠀⠀  \n" +
                    "                              ██║ █╗ ██║██║██╔██╗ ██║██╔██╗ ██║█████╗  ██████╔╝     ⠀⠀⠀⠸⣿⣿⣦⣄⠀⠀⣿⣦⣤⣄⣀⣀⣠⣤⣶⣿⠀⠀⣠⣼⣿⣿⠃⠀⠀⠀ \n" +
                    "                              ██║███╗██║██║██║╚██╗██║██║╚██╗██║██╔══╝  ██╔══██╗     ⠀⢠⣄⡀⠙⢿⣿⣿⣷⡀⢻⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣾⣿⣿⡿⠋⢀⣠⠄\n" +
                    "                              ╚███╔███╔╝██║██║ ╚████║██║ ╚████║███████╗██║  ██║     ⠀⠀⠻⣿⣿⣶⣿⣿⣿⣧⠈⢿⣿⣿⣿⣿⣿⣿⡿⠁⣼⣿⣿⣿⣶⣿⣿⠏⠀⠀ \n" +
                    "                               ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝     ⠀⠀⠀⠈⠻⢿⣿⣿⣿⣿⣧⡈⠻⢿⣿⣿⡿⠟⢀⣾⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀ \n" +
                    "                                                                                    ⠀⠀⠀⠀⣀⣠⣤⣴⣿⣿⣿⡿⠂⢠⣿⣿⡄⠐⢿⣿⣿⣿⣦⣤⣄⡀⠀⠀⠀⠀\n" +
                    "                                                                               ⠀     ⠀⠀⠀⠈⠉⠛⠛⠛⠛⠉⠀⢀⣾⣿⣿⣷⡀⠀⠉⠛⠛⠛⠛⠉⠁⠀⠀⠀⠀\n" +
                    "                                                                                     ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                                                                     ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠻⠟⠛⠛⠉⠀⠀");
        }
        if(player.getScore()< bot.getScore()&& bot.getScore()<=21){
            System.out.println(
                    "                                                                             ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⡀⠀⠀⠀⣀⣀⡀⠀⠀⠀⠀⠀\n" +
                    "                                                                             ⠀⠀⠀⠀⠀⠀⡠⠊⠁⠀⠀⠀⠀⠙⢆⠉⠀⠀⠈⠳⡀⠀⠀⠀\n" +
                    "                                                                            ⠀⠀⠀⠀⠀⡰⠁⠀⠒⠉⠁⠀⣈⣉⣺⣖⠒⠒⠒⢒⣓⡤⢄⠀\n" +
                    "                                                                            ⠀⠀⠀⡠⢲⠁⠀⠀⠀⣠⠖⠁⢀⣀⣨⣝⣗⢈⣉⣠⣤⣸⣝⣇\n" +
                    "                              ██╗      ██████╗ ███████╗███████╗██████╗      ⠀⢀⠎⠀⠈⠀⠀⢠⢾⣗⣂⣩⣿⣿⣻⣆⣼⣁⡀⣼⣿⣯⢮⠏\n" +
                    "                              ██║     ██╔═══██╗██╔════╝██╔════╝██╔══██╗     ⢀⠎⠀⠀⠀⠀⠀⣸⡾⠀⠈⠉⠉⢚⡿⠉⠁⠤⣀⣀⡀⡴⠋⠀\n"+
                    "                              ██║     ██║   ██║███████╗█████╗  ██████╔╝     ⢸⠀⠀⠀⠀⠀⠀⠙⠁⠀⠀⠐⠉⠁⠀⠀⠀⠀⠈⠀⠀⠈⢆⠀\n" +
                    "                              ██║     ██║   ██║╚════██║██╔══╝  ██╔══██╗     ⠸⡀⠀⠀⠀⠀⠀⢠⠚⣗⡢⢄⠤⠤⡔⠤⡀⠀⢀⣀⣀⡠⢚⠆\n" +
                    "                              ███████╗╚██████╔╝███████║███████╗██║  ██║      ⠱⣄⠀⠀⠀⠀⠈⠒⠒⠒⠋⠤⠒⠛⠥⢈⣉⠠⣤⠬⠝⠁⠀\n" +
                    "                              ╚══════╝ ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝       ⠀⠀⠈⠑⠢⠄⣀⣀⠀........⠀⣀⡤⠊⠁⠀⠀⠀⠀\n");
        }
        if(player.getScore()== bot.getScore()){
            System.out.println(
                    "                                                              ⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⢀⣠⣾⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                    "                                                             ⠀⠀⠀⠀  ⣾⠿⠿⠿⠶⠾⠿⠿⣿⣿⣿⣿⣿⣿⠿⠿⠶⠶⠿⠿⠿⣷⠀⠀⠀⠀\n"+
                    "                                                              ⠀⠀⠀  ⣸⢿⣆⠀⠀⠀⠀⠀⠀⠀⠙⢿⡿⠉⠀⠀⠀⠀⠀⠀⠀⣸⣿⡆⠀⠀⠀\n"+
                    "                            ████████████╗████╗██████████╗        ⢠⡟ ⠀⢻⣆⠀⠀⠀⠀⠀⠀⠀⣾⣧⠀⠀⠀⠀⠀⠀⠀⣰⡟⠀⢻⡄⠀⠀\n"+
                    "                            ╚═══████╔═══╝████║████╔═════╝       ⠀⣾⠃ ⠀⠀⢿⡄⠀⠀⠀⠀⠀⢠⣿⣿⡀⠀⠀⠀⠀⢠⡿ ⠀⠀⠘⣷⠀\n"+
                    "                                ████║    ████║████║            ⠀⣼⣏⣀⣀⣀⣈⣿⡀⠀⠀⠀⠀⣸⣿⣿⡇⠀⠀⠀⢀⣿⣃⣀⣀⣀⣸⣧⠀\n"+
                    "                                ████║    ████║████████╗        ⠀⢻⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⣿⣿⣿⣿⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⡿⠀\n" +
                    "                                ████║    ████║████╔═══╝          ⠉⠛⠛⠛⠋⠁⠀⠀⠀⠀⢸⣿⣿⣿⣿⡆⠀⠀⠀⠀⠈⠙⠛⠛⠛⠉⠀⠀\n" +
                    "                                ████║    ████║████              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                ████║    ████║██████████╗             ⠀⠀⠀⠀ ⠀⣠⣾⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                ╚═══╝    ╚═══╝╚═════════╝          ⠀⠀⠀⠀⠀⠀⠀ ⣸⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                                          ⠀⠀⠀⠀⠀⠀     ⠴⠶⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠶⠦⠀⠀⠀⠀⠀⠀\n");
        }
    }
    public void startGame(List<Card> cards){
        var list=StartRound(cards);
        boolean exit=false;
        Player bot=new Player(Bank,list.get(2));
        Player player=new Player(Bank,list.get(3));
        System.out.println("\uD83C\uDFB0");
        displayCard(player.getCards());
        System.out.println("\uD83E\uDD16");
        System.out.print("("+getCardValue(bot.getCards().get(0).getValue())+","+getSuitIcon(bot.getCards().get(0).getSymbol())+")");
        while(exit) {

            System.out.println("                         ╔████████████████████████████████╗");
            System.out.println("                         ║██     1:     ♦♦♦♦  hit       ██║");
            System.out.println("                         ║██     2:     ♥♥♥♥  Stand     ██║");
            System.out.println("                         ╚████████████████████████████████╝");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        player.setScore(hit(player, cards));
                        displayAllCards(player);
                        System.out.print("("+getCardValue(bot.getCards().get(0).getValue())+","+getSuitIcon(bot.getCards().get(0).getSymbol())+")");
                        break;
                    case 2:
                        displayAllCards(player);
                        bot.setScore(stand(bot, cards));
                        displayCard(bot.getCards());
                        exit = true;
                        break;
                }
            }
        }
        winner(player,bot);

    }

    public void roundLoop(){

    }
}
