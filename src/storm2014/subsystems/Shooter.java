package storm2014.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.RobotMap;
import storm2014.utilities.HallEffectSpeedSensor;


public class Shooter extends PIDSubsystem{
    //declares variables
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT);
    private Jaguar _wheelMotor = new Jaguar(RobotMap.PORT_MOTOR_SHOOTER);
    
    
    public Shooter(){
        super("shooter",0,0,0,0);
        _speedSensor.setMinSpeedRpm(200);
        SmartDashboard.putData(this);
    }
    
    //gets current speed of motor
    public double getSpeedRPM(){
        return _speedSensor.getSpeedRpm();
    }
    //gets motors raw value
    public double getMotorRawVal(){
        return _wheelMotor.get();
    }
    //sets the speed of the motor
    public void setMotorRawVal(double speed){
        _wheelMotor.set(speed);
    }

    protected double returnPIDInput() {
          return _speedSensor.getSpeedRpm();
    }

    protected void usePIDOutput(double output) {
        _wheelMotor.set(output);
    }

    protected void initDefaultCommand() {
    }

}