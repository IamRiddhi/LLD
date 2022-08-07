package ratelimitter;

import java.util.HashMap;
import java.util.Map;

public class RulesServiceCache {
	
	private static volatile RulesServiceCache ruleServiceCache;
	
	private Map<String , Rule> serviceIdToRuleCache; // rule specific to service.
	private Map<String , RateLimitterAlgorithm> serviceIdToAlgorithmMap;
	
	private RulesServiceCache() {
		this.serviceIdToRuleCache = new HashMap<>();
		this.serviceIdToAlgorithmMap = new HashMap<>();
		getRulesForAllServicesFromDb();
	}
	
	public static RulesServiceCache getRulesServiceCacheInstance() {
		if(ruleServiceCache == null) {
			synchronized (RulesServiceCache.class) {
				if(ruleServiceCache == null)
					ruleServiceCache = new RulesServiceCache();
			}
		}
		return ruleServiceCache;
	}
	
	public Rule getRuleForService(String serviceId) {
		return serviceIdToRuleCache.get(serviceId);
	}
	
	public RateLimitterAlgorithm getRateLimitterAlgorithm(String serviceId) {
		return serviceIdToAlgorithmMap.get(serviceId);
	}
	
	private void getRulesForAllServicesFromDb() {
		serviceIdToAlgorithmMap.put("Service1" , RateLimitterAlgorithm.TokenBucketAlgorithm);
		Rule ruleForService1 = new TokenBucketRule.Builder()
				.setBucketCapacity(5).setRefreshRate(1).build(); // use a factory to create the specific algorithm
		serviceIdToRuleCache.put("Service1", ruleForService1);
	}

}
