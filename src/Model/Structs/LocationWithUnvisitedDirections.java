package Model.Structs;

public class LocationWithUnvisitedDirections 
{
	private Location location;
    private DirectionsStack unvisitedDirections;

	public LocationWithUnvisitedDirections(Location locationParameter, DirectionsStack directionsStackParameter) 
	{
		this.location = locationParameter;
        this.unvisitedDirections = directionsStackParameter;
	}

	public Location getLocation()
    {
        return this.location;
    }

	public DirectionsStack getUnvisitedDirections()
	{
		return this.unvisitedDirections;
	}
}
