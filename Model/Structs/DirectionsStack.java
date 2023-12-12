package Model.Structs;

import Model.Enums.Direction;
import java.util.Stack;

public class DirectionsStack 
{
    private Stack<Direction> directionsStack;

    public DirectionsStack()
    {
        this.directionsStack = new Stack<Direction>();
        this.directionsStack.push(Direction.UP);
        this.directionsStack.push(Direction.LEFT);
        this.directionsStack.push(Direction.DOWN);
        this.directionsStack.push(Direction.RIGHT);
    }

    public boolean isEmpty()
    {
        return this.directionsStack.isEmpty();
    }

    public Direction pop()
    {
        return this.directionsStack.pop();
    }

    public void push(Direction directionParameter)
    {
        this.directionsStack.push(directionParameter);
    }
}
