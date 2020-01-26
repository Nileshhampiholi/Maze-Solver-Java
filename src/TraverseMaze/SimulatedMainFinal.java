package TraverseMaze;
import MazebotSim.MazebotSimulation;

public class SimulatedMainFinal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MazebotSimulation sim = new MazebotSimulation("Mazes/maze_1_3by4.png", 1.5,  1.13);
		sim.setRobotPosition(0.15, 0.5, 0);
		sim.startSimulation();
				
		//Call to the old main method here
		MazeRobot.main(new String[0]);
		sim.stopSimulation();

	}

	}
