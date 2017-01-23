package Lesson_8.HomeWork;

/**
 * Created by Gubanov Pavel on 23.01.17.
 */
class SafeDeacreaserDecorator implements DeacreaserInterface, Runnable {
    private DeacreaserInterface component;
    private final Object lock;

    SafeDeacreaserDecorator(DeacreaserInterface component, Object lock) {
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
        go();
    }
}
