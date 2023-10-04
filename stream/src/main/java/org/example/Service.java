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
        Integer random = new Random().nextInt(cards.size());
        return extract_h_card(cards, random);
    }

    public List<Card> mixer_cards(List<Card> cards) {
        var shuffledCards=new ArrayList<Card>();
        while(!cards.isEmpty()){
            shuffledCards.add(draw_a_card(cards));
        }
        return shuffledCards;
    }

    public HashMap<Integer,List<Card>> splitCard(List<Card> cards){
        int n = new Random().nextInt(20, 34);
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

    public void displayCard(List<Card> cards){
        System.out.println(cards);
    }

    public void displayScore(Integer score){
        System.out.println(
                "\n                        /)/)                             (\\(\\\n" +
                        "                        ( . .)                           (. . )\n" +
                        "                        ( づ               "+score+"                ⊂ )\n\n");
    }

    public void displayAllCards(Player player){
        System.out.println("\uD83C\uDFB0 \n");
        displayCard(player.getCards());
        System.out.println("       Player: ");
        displayScore(player.getScore());
        System.out.println("\uD83E\uDD16 \n");

    }

    public Integer cardValue(Integer value){
        return switch(value) {
            case 11, 12, 13 -> 10;
            default -> value;
        };
    }

    public Integer calculeScore(List<Card> cards){
        final Integer[] score = {0};
        cards.stream()
                .filter(card->card.getValue()!=1)
                .forEach(card->{
                    score[0] +=  cardValue(card.getValue());});
        Long count=cards.stream().filter(card -> card.getValue()==1)
                        .count();
        Integer newCount=Integer.valueOf(count.intValue());
        if(count!=0) {
            if (score[0] + 11 + count - 1 > 21) {
                score[0] += newCount;
            } else {
                score[0] += 11 + newCount;
            }
        }
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
        while((!cards.isEmpty()) && (score<=16) ){
            score=hit(bot,cards);
        }
        return score;
    }

    public void winner(Player player,Player bot,Integer bank){
        Integer oldbank=player.getBank();
        if(player.getScore()> bot.getScore() && player.getScore()<=21 || bot.getScore()>=21 && player.getScore()<=21) {
           player.setBank(oldbank+(bank*2));
            System.out.println(
                    "                              ██╗    ██╗██╗███╗   ██╗███╗   ██╗███████╗██████╗      ⠀⠀⠀⣦⠀⠀⠀⠀⠀⠀⠀⠀  ⢀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀ ⢀⣤⠀⠀⠀   \n" +
                    "                              ██║    ██║██║████╗  ██║████╗  ██║██╔════╝██╔══██╗     ⠀⠀⠀⢿⣷⣄⠀⠀⠀⠀ ⣴⠟⠛⠛⠉⠉⠛⠛⠻⣦⠀⠀⠀⠀⣠⣾⡿⠀⠀⠀  \n" +
                    "                              ██║ █╗ ██║██║██╔██╗ ██║██╔██╗ ██║█████╗  ██████╔╝     ⠀⠀⠀⠸⣿⣿⣦⣄⠀⠀ ⣿⣦⣤⣄⣀⣀⣠⣤⣶⣿⠀⠀⣠⣼⣿⣿⠃⠀⠀⠀ \n" +
                    "                              ██║███╗██║██║██║╚██╗██║██║╚██╗██║██╔══╝  ██╔══██╗     ⠀⢠⣄⡀⠙⢿⣿⣿⣷⡀⢻⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣾⣿⣿⡿⠋⢀⣠⠄\n" +
                    "                              ╚███╔███╔╝██║██║ ╚████║██║ ╚████║███████╗██║  ██║     ⠀⠀⠻⣿⣿⣶⣿⣿⣿⣧⠈⢿⣿⣿⣿⣿⣿⣿⡿⠁⣼⣿⣿⣿⣶⣿⣿⠏⠀⠀ \n" +
                    "                               ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝     ⠀⠀⠀⠈⠻⢿⣿⣿⣿⣿⣧⡈⠻⢿⣿⣿⡿⠟⢀⣾⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀ \n" +
                    "                                                                                    ⠀⠀⠀⠀⣀⣠⣤⣴⣿⣿⣿⡿⠂⢠⣿⣿⡄⠐⢿⣿⣿⣿⣦⣤⣄⡀⠀⠀⠀⠀\n" +
                    "                                                                               ⠀     ⠀⠀⠀⠈⠉⠛⠛⠛⠛⠉⠀⢀⣾⣿⣿⣷⡀⠀⠉⠛⠛⠛⠛⠉⠁⠀⠀⠀⠀\n" +
                    "                                                                                     ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                                                                     ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠻⠟⠛⠛⠉⠀⠀");
        }else if(player.getScore()> bot.getScore() && bot.getScore()<=21 || player.getScore()> bot.getScore() && player.getScore()>=21 && bot.getScore()<=21){
            player.setBank(oldbank-bank);
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
        }else if(player.getScore()== bot.getScore() || (bot.getScore()>=21 && player.getScore()>=21)){
            System.out.println(
                    "                                                              ⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⢀⣠⣾⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                    "                                                             ⠀⠀⠀⠀  ⣾⠿⠿⠿⠶⠾⠿⠿⣿⣿⣿⣿⣿⣿⠿⠿⠶⠶⠿⠿⠿⣷⠀⠀⠀⠀\n"+
                    "                                                              ⠀⠀⠀  ⣸⢿⣆⠀⠀⠀⠀⠀⠀⠀⠙⢿⡿⠉⠀⠀⠀⠀⠀⠀⠀⣸⣿⡆⠀⠀⠀\n"+
                    "                            ████████████╗████╗ ██████████╗        ⢠⡟ ⠀⢻⣆⠀⠀⠀⠀⠀⠀⣾⣧⠀⠀⠀⠀⠀⠀⠀⣰⡟⠀⢻⡄⠀⠀\n"+
                    "                            ╚═══████╔═══╝████║ ████╔═════╝       ⠀⣾⠃ ⠀⠀⢿⡄⠀⠀⠀⠀⢠⣿⣿⡀⠀⠀⠀⠀⢠⡿ ⠀⠀⠘⣷⠀\n"+
                    "                                ████║    ████║ ████║            ⠀⣼⣏⣀⣀⣀⣈⣿⡀⠀⠀⠀⣸⣿⣿⡇⡀⠀⠀⢀⣿⣃⣀⣀⣀⣸⣧⠀\n"+
                    "                                ████║    ████║ ████████╗        ⠀⢻⣿⣿⣿⣿⣿⣿⠃⠀⠀ ⣿⣿⣿⣿⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⡿⠀\n" +
                    "                                ████║    ████║ ████╔═══╝          ⠉⠛⠛⠛⠋⠁⠀⠀⠀⢸⣿⣿⣿⣿⡆⠀⠀⠀⠀⠈⠙⠛⠛⠛⠉⠀⠀\n" +
                    "                                ████║    ████║ ████              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                ████║    ████║ ██████████╗             ⠀⠀⠀⠀ ⣠⣾⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                ╚═══╝    ╚═══╝ ╚═════════╝          ⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "                                                          ⠀⠀⠀⠀⠀⠀     ⠴⠶⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠶⠦⠀⠀⠀⠀⠀⠀\n");
        }
        player.getCards().clear();
        bot.getCards().clear();
    }

    public void startGame(List<Card> cards, Player player, Player bot){
        boolean exit=false;
        var list=StartRound(cards);
        player.setCards(list.get(2));
        bot.setCards(list.get(1));
        Integer bank=0;
        while(!exit){
                        System.out.println("Enter a number that lower or equal to your bank balance :"+player.getBank());
                        bank=scanner.nextInt();
                        if(bank<player.getBank()){
                            exit=true;
                        }else{
                            System.out.println("Please enter a number lower then your bank balance:"+player.getBank());
                        }
        }
        player.setScore(calculeScore(player.getCards()));
        bot.setScore(calculeScore(bot.getCards()));
        System.out.println("\uD83C\uDFB0 \n");
        System.out.println(player.getCards());
        System.out.println("\uD83E\uDD16 \n");
        System.out.println(bot.getCards().get(0));
        displayScore(player.getScore());
        exit = false;


            while (!exit) {

                System.out.println("                         ╔████████████████████████████████╗");
                System.out.println("                         ║██     1:     ♦♦♦♦  hit       ██║");
                System.out.println("                         ║██     2:     ♥♥♥♥  Stand     ██║");
                System.out.println("                         ╚████████████████████████████████╝");
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            if(cards.size()!=0) {
                                player.setScore(hit(player, cards));
                                displayAllCards(player);
                                bot.getCards().get(0);
                            }else {
                                System.out.println("  No more cards to Hit");
                            exit=true;
                            }
                            break;
                        case 2:
                            player.setScore(calculeScore(player.getCards()));
                            displayAllCards(player);
                            Integer botScore = stand(bot, cards);
                            bot.setScore(botScore);
                            displayCard(bot.getCards());
                            System.out.println("bot score: ");
                            displayScore(bot.getScore());
                            exit = true;
                            break;
                    }
                }
            }
        winner(player,bot,bank);
    }

    public void roundLoop(){
           var Listcards=splitCard(mixer_cards(build()));
           boolean exit=false;
           Integer round=0;
           List<Card> cards=Listcards.get(1);
           Player player=new Player();
           player.setBank(Bank);
           Player bot=new Player();
           bot.setBank(Bank);
           while(cards.size()>=4 && exit==false) {
               if(round>0){
                   System.out.println("                         ╔████████████████████████████████╗");
                   System.out.println("                         ║██     1:     ♦♦♦♦  Continue  ██║");
                   System.out.println("                         ║██     2:     ♥♥♥♥  Quit      ██║");
                   System.out.println("                         ╚████████████████████████████████╝");
                   System.out.print("Enter your choice: ");

                   if (scanner.hasNextInt()) {
                       int choice = scanner.nextInt();
                       switch (choice) {
                           case 1:
                               startGame(cards, player, bot);
                               break;
                           case 2:
                               exit = true;
                               break;
                       }
                   }
               }else {
                   startGame(cards, player, bot);
               }
              round++;
           }
    }
}
