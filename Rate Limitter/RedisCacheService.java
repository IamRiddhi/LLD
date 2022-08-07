package ratelimitter;

import java.util.HashMap;
import java.util.Map;

public class RedisCacheService { // cache specific to apiKey , 
//	apiKey would be unique per user , per service.

	private static volatile RedisCacheService ruleServiceCacheInstance;
	
	private Map<String , Record> apiKeyToRecordMap; 
	
	private RedisCacheService() {
		this.apiKeyToRecordMap = new HashMap<>();
	}
	
	public static RedisCacheService  getRedisCacheServiceInstance() {
		if(ruleServiceCacheInstance == null) {
			synchronized(RedisCacheService.class) {
				if(ruleServiceCacheInstance == null)
					ruleServiceCacheInstance = new RedisCacheService();
			}
		}
		return ruleServiceCacheInstance;
	}
	
	public Record getRecord(String apiKey) {
		Record record = apiKeyToRecordMap.get(apiKey);
		return record;
	}
	
	public void setRecord(String apiKey , Record record) {
		apiKeyToRecordMap.put(apiKey , record);
	}

}
