package com.company;

import java.util.*;

enum State {
    MOVING, STOPPED, IDLE;
}

enum Direction {
    UP, DOWN;
}

public class Elevator
{
    private final int ID;
    Random rand = new Random();
    private int currentFloor = rand.nextInt(10);
    private Direction currentDirection = Direction.UP;
    private State currentState = State.IDLE;
    private PriorityQueue<InternalRequest> internalRequestsUp = new PriorityQueue<>(Comparator.comparing(InternalRequest::getFloorToGo));
    private PriorityQueue<InternalRequest> internalRequestsDown = new PriorityQueue<>(Comparator.comparing(InternalRequest::getFloorToGo).reversed());

    Elevator(int id) {
        ID = id;
    }

    public int getID() {
        return ID;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setCurrentFloor(int floor) { this.currentFloor = floor; }

    public int getNextStop() {
        if(!internalRequestsUp.isEmpty() && currentDirection == Direction.UP)
            return internalRequestsUp.peek().getFloorToGo();
        else if(!internalRequestsDown.isEmpty())
            return internalRequestsDown.peek().getFloorToGo();
        return currentFloor;
    }

    public List<Integer> getStatus() {
        if(currentState == State.IDLE)
            return new LinkedList<>(Arrays.asList(ID, currentFloor, currentFloor));
        return new LinkedList<>(Arrays.asList(ID, currentFloor, getNextStop()));
    }

    public void requestFloor(int targetFloor, Direction direction) {
        if(direction == Direction.UP) {
            internalRequestsUp.add(new InternalRequest(targetFloor));
        }
        else if(direction == Direction.DOWN) {
            internalRequestsDown.add(new InternalRequest(targetFloor));
        }
    }

    public List<InternalRequest> getAllRequests() {
        List<InternalRequest> list = new LinkedList<>();
        if(currentDirection == Direction.UP) {
            list.addAll(internalRequestsUp);
            list.addAll(internalRequestsDown);
        }
        else {
            list.addAll(internalRequestsDown);
            list.addAll(internalRequestsUp);
        }
        return list;
    }

    public void step() {
        if(internalRequestsDown.isEmpty() && internalRequestsUp.isEmpty()) {
            currentState = State.IDLE;
            return;
        }

        List<InternalRequest> currentJobs = getAllRequests();
//        currentJobs.forEach(InternalRequest -> System.out.print(InternalRequest.getFloorToGo() + " "));
        currentState = State.MOVING;
        int destination = currentJobs.get(0).getFloorToGo();
        if(destination > currentFloor && !internalRequestsUp.isEmpty()) {
            currentDirection = Direction.UP;
            currentFloor++;
            if(currentFloor == destination) {
                System.out.println("Elevator " + ID + " stopping at floor number " + currentFloor);
                internalRequestsUp.poll();
                currentState = State.STOPPED;
            }
        }
        else if(!internalRequestsDown.isEmpty()){
            currentDirection = Direction.DOWN;
            currentFloor--;
            if(currentFloor == destination) {
                System.out.println("Elevator " + ID + " stopping at floor number " + currentFloor);
                internalRequestsDown.poll();
                currentState = State.STOPPED;
            }
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null || !o.getClass().equals(getClass())) {
            return false;
        }
        Elevator that = (Elevator) o;
        return Objects.equals(ID, that.ID);
    }
}


