package elevator;

public class ElevatorSystem {
	
	private static ElevatorSystem elevatorSystemInstance;
	ElevatorCar[] elevatorCars;
	private int curElevatorCar;
	//metadata information about the complex it is handling

	private ElevatorSystem(ElevatorCar[] elevatorCars) {
		this.elevatorCars = elevatorCars;
		curElevatorCar = 0;
	}
	
	public static ElevatorSystem getElevatorSystemInstance(int totalCars) {
		if(elevatorSystemInstance != null)
			return elevatorSystemInstance;
		
		ElevatorCar[] elevatorCars = new ElevatorCar[totalCars];
		for(int i = 0 ; i < totalCars ; i ++)
			elevatorCars[i] = new ElevatorCar(0, ElevatorState.IDLE);
		
		return (elevatorSystemInstance = new ElevatorSystem(elevatorCars));
	}
	
	public void scheduleOutsideElevatorRequest(Request request) {
		//round robin to send request to elevators.
		elevatorCars[curElevatorCar].sendRequest(request);
		System.out.println("Sending request to elevator no " + curElevatorCar);
		curElevatorCar = (curElevatorCar + 1) % elevatorCars.length;
	}
	
	public void scheduleInsideElevatorRequest(Request request , int elevatorNo) {
		elevatorCars[elevatorNo].sendRequest(request);
		System.out.println("Elevator no " + curElevatorCar + " stopped to pick up at floor " + request.getSourceFloor());
	}
}
