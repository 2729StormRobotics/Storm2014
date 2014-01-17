package storm2014.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2014.RobotMap;
import storm2014.utilities.BangBangController;
import storm2014.utilities.HallEffectSpeedSensor;


public class Shooter extends PIDSubsystem{
    private static final double SCALE = 10000;
    //declares variables
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT);
    private Jaguar _wheelMotor = new Jaguar(RobotMap.PORT_MOTOR_SHOOTER);
    BangBangController bangbangcontroller;
    
    public Shooter(){
        super("shooter",0,0.032,0,0.27);
        bangbangcontroller = new BangBangController(_wheelMotor, _speedSensor,1,1500);
        _speedSensor.setMinSpeedRpm(200);
        SmartDashboard.putData("Shooter wheel PID",getPIDController());
        SmartDashboard.putData("Bang Bang Controller",bangbangcontroller);
        getPIDController().setOutputRange(-SCALE, SCALE);
        getPIDController().setPercentTolerance(5);
         
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
        _wheelMotor.set(output/SCALE);
    }

    protected void initDefaultCommand() {
    }

}