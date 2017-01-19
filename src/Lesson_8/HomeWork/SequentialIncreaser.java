package Lesson_8.HomeWork;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Gubanov Pavel on 25.12.16.
 */
public class SequentialIncreaser implements Runnable {
    private Synchro synchro;
    private ReentrantLock lock;
    private Condition condition;
    private final Card card;

    SequentialIncreaser(Synchro synchro, Card card) {
        this.synchro = synchro;
        this.lock = synchro.getLock();
        this.condition = synchro.getCondition();
        this.card = card;
    }

    @Override
    public void run() {

    }
}
