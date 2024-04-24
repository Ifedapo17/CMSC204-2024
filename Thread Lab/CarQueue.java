import java.util.*;

public class CarQueue {
	private static final int DELAY = 1000;
	public Random random = new Random();
	public ArrayDeque<Integer> queue = new ArrayDeque<>();
	
	public CarQueue() {
		for(int i = 0; i < 7; i++) {
			queue.add(random.nextInt(4));
		}
	}
	
	public void addToQueue() {
		class innerClass implements Runnable{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for(int i = 0; i < 5; i++) {
						queue.add(random.nextInt(4));
					}
					Thread.sleep(DELAY);
				}catch(InterruptedException e) {
				}
			}
		}
		
		Runnable run = new innerClass();
		Thread thread = new Thread(run);
		thread.start();
	}
	
	public int deleteQueue() {
		return queue.remove();
	}
}
