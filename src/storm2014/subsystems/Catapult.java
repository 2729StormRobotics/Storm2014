/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.subsystems;

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
    //Encoders
    //Motor Puller back thing/ winch
    //release/Pneumatics
    private Talon _winch = new Talon(RobotMap.PORT_MOTOR_WINCH);
    private Encoder _winchEncoder =  new Encoder(RobotMap.PORT_ENCODER_PULLBACKENCODER_1,RobotMap.PORT_ENCODER_PULLBACKENCODER_2);
    private Solenoid _release = new Solenoid(RobotMap.PORT_SOLENOID_RELEASE_CHANNEL);
    private Solenoid _engage = new Solenoid(RobotMap.PORT_SOLENOID_ENGAGE_CHANNEL);
    //new talon
    public Catapult(){
        
    }
    protected void initDefaultCommand() {
        setDefaultCommand(new PullBack(0.0,0.0));
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
    
    public void release(boolean release){
        _release.set(release);
    }
    
    public void engage(boolean engage){
        _engage.set(engage);
    }
}
