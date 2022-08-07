package ratelimitter;

public class TokenBucketRecord implements Record {
	private int currentCapacity;
	private Long timestamp;
	
	public TokenBucketRecord(int currentCapacity, Long timestamp) {
		this.currentCapacity = currentCapacity;
		this.timestamp = timestamp;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TokenBucketRecord [currentCapacity=" + currentCapacity + ", timestamp=" + timestamp + "]";
	}
}
