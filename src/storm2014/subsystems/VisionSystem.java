package storm2014.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class VisionSystem {
    public static double getTargetXAngle() {
        return SmartDashboard.getNumber("Vertical target horizontal angle", 0);
    }
    public static double getTargetYAngle() {
        return SmartDashboard.getNumber("Vertical target vertical angle", 0);
    }
    public static double getBallDistance() {
        return SmartDashboard.getNumber("Distance to ball in inches", 0);
    }
    public static double getTargetDistance() {
        return SmartDashboard.getNumber("Distance to wall", 0);
    }
    public static double getBallXAngle() {
        return SmartDashboard.getNumber("Ball horizontal angle to center", 0);
    }
    public static double getBallYAngle() {
        return SmartDashboard.getNumber("Ball vertical angle to center", 0);
    }
    public static boolean foundBall() {
        return SmartDashboard.getBoolean("Found ball", false);
    }
    public static boolean foundHotTarget() {
        return SmartDashboard.getBoolean("Found horizontal target", false);
    }
    
}