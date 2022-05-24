package elevator;

public class Driver {

	public static void main(String[] args) {
		ElevatorSystem elevatorSystem = ElevatorSystem.getElevatorSystemInstance(2);
		
		Request passengerPressUpButtonOnOthFloor = new Request.RequestBuilder().setSourceFloor(0)
				.setDestinationFloor(0)
				.setLocation(Location.OUTSIDE_ELEVATOR)
				.setDirection(Direction.UP).buildRequest();
		
		elevatorSystem.scheduleOutsideElevatorRequest(passengerPressUpButtonOnOthFloor);
		
		Request passengerPressUpButtonOn3rdFloor = new Request.RequestBuilder().setSourceFloor(3)
				.setDestinationFloor(3)
				.setLocation(Location.OUTSIDE_ELEVATOR)
				.setDirection(Direction.UP).buildRequest();
		
		elevatorSystem.scheduleOutsideElevatorRequest(passengerPressUpButtonOn3rdFloor);
		
		Request passengerPressDownButtonOn5thFloor = new Request.RequestBuilder().setSourceFloor(5)
				.setDestinationFloor(5)
				.setLocation(Location.OUTSIDE_ELEVATOR)
				.setDirection(Direction.DOWN).buildRequest();
		
		elevatorSystem.scheduleOutsideElevatorRequest(passengerPressDownButtonOn5thFloor);
		
		Request firstPassengerWantsToGoTo7thFloor = new Request.RequestBuilder().setSourceFloor(0)
				.setDestinationFloor(7)
				.setLocation(Location.INSIDE_ELEVATOR)
				.setDirection(Direction.UP).buildRequest();
		
		elevatorSystem.scheduleInsideElevatorRequest(firstPassengerWantsToGoTo7thFloor , 0);
		
		Request secondPassengerWantsToGoTo4thFloor = new Request.RequestBuilder().setSourceFloor(3)
				.setDestinationFloor(4)
				.setLocation(Location.INSIDE_ELEVATOR)
				.setDirection(Direction.UP).buildRequest();
		
		elevatorSystem.scheduleInsideElevatorRequest(secondPassengerWantsToGoTo4thFloor , 1);
		
		Request thirdPassengerWantsToGoTo2ndFloor = new Request.RequestBuilder().setSourceFloor(5)
				.setDestinationFloor(2)
				.setLocation(Location.INSIDE_ELEVATOR)
				.setDirection(Direction.DOWN).buildRequest();
		
		elevatorSystem.scheduleInsideElevatorRequest(thirdPassengerWantsToGoTo2ndFloor , 0);

	}

}
