package ratelimitter;

public class TokenBucketRateLimitter implements RateLimitter {

	private static volatile TokenBucketRateLimitter tokenBucketRateLimitterInstance;
	
	private RulesServiceCache ruleServiceCache;
	
	private RedisCacheService redisCacheService;
	
	private TokenBucketRateLimitter() {
		ruleServiceCache = RulesServiceCache.getRulesServiceCacheInstance();
		redisCacheService = RedisCacheService.getRedisCacheServiceInstance();
	}
	
	public static TokenBucketRateLimitter getTokenBucketRateLimitterInstance() {
		if(tokenBucketRateLimitterInstance == null) {
			synchronized (TokenBucketRateLimitter.class) {
				if(tokenBucketRateLimitterInstance == null)
					tokenBucketRateLimitterInstance = new TokenBucketRateLimitter();
			}
		}
		return tokenBucketRateLimitterInstance;
	}

	@Override
	public synchronized boolean allowAccess(String apiKey , String serviceId , int tokens) {
		TokenBucketRule rule = (TokenBucketRule)ruleServiceCache.getRuleForService(serviceId);
		int bucketCapacity = rule.getBucketCapacity();
		int refreshRate = rule.getRefreshRate();
		
		TokenBucketRecord tokenBucketRecord = (TokenBucketRecord)redisCacheService.getRecord(apiKey);
		if(tokenBucketRecord == null) {
			tokenBucketRecord = new TokenBucketRecord(bucketCapacity, System.currentTimeMillis());
		}
		
		this.refill(bucketCapacity, refreshRate , tokenBucketRecord, apiKey);
		int currentCapacity = tokenBucketRecord.getCurrentCapacity();
		Long lastUpdatedTimestamp = tokenBucketRecord.getTimestamp();
		
		if(currentCapacity - tokens >= 0)
		{
			currentCapacity -= tokens;
			tokenBucketRecord.setCurrentCapacity(currentCapacity);
			redisCacheService.setRecord(apiKey, tokenBucketRecord);
			System.out.println("Thread = " + Thread.currentThread() + " , Request allowed and current capacity is " + currentCapacity);
			return true;
		}
		System.out.println("Thread = " + Thread.currentThread() + " , Request throttled becuase current capacity is " + currentCapacity);
		return false;
	}
	
	private void refill(int bucketCapacity , int refreshRate , TokenBucketRecord tokenBucketRecord , String apiKey) {
		int currentCapacity = tokenBucketRecord.getCurrentCapacity();
		Long lastUpdatedTimestamp = tokenBucketRecord.getTimestamp();
		long now = System.currentTimeMillis();
		int additionalTokens = (int)((now - lastUpdatedTimestamp) / 1000) * refreshRate;
		currentCapacity = Math.min(bucketCapacity, currentCapacity + additionalTokens);
		lastUpdatedTimestamp = now;
		tokenBucketRecord.setCurrentCapacity(currentCapacity);
		tokenBucketRecord.setTimestamp(lastUpdatedTimestamp);
		redisCacheService.setRecord(apiKey, tokenBucketRecord);
	}
}
