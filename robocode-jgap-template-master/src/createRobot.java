import javax.tools.*;
import java.io.*;
import java.util.ArrayList;
/* 
 * Create Robot
 * Creates and compiles Robocode Java file to test 
 * 
 */

public class createRobot {

	public static void create(double[] chromo) {
		createRobotFile(chromo); // create file
		compile(); // now compile it
	}
	
	public static void compile () {
		String fileToCompile = "robots/custom/SamBot.java"; // which file to compile * rhyming :) *
	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    compiler.run(null, null, null, fileToCompile); // run compile
	}
	
	private static String getMove(double index, double degrees, double distance) {
		String goLeft = String.format("turnLeft(%d); ahead(%d);", (int) degrees, (int) distance);
		String goRight = String.format("turnRight(%d); ahead(%d);", (int) degrees, (int) distance);
		String goBack = String.format("back(%d);", (int) distance);
		String[] movements = new String[4];
		movements[0] = goLeft;
		movements[1] = goRight;
		movements[2] = goBack;
		return movements[(int) index];
	}
	
	public static void createRobotFile(double[] chromo){
		try {
			FileWriter fstream = new FileWriter("robots/custom/SamBot.java"); // file name to create
			BufferedWriter out = new BufferedWriter(fstream);
			
			
			int AHEAD = (int) chromo[0];
			int MOVE_INDEX = (int) chromo[1];
			int MOVE_DISTANCE = (int) chromo[2];
			int TURN_ANGLE = (int) chromo[3];
			int BULLETS = (int) chromo[4];
			int TURN_GUN_ANGLE = (int) chromo[5];
			//start code
			out.write("package custom; \n " +
				"import robocode.*; \n" +
				"import robocode.HitByBulletEvent;\n" + 
				"public class SamBot extends AdvancedRobot {\n");
			
					// build up robot logic in here
					// access chromosomes from array to set as variables
			
					out.append("boolean movingForward; \n");
					out.append("int AHEAD = " + AHEAD + ";\n");
					out.append("int MOVE_INDEX = " + MOVE_INDEX + ";\n");
					out.append("int MOVE_DISTANCE = " + MOVE_DISTANCE + ";\n");
					out.append("int TURN_ANGLE = " + TURN_ANGLE + ";\n");
					out.append("int BULLETS = " + BULLETS + ";\n");
					out.append("int TURN_GUN_ANGLE = " + TURN_GUN_ANGLE + ";\n\n");

					out.append("public void run() { \n\n");
					out.append("while (true) { \n");
					out.append("setAhead(AHEAD); \n");
					out.append("setTurnRight(90); \n");
					out.append("waitFor(new TurnCompleteCondition(this)) ; \n");
					out.append("setTurnLeft(180); \n");
					out.append("waitFor(new TurnCompleteCondition(this)); \n");
					out.append("setTurnRight(180); \n");
					out.append("waitFor(new TurnCompleteCondition(this)); \n");
					out.append("} \n");
					out.append("} \n");
					out.append("public void onScannedRobot(ScannedRobotEvent e) { \n");
//					out.append(getMove(1.3, 5.0, 20.0) + " \n");
					out.append("fire(BULLETS); \n");
					out.append("} \n\n");
					// end of robot
					out.append("public void onHitByBullet(HitByBulletEvent e) { \n");
					out.append( getMove(MOVE_INDEX, TURN_ANGLE, MOVE_DISTANCE) + " \n" );
					out.append("}\n\n");

					out.append("public void onHitWall(HitWallEvent e) { \n");
					out.append("if (movingForward) { setBack(" + AHEAD + "); movingForward = false; \nturnRight(TURN_GUN_ANGLE); \n} \n");
					out.append("else { \nsetAhead(" + AHEAD + "); \nmovingForward=true; \n}\n");
					out.append("} \n");					
			out.append("\n}");
			
			  
			out.close(); // close output stream
			
		} catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}