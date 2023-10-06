package ru.mpei;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class TripletDequeTest {

    private Deque<Integer> tQueue = new TripletDeque<>();

    @Test
    void removeFirstOccurrence() {
        tQueue.addFirst(1);
        tQueue.addFirst(2);
        tQueue.addFirst(3);
        tQueue.addLast(4);
        tQueue.addLast(4);
        tQueue.addFirst(4);
        tQueue.addFirst(4);
        tQueue.removeFirstOccurrence(3);
        Assertions.assertEquals(4, tQueue.removeFirst());
        tQueue.addFirst(3);
        tQueue.removeFirstOccurrence(4);
        Assertions.assertEquals(3, tQueue.removeFirst());
        Assertions.assertEquals(4, tQueue.removeLast());
    }

}