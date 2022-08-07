package ratelimitter;

public class RateLimitterFactory {
	public RateLimitter getRateLimitter(RateLimitterAlgorithm algorithm) {
		if(algorithm == RateLimitterAlgorithm.TokenBucketAlgorithm)
			return TokenBucketRateLimitter.getTokenBucketRateLimitterInstance();
		else if(algorithm == RateLimitterAlgorithm.SlidingWindowCounterAlgorithm)
			return SlidingWindowCounterRateLimitter.getInstance();
		return null;
	}
}
