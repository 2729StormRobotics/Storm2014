package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2014.RobotMap;
import storm2014.utilities.HallEffectSpeedSensor;

public final class Intake extends Subsystem {
    private final Talon                 _motor       = new Talon(RobotMap.PORT_MOTOR_ROLLER);
    private final DigitalInput          _ir          = new DigitalInput(RobotMap.PORT_SENSOR_BALL_IR);
//    private final HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT_ROLLER);
    private final DoubleSolenoid        _solBot      = new DoubleSolenoid(RobotMap.PORT_SOLENOID_INTAKE_BOTTOM_OUT,
                                                                          RobotMap.PORT_SOLENOID_INTAKE_BOTTOM_IN);
    private final DoubleSolenoid        _solTop      = new DoubleSolenoid(RobotMap.PORT_SOLENOID_INTAKE_TOP_OUT,
                                                                          RobotMap.PORT_SOLENOID_INTAKE_TOP_IN);
    private int _mode;

    public Intake(int mode) {
        setMode(mode);
        LiveWindow.addSensor("Intake", "Ball Detector", _ir);
        LiveWindow.addActuator("Intake", "Arm Bottom", _solBot);
        LiveWindow.addActuator("Intake", "Arm Top", _solTop);
        LiveWindow.addActuator("Intake", "Motor", _motor);
    }

    protected void initDefaultCommand() {}

//    public double getRollerSpeed() {
//        return _speedSensor.pidGet();
//    }

    public void setRollerRaw(double output) {
        _motor.set(output);
    }
    
    public void spinIn() {
        setRollerRaw(1);
    }
    public void spinOut() {
        setRollerRaw(-1);
    }
    public void stop() {
        setRollerRaw(0);
    }

    public boolean isInIntake() {
        return _ir.get();
    }

    public void setMode(int mode) {
        _mode = mode;
        switch (mode) {
            case 0:
                _solBot.set(DoubleSolenoid.Value.kReverse);
                _solTop.set(DoubleSolenoid.Value.kReverse);
                break;
            case 1:
                _solBot.set(DoubleSolenoid.Value.kForward);
                _solTop.set(DoubleSolenoid.Value.kReverse);
                break;
            case 2:
                _solBot.set(DoubleSolenoid.Value.kForward);
                _solTop.set(DoubleSolenoid.Value.kForward);
                break;
            default:
                _solBot.set(DoubleSolenoid.Value.kReverse);
                _solTop.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public int getMode() {
        return _mode;
    }

    public String getModeName() {
        switch (_mode) {
            case 0:
                return "Low";
            case 1:
                return "Middle";
            case 2:
                return "High";
        }
        return "";
    }
}
