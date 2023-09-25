package org.example;

import java.util.*;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    static final String SPADES = "♠";
    static final String HEARTS ="♥";
    static final String DIAMONDS = "♦";
    static final String CLUBS = "♣";
    public void menu(){
        System.out.println("_______________________████___█████");
        System.out.println("_____________________██____███____██__███");
        System.out.println("___________________██___███____███████___██");
        System.out.println("_________________█████████████████████████████████");
        System.out.println("______________█████__█__________________________██");
        System.out.println("___________████__██__█___________________________█");
        System.out.println("_________███_██__█__███__________________________█");
        System.out.println("______███_██_███_█_██_██_________█_______________█");
        System.out.println("_____█_██__█_█_████_███__________██______________█");
        System.out.println("___███_█████_██████_███_________███______________█");
        System.out.println("_██__████████_██_██____________█████_____________█");
        System.out.println("█_██__█__████____██___________███████____________█");
        System.out.println("█__██████████____██__________██████████__________█");
        System.out.println("_███_█_██___██___██________█████████████_________█");
        System.out.println("__██_██_█___██___██______█████████████████_______█");
        System.out.println("___██____█___█___██____█████████████████████_____█");
        System.out.println("____██___██__██__██___████████████████████████___█");
        System.out.println("_____██___█__██__██__██████████████████████████__█");
        System.out.println("______██___█__█__██_████████████████████████████_█");
        System.out.println("_______██__██_█__██_████████████████████████████_█");
        System.out.println("________██__█_██_██__███████████████████████████_█");
        System.out.println("_________██__█_█_██__██████████████████████████__█");
        System.out.println("__________██_███_██___█████████__██__█████████___█");
        System.out.println("____________█_██_██_____█████____██____█████_____█");
        System.out.println("_____________█_████___________████████___________█");
        System.out.println("______________█████__________██████████______██__█");
        System.out.println("_______________███____________████████_______███_█");
        System.out.println("________________██__________________________██_█_█");
        System.out.println("_________________██__________________________██__█");
        System.out.println("_________________██__________________________███_█");
        System.out.println("__________________██__████████████████████████████");
        System.out.println("__________________████████████");
        System.out.println(" _____________________██████");

    }
    public static int[][] build(){
//        List<Map<Integer,Integer>> cards=new ArrayList<>();
        int count=0;
        boolean exist=false;
        int type=1;
        System.out.println("Entre the number of the card you want to start with");
        int card= scanner.nextInt();
      while(!exist) {

          System.out.println("                         ╔████████████████████████████████╗");
          System.out.println("                         ║██     1:     ♦♦♦♦  Diamond   ██║");
          System.out.println("                         ║██     2:     ♥♥♥♥  Heart     ██║");
          System.out.println("                         ║██     3:     ♠♠♠♠  Spades    ██║");
          System.out.println("                         ║██     4:     ♣♣♣♣  Clubs     ██║");
          System.out.println("                         ╚████████████████████████████████╝");
          System.out.print("Enter your choice: ");

          if (scanner.hasNextInt()) {
              int choice = scanner.nextInt();

              switch (choice) {
                  case 1:
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
        return switch (suit) {
            case 1 -> DIAMONDS;
            case 2 -> HEARTS;
            case 3 -> SPADES;
            case 4 -> CLUBS;
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

    public static List<int[][]> extract_h_card(int indice,int[][] cards){
        List<int[][]> shuffle=new ArrayList<>();

        int[][] newCard=new int[cards.length-1][2];
        int count=0;
        for(int i=0;i<= cards.length-1;i++){
            if(i!=indice){
                newCard[count]=cards[i];
                count++;
            }
        }
        shuffle.add(new int[][] { cards[indice] });
        shuffle.add(newCard);
        return shuffle;
    }

    public static List<int[][]> draw_a_card(int[][] cards){
         int rand=new Random().nextInt(cards.length);
         return extract_h_card(rand,cards);
    }

    public static int[][] mixer_cards(int[][] cards){
        int[][] shuffledCards=new int[cards.length][2];
        int count=0;
        List<int[][]> listCard;
        while (cards.length != 0) {
            listCard = draw_a_card(cards);
            cards = listCard.get(1);
            shuffledCards[count] = listCard.get(0)[0];
            count++;
        }
        return shuffledCards;
    }

    public static List<int[][]> pioche(int[][] cards){
        int number =new Random().nextInt(cards.length/3, cards.length/2);
        List<int[][]> shuffle=new ArrayList<>();

        int[][] newCard=new int[cards.length-1][2];
        int[][] half=new int[cards.length-1][2];

        int count=0;
        int count1=0;
        for(int i=0;i<= cards.length-1;i++){
            if(i>number){
                newCard[count]=cards[i];
                count++;
            }else{
                half[count1]=cards[i];
                count1++;
            }
        }
        shuffle.add(newCard);
        shuffle.add(half);
        return shuffle;
    }

    public static List<int[][]> startPlay(int[][] cards){
        int[][] botCard = Arrays.copyOfRange(cards, 0, 2);
        int[][] playerCard = Arrays.copyOfRange(cards, 2, 4);
        int[][] newCards = Arrays.copyOfRange(cards, 4, cards.length);

        List<int[][]> listCards = new ArrayList<>();
        listCards.add(playerCard);
        listCards.add(botCard);
        listCards.add(newCards);

        return listCards;
    }

    public void round(int[][]cards){
        List<int[][]> startRound=startPlay(cards);
        boolean stand=false;
        int[][] botCards=startRound.get(0);
        int[][] playerCards=startRound.get(1);
        int[][] newCards=startRound.get(2);
        System.out.println("Player cards");
        System.out.print("{");
        display(playerCards);
        System.out.println("dealer first card");
        System.out.print("{");
            System.out.print("(");
            System.out.print(getSuitIcon(botCards[0][1]) + "," + getCardValue(botCards[0][0]));
            System.out.print(")");
        System.out.print("}\n");
        while (stand){

            System.out.println("                         ╔████████████████████████████████╗");
            System.out.println("                         ║██     1:     ♦♦♦♦  hit       ██║");
            System.out.println("                         ║██     2:     ♥♥♥♥  Stand     ██║");
            System.out.println("                         ╚████████████████████████████████╝");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                       startRound= extract_h_card(0,newCards);

                }
            }
        }
    }
    public static void display(int[][] cards){
        System.out.print("{");
        for (int[] ints : cards) {
            System.out.print("(");
            System.out.print(getSuitIcon(ints[1]) + "," + getCardValue(ints[0]));
            System.out.print("),");
        }
        System.out.print("}\n");

    }

    public static void main(String[] args) {

        //display();

    }
}