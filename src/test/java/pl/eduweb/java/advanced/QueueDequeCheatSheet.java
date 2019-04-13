package pl.eduweb.java.advanced;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueDequeCheatSheet {

    @Test
    void createAndUseQueue() {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(5);
        queue.add(10);
        queue.add(15);

        System.out.println(queue.peek()); // get but without removing

        System.out.println(queue.poll()); // get and remove
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        queue.add(1);
        System.out.println(queue.element()); // get without removing but throw when empty
        System.out.println(queue.remove()); // get and remove but throw when empty
    }


    @Test
    void addOnCapacityRestrictedQueue() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(1);

        System.out.println(queue.add(5));
        Assertions.assertThrows(IllegalStateException.class, () -> {
            System.out.println(queue.add(5));
        });
    }

    @Test
    void offerAndPollOnCapacityRestrictedQueue() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(1);

        System.out.println(queue.offer(5));
        System.out.println(queue.offer(5));

        System.out.println(queue.poll());

        System.out.println(queue.offer(5));
    }

    @Test
    void stackEffectUsingDeque() {
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(1);
        deque.addLast(2);

        System.out.println(deque);

        System.out.println(deque.pollLast());
        System.out.println(deque.pollLast());
    }
}
