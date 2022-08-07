package ratelimitter;

public class TokenBucketRule implements Rule {
	private int bucketCapacity;
	private int refreshRate;
	
	private TokenBucketRule(Builder builder) {
		this.bucketCapacity = builder.getBucketCapacity();
		this.refreshRate = builder.getRefreshRate();
	}
	
	public int getBucketCapacity() {
		return bucketCapacity;
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public static class Builder {
		private int bucketCapacity;
		private int refreshRate;
		
		public int getBucketCapacity() {
			return bucketCapacity;
		}
		public int getRefreshRate() {
			return refreshRate;
		}

		public Builder setBucketCapacity(int bucketCapacity) {
			this.bucketCapacity = bucketCapacity;
			return this;
		}

		public Builder setRefreshRate(int refreshRate) {
			this.refreshRate = refreshRate;
			return this;
		}
		
		public Rule build() {
			return new TokenBucketRule(this);
		}
	}
}
