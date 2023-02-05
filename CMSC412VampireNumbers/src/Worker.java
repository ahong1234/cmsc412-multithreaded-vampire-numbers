import java.util.Arrays;

// class to check if a number in the range of 100_000 to 999_999 is a vampire number
// increment the count integer if a number is a vampire number
// when constructed, a boolean is passed in to determine whether the code 
// should scan numbers in the range of 100_000 to 550_000 (low) or 
// 550_000 to 999_999 (high)
// code to check each number is referenced from https://rosettacode.org/wiki/Vampire_number#Java

public class Worker implements Runnable {
	
	
	private int count; // the number of vampire numbers found by this worker
	private boolean low; // specify which range of numbers to scan
	
	// constructor to determine which range to scan - low or high
	public Worker(boolean low) {
		this.low = low;
	}
		
	public void run() {
		if (low) {
			countLows();			
		}
		else {
			countHighs();			
		}
		
	}
	
	// return the amount of vampire numbers found by this worker
	public int getCount() {
		return this.count;
	}
	
	// check the lower range of 100_000 to 550_000
	public void countLows() {
		for (long i = 100_000; i < 550_000; i++) {
			if (isAVampire(i)) {
				System.out.println(Thread.currentThread().getName() + " found a vampire number: " + i);						
				count++;
				}		
		}	
	}
	
	// check the higher range of 550_000 to 999_999
	public void countHighs() {
		for (long i = 550_000; i < 1_000_000; i++) {
				if (isAVampire(i)) {
				System.out.println(Thread.currentThread().getName() + " found a vampire number: " + i);
				count++;
				}
		}
	}
	
	// this method takes input of a number and checks if it is a vampire number
	// the code is referenced from https://rosettacode.org/wiki/Vampire_number#Java
	public boolean isAVampire(long n) {
		long i = n;
        if((numDigits(i) % 2) != 0) {i = i * 10 - 1;}
        for(long fang1 = 2; fang1 <= Math.sqrt(i) + 1; fang1++) {
            if(i % fang1 == 0){
                long fang2 = i / fang1;
                if(fangCheck(i, fang1, fang2) && fang1 <= fang2) {
                	
                    return true;
                }       
            }
        }
        return false;
	}
	
	// this code is referenced from https://rosettacode.org/wiki/Vampire_number#Java
	private static int numDigits(long num) {
        return Long.toString(Math.abs(num)).length();
    }
	
	// this code is referenced from https://rosettacode.org/wiki/Vampire_number#Java
    private static boolean fangCheck(long orig, long fang1, long fang2){
        if(Long.toString(fang1).endsWith("0") && Long.toString(fang2).endsWith("0")) return false;
        int origLen = numDigits(orig);
        if(numDigits(fang1) != origLen / 2 || numDigits(fang2) != origLen / 2) return false;
        byte[] origBytes = Long.toString(orig).getBytes();
        byte[] fangBytes = (Long.toString(fang1) + Long.toString(fang2)).getBytes();
        Arrays.sort(origBytes);
        Arrays.sort(fangBytes);
        return Arrays.equals(origBytes, fangBytes);
    }
}
