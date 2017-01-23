package Lesson_8.HomeWork;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Класс используемый для синхронизации средства пакета "java.util.concurrent.locks"
 */

class Synchro {
    private final ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile boolean isIncrementThreadCurrent;

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
