package ratelimitter;

public class RateLimitterFactory {
	public RateLimitter getRateLimitter(RateLimitterAlgorithm algorithm) {
		if(algorithm == RateLimitterAlgorithm.TokenBucketAlgorithm) {
			return TokenBucketRateLimitter.getTokenBucketRateLimitterInstance();
		}
		return null;
	}
}
