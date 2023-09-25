package org.example;


import org.example.domain.Card;

import java.util.HashMap;
import java.util.List;

public class Main {
   static Service service=new Service();
    public static void main(String[] args) {
        List<Card> build = service.build();
        build.stream()
                .forEach(System.out::println);

        HashMap<Integer, List<Card>> extraireHCard = service.extraire_h_card(build, 1);

        System.out.println();
    }
}