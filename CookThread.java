import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
class CookThread extends Thread {
    private final List<Food> foodList;
    private final BlockingQueue<Food> blockingQueue;
    private final AtomicBoolean isFinishedCooking;

    public CookThread(List<Food> foodList, BlockingQueue<Food> blockingQueue, AtomicBoolean isFinishedCooking) {
        this.foodList = foodList;
        this.blockingQueue = blockingQueue;
        this.isFinishedCooking = isFinishedCooking;
    }

    @Override
    public void run() {
        try {
            for (Food food : foodList) {
                System.out.println("COOK READY");
                System.out.println("COOK STARTING: " + food);
                sleep(food.getCookTime() * 1000L);
                System.out.println("COOK ENDING: " + food);

                while (!blockingQueue.offer(food)) {
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        isFinishedCooking.set(true);
    }
}
