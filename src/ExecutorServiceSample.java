import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceSample {

	public static void main(String args[]) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		Future<Integer> future = executorService.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				if (duration > 2000) {
					throw new IOException("Sleep for a long time .. ");
				}
				System.out.println("Starting thread ....");
				Thread.sleep(duration);
				System.out.println("Finished Thread ........");

				return duration;
			}

		});

		executorService.shutdown();

		try {
			System.out.println("Result is .... " + future.get());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} catch (ExecutionException e) {
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}
	}
}
