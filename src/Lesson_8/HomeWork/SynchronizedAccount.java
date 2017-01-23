package Lesson_8.HomeWork;

/*
 *  Общий для классов инкремента и декримента - декоратор
 */
class SynchronizedAccount implements DecoratorInterface, Runnable{
    private DecoratorInterface component;
    private final Object lock;

    SynchronizedAccount(DecoratorInterface component, Object lock) {
        this.component = component;
        this.lock = lock;
    }

    @Override
    public void go() {
        synchronized (lock) {
            component.go();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            go();
        }
    }
}
