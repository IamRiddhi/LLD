package ratelimitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

	public static void main(String[] args) {
		RateLimitterService.registerService(1, new TokenBucketAlgorithm(0, 3));
		
		ExecutorService executors = Executors.newFixedThreadPool(10);
		
		for(int i = 0 ; i < 10 ; i ++) {
			executors.execute(() -> RateLimitterService.accessService(1, 2));
		}
		
		executors.shutdown();
	}

}
