package com.company;

import java.util.*;

public class Administrator implements ElevatorSystem
{
    private ArrayList<Elevator> elevators = new ArrayList<>();
    private Queue<ExternalRequest> externalRequests = new LinkedList<>();

    public Administrator(int numberOfElevators) {
        for(int i=0; i<numberOfElevators; i++)
            elevators.add(new Elevator(i));
    }

    private Elevator findClosestElevator(ExternalRequest request) {
        Elevator closestElevator = elevators.get(0);
        int min = 163; // currently, Burj Khalifa is the building with the most floors, at 163
        for(Elevator elevator: elevators) {
            int distance = request.getSourceFloor() - elevator.getCurrentFloor();
            Direction currDir = elevator.getCurrentDirection();
            State currState = elevator.getCurrentState();

            if(Math.abs(distance) < min) {
                // if the elevator is moving towards the target floor but has different direction
                if(currState != State.IDLE && currDir != request.getDirectionToGo())
                    continue;
                // if the elevator is moving away from the target floor
                if(((distance > 0 && currDir != Direction.UP) || (distance < 0 && currDir != Direction.DOWN)) && currState != State.IDLE)
                    continue;

                min = Math.abs(distance);
                closestElevator = elevator;
            }
        }
        return closestElevator;
    }

    private void processRequests() {
        while(!externalRequests.isEmpty())
        {
            ExternalRequest request = externalRequests.poll();
            Elevator elevator = findClosestElevator(request);
            // if the elevator is already at the correct floor then it just stops
            if(elevator.getCurrentFloor() == request.getSourceFloor()){
                elevator.setCurrentState(State.STOPPED);
                continue;
            }
            System.out.println("Elevator " + elevator.getID() + " coming to floor number " + request.getSourceFloor());
            elevator.requestFloor(request.getSourceFloor(), request.getDirectionToGo());
        }
    }

    @Override
    public void requestPickup(Direction direction, int floor) {
        externalRequests.add(new ExternalRequest(direction, floor));
    }

    @Override
    public void requestFloor(int ID, int targetFloor) {
        Elevator elevator = elevators.get(ID);
        if(targetFloor > elevator.getCurrentFloor())
            elevator.requestFloor(targetFloor, Direction.UP);
        else if(targetFloor < elevator.getCurrentFloor())
            elevator.requestFloor(targetFloor, Direction.DOWN);
        System.out.println("Elevator " + elevator.getID() + " coming to floor number " + targetFloor);
    }

    @Override
    public void step() {
        processRequests();
        elevators.forEach(Elevator::step);
    }

    @Override
    public List<List<Integer>> status() {
        List<List<Integer>> status = new LinkedList<>();
        for(Elevator elevator: elevators)
            status.add(elevator.getStatus());
        return status;
    }
}
