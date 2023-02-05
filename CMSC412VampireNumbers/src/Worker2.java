import java.util.Arrays;

// class to check if a number in the range of 100_000 to 999_999 is a vampire number 
// this class is run in the main thread
// increment the count integer if a number is a vampire number
// code to check each number is referenced from https://rosettacode.org/wiki/Vampire_number#Java

public class Worker2 implements Runnable {
	
	private int count;
	
	public void run() {
		countNums();
	}
	
	public int getCount() {
		return count;
	}
	
	
	// run in main thread
	public void countNums() {
		for (long i = 100_000; i < 1_000_000; i++) {
			if (isAVampire(i)) {
				System.out.println(Thread.currentThread().getName() + " found a vampire number: " + i);
				count++;
				}		
		}
	}
	
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
	
	private static int numDigits(long num) {
        return Long.toString(Math.abs(num)).length();
    }

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
