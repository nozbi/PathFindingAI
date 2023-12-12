package Model.Classes;

import java.util.Stack;

import Model.Enums.Direction;
import Model.Enums.PathFinderStatus;
import Model.Structs.Location;
import Model.Structs.LocationWithUnvisitedDirections;
import Model.Structs.PathFinderResult;

public class PathFinder 
{
	private Stack<LocationWithUnvisitedDirections> locationWithUnvisitedDirectionsStack;
	private Maze maze;

	public PathFinder(Maze mazeParameter)
	{
		this.maze = mazeParameter;
		this.locationWithUnvisitedDirectionsStack = new Stack<LocationWithUnvisitedDirections>();
		LocationWithUnvisitedDirections startingLocationWithDirection = DirectionsStackSorter.getSortedDirectionsStackAccordingToTargetDistance(this.maze.getStartLocationCopy(), this.maze.getEndLocationCopy());
		this.locationWithUnvisitedDirectionsStack.push(startingLocationWithDirection);
	}

	public PathFinderResult getNextFindingStepResult()
	{
		if(this.locationWithUnvisitedDirectionsStack.isEmpty())
		{
			return new PathFinderResult(PathFinderStatus.PATH_NOT_FOUND, this.maze.getMazeArray());
		}
		else if (this.maze.isLocationTheTarget(this.locationWithUnvisitedDirectionsStack.peek().getLocation()))
		{
			this.maze.setLocationAsPath(this.locationWithUnvisitedDirectionsStack.peek().getLocation());
			return new PathFinderResult(PathFinderStatus.PATH_FOUND, this.maze.getMazeArray());
		}
		else if(this.locationWithUnvisitedDirectionsStack.peek().getUnvisitedDirections().isEmpty())
		{
			this.maze.setLocationAsVisited(this.locationWithUnvisitedDirectionsStack.peek().getLocation());
			this.locationWithUnvisitedDirectionsStack.pop();
			return new PathFinderResult(PathFinderStatus.SEARCHING, this.maze.getMazeArray());
		}
		else
		{
			LocationWithUnvisitedDirections currentlocationWithUnvisitedDirections = this.locationWithUnvisitedDirectionsStack.peek();

			Direction searchedDirection = null;
			Location nextLocation = null;
			while(currentlocationWithUnvisitedDirections.getUnvisitedDirections().isEmpty() == false)
			{
				searchedDirection = currentlocationWithUnvisitedDirections.getUnvisitedDirections().pop(); 
				nextLocation = currentlocationWithUnvisitedDirections.getLocation().getCopy();
				switch(searchedDirection)
				{
					case UP: 
						nextLocation.moveUp();
						break;
					case DOWN:
						nextLocation.moveDown();
						break;
					case LEFT:
						nextLocation.moveLeft();
						break;
					case RIGHT:
						nextLocation.moveRight();
						break;
					default:
						break;
				}
				if(this.maze.isLocationUnvisited(nextLocation))
				{
					LocationWithUnvisitedDirections newLocationWithDirection = DirectionsStackSorter.getSortedDirectionsStackAccordingToTargetDistance(nextLocation, this.maze.getEndLocationCopy());
					this.locationWithUnvisitedDirectionsStack.push(newLocationWithDirection);
					break;
				}
			}

			if(!this.maze.isLocationPath(currentlocationWithUnvisitedDirections.getLocation()))
			{
				this.maze.setLocationAsPath(currentlocationWithUnvisitedDirections.getLocation());
				return new PathFinderResult(PathFinderStatus.SEARCHING, this.maze.getMazeArray());
			}
			else if(nextLocation == null)
			{
				this.maze.setLocationAsVisited(this.locationWithUnvisitedDirectionsStack.peek().getLocation()); 
				this.locationWithUnvisitedDirectionsStack.pop();
				return new PathFinderResult(PathFinderStatus.SEARCHING, this.maze.getMazeArray());
			}
			else
			{
				return this.getNextFindingStepResult();
			}
		}
	}

	public PathFinderResult getPathFinderResult()
	{
		PathFinderResult pathFinderResult = this.getNextFindingStepResult();
		while((pathFinderResult.getPathFinderStatus() != PathFinderStatus.PATH_FOUND) && (pathFinderResult.getPathFinderStatus() != PathFinderStatus.PATH_NOT_FOUND))
		{
			pathFinderResult = this.getNextFindingStepResult();
		}
		return pathFinderResult;
	}
}
