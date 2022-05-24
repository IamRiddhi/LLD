package elevator;

import java.util.PriorityQueue;

public class ElevatorCar {

	private PriorityQueue<Request> upQueue;
	private PriorityQueue<Request> downQueue;
	private int currentFloor;
	private Direction direction;
	private ElevatorState elevatorState;
	
	public ElevatorCar(int currentFloor, ElevatorState elevatorState) {
		
		this.upQueue = new PriorityQueue<>((req1 , req2) -> req1.getDestinationFloor() - req2.getDestinationFloor());
		this.downQueue = new PriorityQueue<>((req1 , req2) -> req2.getDestinationFloor() - req1.getDestinationFloor());
		
		this.currentFloor = currentFloor;
		this.elevatorState = ElevatorState.IDLE;
	}
	
	public void sendRequest(Request request) {
		if(request.getDirection() == Direction.UP)
			sendUpRequest(request);
		else
			sendDownRequest(request);
	}
	
	private void sendUpRequest(Request request) {
		upQueue.offer(request);
	}
	
	private void sendDownRequest(Request request) {
		downQueue.offer(request);
	}
	
	public void moveElevator() { //scheduler will call this method on an intermittent basis
		if(this.elevatorState == ElevatorState.IDLE && (!upQueue.isEmpty() || !downQueue.isEmpty()))
			processRequest();
		System.out.println("Finished processing all requests");
		this.elevatorState = ElevatorState.IDLE;
	}
	
	private void processRequest() {
		if(this.direction == Direction.UP || this.elevatorState == ElevatorState.IDLE) {
			processUpRequest();
			processDownRequest();
		}
		else if(this.direction == Direction.DOWN){
			processDownRequest();
			processUpRequest();
		}
	}
	
	private void processUpRequest() {
		while(!upQueue.isEmpty()) {
			Request request = upQueue.poll();
			// do some hardware operation (go to that floor and open the door)
			this.currentFloor = request.getDestinationFloor();
			System.out.println("Elevator stopped at floor no " + this.currentFloor);
		}
		if(downQueue.isEmpty())
			this.elevatorState = ElevatorState.IDLE;
		else
			this.direction = Direction.DOWN;
	}
	
	private void processDownRequest() {
		while(!downQueue.isEmpty()) {
			Request request = downQueue.poll();
			// do some hardware operation (go to that floor and open the door)
			this.currentFloor = request.getDestinationFloor();
			System.out.println("Elevator stopped at floor no " + this.currentFloor);
		}
		if(upQueue.isEmpty())
			this.elevatorState = ElevatorState.IDLE;
		else
			this.direction = Direction.UP;
	}
}
