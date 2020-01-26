package Lab4;
import MazebotSim.MazebotSimulation;
import TraverseMaze.*;
//import Lab2.*;
public class SimulatedMain {

	public static void main(String[] args) {
		MazebotSimulation sim = new MazebotSimulation("Mazes/maze_1_3by4.png", 1.5,  1.13);
		sim.setRobotPosition(0.150, 0.9, 0);
		sim.startSimulation();
		
		
		
		MazeRobot.main(new String[0]);
		sim.stopSimulation();


	}

}