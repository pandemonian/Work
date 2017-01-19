package Lesson_8.HomeWork;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Gubanov Pavel on 19.01.17.
 */
public class Synchro {
    private final ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean isIncrementThreadCurrent;

    ReentrantLock getLock() {
        return lock;
    }

    Condition getCondition() {
        return condition;
    }

    boolean isIncrementThreadCurrent() {
        return isIncrementThreadCurrent;
    }

    void setIncrementThreadCurrent(boolean incrementThreadCurrent) {
        isIncrementThreadCurrent = incrementThreadCurrent;
    }
}
