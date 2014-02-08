package storm2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.utilities.HallEffectSpeedSensor;

public final class Intake extends Subsystem {
    private final Talon                 _motor = new Talon(RobotMap.PORT_MOTOR_ROLLER);
    private final DigitalInput          _ir = new DigitalInput(RobotMap.PORT_SENSOR_BALL_IR);
    private final HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT_ROLLER);
    private final Solenoid              _solBot = new Solenoid(RobotMap.PORT_SOLENOID_INTAKE_BOTTOM);
    private final Solenoid              _solTop = new Solenoid(RobotMap.PORT_SOLENOID_INTAKE_TOP);
    private int _mode;

    public Intake(int mode) {
        setMode(mode);
    }

    protected void initDefaultCommand() {}

    public double getRollerSpeed() {
        return _speedSensor.pidGet();
    }

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
                _solBot.set(false);
                _solTop.set(false);
                break;
            case 1:
                _solBot.set(true);
                _solTop.set(false);
                break;
            case 2:
                _solBot.set(true);
                _solTop.set(true);
                break;
            default:
                _solBot.set(false);
                _solTop.set(false);
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
