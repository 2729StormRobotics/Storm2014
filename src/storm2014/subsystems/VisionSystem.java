/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.subsystems;
import storm2014.Robot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.RobotMap;
import storm2014.utilities.BangBangController;
import storm2014.utilities.HallEffectSpeedSensor;
import storm2014.utilities.TakeBackHalfController;
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
public static double getXAngle() {
   return SmartDashboard.getNumber("Target horizontal angle", 0);
}
public static double getYAngle() {
    return SmartDashboard.getNumber("Target vertical angle", 0);
}
public static boolean foundTarget() {
    return SmartDashboard.getBoolean("Found target ", false);
}
}