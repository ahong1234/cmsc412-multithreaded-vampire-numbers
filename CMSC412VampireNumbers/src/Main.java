
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// create workers
		Worker worker1 = new Worker(true);
		Worker worker2 = new Worker(false);
		
		// create threads
		Thread lows = new Thread(worker1);
		Thread highs = new Thread(worker2);
		
		// set start time
		//long start= System.currentTimeMillis();
		
		// set thread names
		lows.setName("First Worker");
		highs.setName("Second Worker");
		
		// begin threads
		lows.start();
		highs.start();	
		
		// wait for threads to finish
		lows.join();
		highs.join();
		
	
		
		System.out.println();
		System.out.println("Vampire numbers found: " + (worker1.getCount() + worker2.getCount()));		
		System.out.println("First Worker found: " + worker1.getCount());
		System.out.println("Second Worker found: " + worker2.getCount() + "\n");
		

		
	}

}
