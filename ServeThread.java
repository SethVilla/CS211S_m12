import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;


class ServeThread extends Thread {
    private final BlockingQueue<Food> blockingQueue;

    private final AtomicBoolean  isFinishedCooking;

    public ServeThread(BlockingQueue<Food> blockingQueue, AtomicBoolean isFinishedCooking) {
        this.blockingQueue = blockingQueue;
        this.isFinishedCooking = isFinishedCooking;
    }

    @Override
    public void run() {
        try {
            Food food;
            while (!(blockingQueue.isEmpty() && isFinishedCooking.get())) {
                food = blockingQueue.take();
                System.out.println("SERVER READY");
                System.out.println("SERVER STARTING: " + food);
                sleep(food.getServeTime() * 1000L);
                System.out.println("SERVER ENDING: " + food);

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}