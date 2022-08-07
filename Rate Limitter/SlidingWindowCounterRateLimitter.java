package ratelimitter;

import java.util.Queue;

import ratelimitter.SlidingWindowCounterRecord.Entry;

public class SlidingWindowCounterRateLimitter implements RateLimitter {

	private static volatile SlidingWindowCounterRateLimitter instance;

	private RulesServiceCache ruleServiceCache;
	
	private RedisCacheService redisCacheService;
	
	private SlidingWindowCounterRateLimitter() {
		ruleServiceCache = RulesServiceCache.getRulesServiceCacheInstance();
		redisCacheService= RedisCacheService.getRedisCacheServiceInstance();
	}
	
	public static SlidingWindowCounterRateLimitter getInstance() {
		if(instance == null) {
			synchronized(SlidingWindowCounterRateLimitter.class) {
				if(instance == null)
					instance = new SlidingWindowCounterRateLimitter();
			}
		}
		return instance;
	}
	@Override
	public synchronized boolean allowAccess(String apiKey, String serviceId, int tokens) {
		SlidingWindowCounterRule rule = (SlidingWindowCounterRule)ruleServiceCache.getRuleForService(serviceId);
		int maxRequestsAllowedInCurrentWindow = rule.getAllowedRequests();
		long timeFrame = rule.getTimeFrame();
		
		SlidingWindowCounterRecord record = (SlidingWindowCounterRecord)redisCacheService.getRecord(apiKey);
		if(record == null)
			record = new SlidingWindowCounterRecord();
		Queue<Entry> queue = record.getQueue();

		// removing outdated entries.
		while(!queue.isEmpty() && queue.peek().getTimestamp() + timeFrame < System.currentTimeMillis())
			queue.poll();
		
		if(queue.size() + tokens <= maxRequestsAllowedInCurrentWindow) {
			queue.add(new SlidingWindowCounterRecord.Entry(System.currentTimeMillis() , tokens));
			redisCacheService.setRecord(apiKey, record);
			System.out.println("API KEY : " + apiKey + "allowed.");
			return true;
		}
		System.out.println("API KEY : " + apiKey + "rejected.");
		return false;
	}
}
