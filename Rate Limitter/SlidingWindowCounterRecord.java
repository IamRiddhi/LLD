package ratelimitter;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowCounterRecord implements Record {
	private Queue<Entry> queue;
	
	public SlidingWindowCounterRecord() {
		queue = new LinkedList<>();
	}

	public Queue<Entry> getQueue() {
		return queue;
	}
	
	public static class Entry {
		private Long timestamp;
		private int noOfTokens;
		
		public Entry(Long timestamp, int noOfTokens) {
			super();
			this.timestamp = timestamp;
			this.noOfTokens = noOfTokens;
		}

		public Long getTimestamp() {
			return timestamp;
		}
		
		public int getNoOfTokens() {
			return noOfTokens;
		}
	}
}
