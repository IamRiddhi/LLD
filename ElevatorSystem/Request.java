package elevator;

public class Request {
	private int sourceFloor;
	private int destinationFloor;
	private Location location;
	private Direction direction;
	
	private Request(RequestBuilder reqBuilder) {
		this.sourceFloor = reqBuilder.sourceFloor;
		this.destinationFloor = reqBuilder.destinationFloor;
		this.location = reqBuilder.location;
		this.direction = reqBuilder.direction;
	}
	
	public int getSourceFloor() {
		return sourceFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public Location getLocation() {
		return location;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public static class RequestBuilder {
		
		private int sourceFloor;
		private int destinationFloor;
		private Location location;
		private Direction direction;
		
		public int getSourceFloor() {
			return sourceFloor;
		}
		
		public RequestBuilder setSourceFloor(int sourceFloor) {
			this.sourceFloor = sourceFloor;
			return this;
		}
		
		public int getDestinationFloor() {
			return destinationFloor;
		}
		
		public RequestBuilder setDestinationFloor(int destinationFloor) {
			this.destinationFloor = destinationFloor;
			return this;
		}
		
		public Location getLocation() {
			return location;
		}
		
		public RequestBuilder setLocation(Location location) {
			this.location = location;
			return this;
		}
		
		public Direction getDirection() {
			return direction;
		}
		
		public RequestBuilder setDirection(Direction direction) {
			this.direction = direction;
			return this;
		}
		
		public Request buildRequest() {
			return new Request(this);
		}
		
	}
}
