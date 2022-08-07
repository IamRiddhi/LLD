package ratelimitter;

public class Driver {

	public static void main(String[] args) {
		
		RateLimitterService rateLimitterService = new RateLimitterService();
		for(int i = 1 ; i <= 2 ; i ++)
			rateLimitterService.allowAccess("123", "Service1", 3);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rateLimitterService.allowAccess("123", "Service1", 3);
	}

}
