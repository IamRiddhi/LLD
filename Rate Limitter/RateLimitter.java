package ratelimitter;

public interface RateLimitter {
	public boolean allowAccess(String apiKey , String serviceId , int tokens);
}
