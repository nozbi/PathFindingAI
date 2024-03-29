package Model.Classes;

import Model.Enums.Direction;
import Model.Structs.DirectionsStack;
import Model.Structs.Location;
import Model.Structs.LocationWithUnvisitedDirections;

public class DirectionsStackSorter 
{
    private static double calculateDistance(Location currentLocation, Location targetLocation)
    {
        return Math.hypot(targetLocation.getX() - currentLocation.getX(), targetLocation.getY() - currentLocation.getY());
    }

    public static LocationWithUnvisitedDirections getSortedDirectionsStackAccordingToTargetDistance(Location currentLocation, Location targetLocation)
    {
        Location rightLocation = currentLocation.getCopy();
        rightLocation.moveRight();
        double rightDistance = DirectionsStackSorter.calculateDistance(rightLocation, targetLocation);
        Location downLocation = currentLocation.getCopy();
        downLocation.moveDown();
        double downDistance = DirectionsStackSorter.calculateDistance(downLocation, targetLocation);
        Location leftLocation = currentLocation.getCopy();
        leftLocation.moveLeft();
        double leftDistance = DirectionsStackSorter.calculateDistance(leftLocation, targetLocation);
        Location upLocation = currentLocation.getCopy();
        upLocation.moveUp();
        double upDistance = DirectionsStackSorter.calculateDistance(upLocation, targetLocation);
        Direction[] directionsArray = {Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.UP};
        double[] distancesArray = {rightDistance, downDistance, leftDistance, upDistance};
        for (int i = 0; i < distancesArray.length; i++) 
        {
            for (int j = i + 1; j < distancesArray.length; j++) 
            {
                if (distancesArray[j] < distancesArray[i]) 
                {
                    double distanceTemp = distancesArray[i];
                    distancesArray[i] = distancesArray[j];
                    distancesArray[j] = distanceTemp;

                    Direction directionTemp = directionsArray[i];
                    directionsArray[i] = directionsArray[j];
                    directionsArray[j] = directionTemp;
                }
            }
        }
        DirectionsStack directionsStack = new DirectionsStack();
        directionsStack.push(directionsArray[3]);
        directionsStack.push(directionsArray[2]);
        directionsStack.push(directionsArray[1]);
        directionsStack.push(directionsArray[0]);
        return new LocationWithUnvisitedDirections(currentLocation, directionsStack);
    }
}
