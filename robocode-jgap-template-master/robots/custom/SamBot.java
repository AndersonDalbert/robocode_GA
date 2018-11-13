package custom; 
 import robocode.*; 
import robocode.HitByBulletEvent;
public class SamBot extends AdvancedRobot {
boolean movingForward; 
int AHEAD = 3308;
int MOVE_INDEX = 2;
int MOVE_DISTANCE = 21;
int TURN_ANGLE = 10;
int BULLETS = 3;
int TURN_GUN_ANGLE = 5;

public void run() { 

while (true) { 
setAhead(AHEAD); 
setTurnRight(90); 
waitFor(new TurnCompleteCondition(this)) ; 
setTurnLeft(180); 
waitFor(new TurnCompleteCondition(this)); 
setTurnRight(180); 
waitFor(new TurnCompleteCondition(this)); 
} 
} 
public void onScannedRobot(ScannedRobotEvent e) { 
fire(BULLETS); 
} 

public void onHitByBullet(HitByBulletEvent e) { 
back(21); 
}

public void onHitWall(HitWallEvent e) { 
if (movingForward) { setBack(3308); movingForward = false; 
turnRight(TURN_GUN_ANGLE); 
} 
else { 
setAhead(3308); 
movingForward=true; 
}
} 

}