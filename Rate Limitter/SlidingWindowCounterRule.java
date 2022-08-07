package ratelimitter;

public class SlidingWindowCounterRule implements Rule {
	private long timeFrame;
	private int allowedRequests;
	
	public SlidingWindowCounterRule(long timeFrame, int allowedRequests) {
		this.timeFrame = timeFrame;
		this.allowedRequests = allowedRequests;
	}

	public long getTimeFrame() {
		return timeFrame;
	}
	
	public int getAllowedRequests() {
		return allowedRequests;
	}
}
