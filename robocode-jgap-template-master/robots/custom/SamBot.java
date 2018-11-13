package custom; 
 import robocode.*; 
import robocode.HitByBulletEvent;
public class SamBot extends AdvancedRobot {
boolean movingForward; 
int AHEAD = 4742;
int MOVE_INDEX = 1;
int MOVE_DISTANCE = 37;
int TURN_ANGLE = 24;
int BULLETS = 3;
int TURN_GUN_ANGLE = 3;

public void run() { 

while (true) { 
setAhead(AHEAD); 
setTurnRight(90); 
waitFor(new TurnCompleteCondition(this)) ; 
setTurnLeft(180); 
waitFor(new TurnCompleteCondition(this)); 
setTurnRight(180); 
waitFor(new TurnCompleteCondition(this)); 
turnGunRight(TURN_GUN_ANGLE); 
} 
} 
public void onScannedRobot(ScannedRobotEvent e) { 
fire(BULLETS); 
} 

public void onHitByBullet(HitByBulletEvent e) { 
turnRight(24); ahead(37); 
}

public void onHitWall(HitWallEvent e) { 
if (movingForward) { setBack(4742); movingForward = false; 
turnRight(TURN_GUN_ANGLE); 
} 
else { 
setAhead(4742); 
movingForward=true; 
}
} 

}