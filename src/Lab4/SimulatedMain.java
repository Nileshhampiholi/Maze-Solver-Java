package Lab4;

import MazebotSim.MazebotSimulation;

public class SimulatedMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MazebotSimulation sim = new MazebotSimulation("Mazes/maze_1_3by4.png", 1.5,  1.13);
		sim.setRobotPosition(0.525, 0.175, 90);
		sim.startSimulation();
				
		//Call to the old main method here
		 MazeRobot.main(new String[0]);
		sim.stopSimulation();

	}

}
