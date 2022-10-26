package com.company;

public class InternalRequest
{
    private final int floorToGo;

    public InternalRequest(int destination) {
        floorToGo = destination;
    }

    public int getFloorToGo() {
        return floorToGo;
    }
}
