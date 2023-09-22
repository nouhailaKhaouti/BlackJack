package org.example;

import java.util.*;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    static final String SPADES = "♠";
    static final String HEARTS ="♥";
    static final String DIAMONDS = "♦";
    static final String CLUBS = "♣";
    public void menu(){
        System.out.println("_______________________¶¶¶¶___¶¶¶¶¶");
        System.out.println("_____________________¶¶____¶¶¶____¶¶__¶¶¶");
        System.out.println("___________________¶¶___¶¶¶____¶¶¶¶¶¶¶___¶¶");
        System.out.println("_________________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
        System.out.println("______________¶¶¶¶¶__¶__________________________¶¶");
        System.out.println("___________¶¶¶¶__¶¶__¶___________________________¶");
        System.out.println("_________¶¶¶_¶¶__¶__¶¶¶__________________________¶");
        System.out.println("______¶¶¶_¶¶_¶¶¶_¶_¶¶_¶¶_________¶_______________¶");
        System.out.println("_____¶_¶¶__¶_¶_¶¶¶¶_¶¶¶__________¶¶______________¶");
        System.out.println("___¶¶¶_¶¶¶¶¶_¶¶¶¶¶¶_¶¶¶_________¶¶¶______________¶");
        System.out.println("_¶¶__¶¶¶¶¶¶¶¶_¶¶_¶¶____________¶¶¶¶¶_____________¶");
        System.out.println("¶_¶¶__¶__¶¶¶¶____¶¶___________¶¶¶¶¶¶¶____________¶");
        System.out.println("¶__¶¶¶¶¶¶¶¶¶¶____¶¶__________¶¶¶¶¶¶¶¶¶¶__________¶");
        System.out.println("_¶¶¶_¶_¶¶___¶¶___¶¶________¶¶¶¶¶¶¶¶¶¶¶¶¶_________¶");
        System.out.println("__¶¶_¶¶_¶___¶¶___¶¶______¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_______¶");
        System.out.println("___¶¶____¶___¶___¶¶____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_____¶");
        System.out.println("____¶¶___¶¶__¶¶__¶¶___¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶___¶");
        System.out.println("_____¶¶___¶__¶¶__¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶__¶");
        System.out.println("______¶¶___¶__¶__¶¶_¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_¶");
        System.out.println("_______¶¶__¶¶_¶__¶¶_¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_¶");
        System.out.println("________¶¶__¶_¶¶_¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_¶");
        System.out.println("_________¶¶__¶_¶_¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶__¶");
        System.out.println("__________¶¶_¶¶¶_¶¶___¶¶¶¶¶¶¶¶¶__¶¶__¶¶¶¶¶¶¶¶¶___¶");
        System.out.println("____________¶_¶¶_¶¶_____¶¶¶¶¶____¶¶____¶¶¶¶¶_____¶");
        System.out.println("_____________¶_¶¶¶¶___________¶¶¶¶¶¶¶¶___________¶");
        System.out.println("______________¶¶¶¶¶__________¶¶¶¶¶¶¶¶¶¶______¶¶__¶");
        System.out.println("_______________¶¶¶____________¶¶¶¶¶¶¶¶_______¶¶¶_¶");
        System.out.println("________________¶¶__________________________¶¶_¶_¶");
        System.out.println("_________________¶¶__________________________¶¶__¶");
        System.out.println("_________________¶¶__________________________¶¶¶_¶");
        System.out.println("__________________¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
        System.out.println("__________________¶¶¶¶¶¶¶¶¶¶¶¶");
        System.out.println(" _____________________¶¶¶¶¶¶");

    }
    public static int[][] build(){
//        List<Map<Integer,Integer>> cards=new ArrayList<>();
        int count=0;
        Boolean exist=false;
        Integer type=1;
        System.out.println("Entre the number of the card you want to start with");
        Integer card= scanner.nextInt();
      while(!exist) {

          System.out.println("                         ╔████████████████████████████████╗");
          System.out.println("                         ║██     1:     ♦♦♦♦  Diams     ██║");
          System.out.println("                         ║██     2:     ♥♥♥♥  Heart     ██║");
          System.out.println("                         ║██     3:     ♠♠♠♠  Spades    ██║");
          System.out.println("                         ║██     4:     ♣♣♣♣  Clubs     ██║");
          System.out.println("                         ╚████████████████████████████████╝");
          System.out.print("Enter your choice: ");

          if (scanner.hasNextInt()) {
              int choice = scanner.nextInt();

              switch (choice) {
                  case 1:
                      type = 1;
                      exist = true;
                      break;
                  case 2:
                      type = 2;
                      exist = true;
                      break;
                  case 3:
                      type = 3;
                      exist = true;
                      break;
                  case 4:
                      type = 4;
                      exist = true;
                      break;
                  default:
                      System.out.println("Invalid choice. Please try again.\n");
              }
          }
      }
      int size=(13-card+1)+(4-type)*13;
      int[][] cardsA=new int[size][2];
      for(int i=type;i<=4;i++){
          for(int j=card;j<=13;j++){
             cardsA[count][0]=j;
             cardsA[count][1]=i;
             count++;
          }
          card=1;
      }

        //System.out.println("hashmap methode");
//      for(int i=type;i<=4;i++){
//          for(int j=(type==i?card:1);j<=13;j++){
//              Map<Integer,Integer> newCard=new HashMap<>() ;
//              newCard.put(j,i);
//              cards.add(newCard);
//          }
//      }
        return cardsA;
    }

    public static String getSuitIcon(int suit){
        String newSuit="";
        switch (suit){
            case 1:  newSuit=DIAMONDS; break;
            case 2: newSuit= HEARTS; break;
            case 3: newSuit= SPADES; break;
            case 4: newSuit= CLUBS; break;
        }
        return newSuit;
    }


    public static String getCardValue(int cardNumber){
        String cardIcon="";
        switch (cardNumber) {
            case 1:
                cardIcon = "A";
                break;
            case 11:
                cardIcon = "J";
                break;
            case 12:
                cardIcon = "Q";
                break;
            case 13:
                cardIcon = "K";
                break;
            default:
                cardIcon = String.valueOf(cardNumber);
                break;
        }
        return cardIcon;
    }

    public static List<int[][]> extract_h_card(int indice,int[][] cards){
        List<int[][]> shufle=new ArrayList<>();

        int[][] newCard=new int[cards.length-1][2];
        int count=0;
        for(int i=0;i<= cards.length-1;i++){
            if(i!=indice){
                newCard[count]=cards[i];
                count++;
            }
        }
        shufle.add(new int[][] { cards[indice] });
        shufle.add(newCard);
        return shufle;
    }

    public static List<int[][]> draw_a_card(int[][] cards){
         int rand=new Random().nextInt(cards.length);
         return extract_h_card(rand,cards);
    }

    public static int[][] mixer_cards(int[][] cards){
        int[][] shufledCards=new int[cards.length][2];
        int count=0;
        List<int[][]> listCard;
        while(true){
            if(cards.length==0){
                break;
            }
            listCard=draw_a_card(cards);
            cards=listCard.get(1);
            shufledCards[count]=listCard.get(0)[0];
            count++;
        }
        return shufledCards;
    }

    public static List<int[][]> pioche(int[][] cards){
        int number =new Random().nextInt(cards.length/3, cards.length/2);
        List<int[][]> shufle=new ArrayList<>();

        int[][] newCard=new int[cards.length-1][2];
        int[][] half=new int[cards.length-1][2];

        int count=0;
        int count1=0;
        for(int i=0;i<= cards.length-1;i++){
            if(i>number){
                newCard[count]=cards[i];
                count++;
            }else{
                half[count]=cards[i];
                count++;
            }
        }
        shufle.add(newCard);
        shufle.add(half);
        return shufle;
    }

    public static
    public static void display(){
        int[][] cards=build();
        int[][] display=mixer_cards(cards);
        System.out.printf("{");
        for(int i=0;i<display.length;i++){
            System.out.printf("(");
            System.out.print(getSuitIcon(display[i][1])+","+getCardValue(display[i][0]));
            System.out.printf("),");
        }
        System.out.printf("}");

    }

    public static void main(String[] args) {

        display();

    }
}