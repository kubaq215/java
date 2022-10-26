package com.company;

public class ExternalRequest
{
    private final Direction directionToGo;
    private final int sourceFloor;

    public ExternalRequest(Direction direction, int floor) {
        directionToGo = direction;
        sourceFloor = floor;
    }

    public int getSourceFloor()
    {
        return sourceFloor;
    }

    public Direction getDirectionToGo()
    {
        return directionToGo;
    }
}
