/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package storm2014.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Erik
 */
public class VisionSystem {
    
    public static double getXTarget() {
        return SmartDashboard.getNumber("Target X position ", 0);
    }
    public static double getYTarget() {
        return SmartDashboard.getNumber("Target Y position ", 0);
    }
    public static double getXBall() {
        return SmartDashboard.getNumber("Ball X position", 0);
    }
    public static double getYBall() {
        return SmartDashboard.getNumber("Ball Y position", 0);
    }
    public static double getXAngle() {
        return SmartDashboard.getNumber("Target horizontal angle", 0);
    }
    public static double getYAngle() {
        return SmartDashboard.getNumber("Target vertical angle", 0);
    }
    public static double getBallXAngle() {
        return SmartDashboard.getNumber("Ball horizontal angle", 0);
    }
    public static double getBallYAngle() {
        return SmartDashboard.getNumber("Ball vertical angle", 0);
    }
    public static boolean foundBall() {
        return SmartDashboard.getBoolean("Found ball", false);
    }
    public static boolean foundTarget() {
        return SmartDashboard.getBoolean("Found target ", false);
    }
}