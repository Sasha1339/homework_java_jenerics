package ru.mpei;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        TripletDeque<Integer> tripletDeque = new TripletDeque<>();

        tripletDeque.addFirst(4);
        tripletDeque.addFirst(5);

        tripletDeque.addFirst(4);
        tripletDeque.addFirst(5);
        tripletDeque.addFirst(4);

        tripletDeque.removeFirstOccurrence(5);




        Iterator iterator = tripletDeque.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }





    }
}