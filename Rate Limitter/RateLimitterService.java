package ratelimitter;

import java.util.HashMap;
import java.util.Map;

public class RateLimitterService {
	
	private static Map<Integer , RateLimitterAlgorithm> cache; // rate limitting per service.
	
	static {
		cache = new HashMap<>();
	}
	
	public static void registerService(int serviceId , RateLimitterAlgorithm algorithm) {
		cache.put(serviceId, algorithm);
	}
	
	public static void accessService(int serviceId , int token) {
		if(cache.get(serviceId).shallRateLimit(token)) {
			System.out.println("Thread = " + Thread.currentThread() + " , Service throttled.");
		}
		else {
			System.out.println("Thread = " + Thread.currentThread() + " , Service accessed successfully.");
		}
	}
}
