package ratelimitter;

public interface RateLimitterAlgorithm {
	public boolean shallRateLimit(int token);
}
