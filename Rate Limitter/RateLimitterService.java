package ratelimitter;

public class RateLimitterService {
	RulesServiceCache ruleService;
	
	public RateLimitterService() {
		ruleService = RulesServiceCache.getRulesServiceCacheInstance();
	}
	public boolean allowAccess(String apiKey , String serviceId , int tokens) {
		RateLimitter rateLimitter = new RateLimitterFactory().getRateLimitter(ruleService.getRateLimitterAlgorithm(serviceId));
		return rateLimitter.allowAccess(apiKey, serviceId, tokens);
	}
}
