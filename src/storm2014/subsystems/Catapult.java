/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.commands.PullBack;

/**
 *
 * @author Matthew Rassmann
 */
public class Catapult extends Subsystem {

    private Talon _winch = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private Encoder _winchEncoder =  new Encoder(RobotMap.PORT_ENCODER_PULLBACKENCODER_1,RobotMap.PORT_ENCODER_PULLBACKENCODER_2);
    private Solenoid _disengage = new Solenoid(RobotMap.PORT_SOLENOID_DISENGAGE_CHANNEL);
    private Solenoid _engage = new Solenoid(RobotMap.PORT_SOLENOID_ENGAGE_CHANNEL);
    private Solenoid _latched = new Solenoid(RobotMap.PORT_SOLENOID_LATCHED_CHANNEL);
    private Solenoid _unlatched = new Solenoid(RobotMap.PORT_SOLENOID_UNLATCHED_CHANNEL);
    private AnalogChannel _pot = new AnalogChannel(RobotMap.PORT_SENSOR_POTENTIOMETER);
    
    
    protected void initDefaultCommand() {
        setDefaultCommand(new PullBack(0.0));
    }
    
    public void setWinchRawVal(double winchRawVal){
        _winch.set(winchRawVal);
    }
    
    public void resetWinchEncoder(){
        _winchEncoder.reset();
    }
    
    public double getWinchDistance(){
        return _winchEncoder.get();
    }
    
    public void disengage(){
        _disengage.set(true);
        _engage.set(false);
    }
    
    public void engage(){
        _engage.set(true);
        _disengage.set(false);
    }
    
    public void latch(){
        _latched.set(true);
        _unlatched.set(false);
    }
    
    public void unlatch(){
        _unlatched.set(true);
        _latched.set(false);
    }
    
    public double getVoltage(){
        return _pot.getVoltage();
    }
}
