import java.util.Random;

public class InterruptedThread {
	public static void main(String args[]) throws InterruptedException {
		Thread th =  new Thread(new Runnable() {
			public void run() {
				System.out.println("Starting thread ....");
				
				Random random =  new Random();
				for(int i = 0; i<1E8; i++) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted");
						break;
					}
					Math.sin(random.nextDouble());
				}
				
				
			}
		});
		
		th.start();
		
		th.sleep(500);
		
		th.interrupt();
		
		th.join();
		
		System.out.println("Finished thread ...");
	}
}
