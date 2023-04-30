import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	Queue<Integer> queue = new LinkedList<>();
	Random rand = new Random();
	
	public CarQueue() {
		queue.add(rand.nextInt(4));
        queue.add(rand.nextInt(4));
        queue.add(rand.nextInt(4));
        queue.add(rand.nextInt(4));
        queue.add(rand.nextInt(4));
	}
	
	public void addToQueue() {
		class CarQueueRunnable implements Runnable{
			@Override
			public void run() {
				try {
					int loop = 0;
					while(loop == 0) {
						queue.add(rand.nextInt(4));
						Thread.sleep(100);
					}
				}catch (InterruptedException E) {
					return;
				}
			}
		}
		Runnable test = new CarQueueRunnable();
		Thread t = new Thread(test);
		t.start();
	}
	
	public int deleteQueue() {
		return queue.remove();
	}
	
}
