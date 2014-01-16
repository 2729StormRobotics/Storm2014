/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.subsystems;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.RobotMap;
import storm2014.utilities.HallEffectSpeedSensor;
/**
 *
 * @author Erik
 */
public abstract class PossibleCatapultShooter extends PIDSubsystem{
    static final double SCALE = 10000;
    static double shootMethodOutput = 0;
    private PIDSource _pidSource;
    private PIDSource _pidsourceAngle;
    private PIDSource _pidsourceRelease;
    
    public PossibleCatapultShooter(){
        super("shooter",0,0.032,0,0.27);
        //_shotsensor 
        SmartDashboard.putData("Catapult PID",getPIDController());
        getPIDController().setOutputRange(-SCALE, SCALE);
        getPIDController().setPercentTolerance(5);
    }
    
    public double getShooterMethod(){
        return _pidSource.pidGet();
    }
    
    public double getDrawAngleMethod(){
        return _pidsourceAngle.pidGet();
    }
    
    public double getReleaseShot(){
        return _pidsourceRelease.pidGet();
    }

}
