package ratelimitter;

public class TokenBucketAlgorithm implements RateLimitterAlgorithm {

	private int bucketCapacity;
	private int refereshRate; 
	private int currentCapacity;
	private long lastUpdatedTimestamp;
	
	public TokenBucketAlgorithm(int refereshRate, int bucketCapacity) {
		this.refereshRate = refereshRate;
		this.bucketCapacity = bucketCapacity;
		this.currentCapacity = bucketCapacity;
		this.lastUpdatedTimestamp = System.currentTimeMillis();
	}

	public synchronized boolean shallRateLimit(int tokens) {
		this.refill();
		if(currentCapacity - tokens > 0)
		{
			currentCapacity -= tokens;
			System.out.println("Thread = " + Thread.currentThread() + " , capacity = " + currentCapacity);
			return false;
		}
		return true;
	}
	
	private void refill() {
		long now = System.currentTimeMillis();
		int additionalTokens = (int)((now - lastUpdatedTimestamp) / 1000) * refereshRate;
		this.currentCapacity = Math.min(this.bucketCapacity, this.currentCapacity + additionalTokens);
		lastUpdatedTimestamp = now;
	}

}
