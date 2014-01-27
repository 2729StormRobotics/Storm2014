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
import storm2014.commands.CatapultReload;

/**
 *
 * @author Matthew Rassmann
 */
public class Catapult extends Subsystem {
    //Encoders
    //Motor Puller back thing/ winch
    //release/Pneumatics
    Talon _winch;
    Encoder _winchEncoder =  new Encoder(RobotMap.PORT_ENCODER_RELOADENCODER_1,RobotMap.PORT_ENCODER_RELOADENCODER_2);
    Solenoid _release = new Solenoid(RobotMap.PORT_SOLENOID_RELEASE_CHANNEL);
    Solenoid _engage = new Solenoid(RobotMap.PORT_SOLENOID_ENGAGE_CHANNEL);
    //new talon
    public Catapult(){
        _winch = new Talon(RobotMap.PORT_MOTOR_WINCH);
    }
    protected void initDefaultCommand() {
        setDefaultCommand(new CatapultReload(0.0,0.0));
    }
    
    public void setWinch(Talon winch){
        _winch=winch;
    }
  
    public void setWinchEncoder(Encoder winchEncoder){
        _winchEncoder=winchEncoder;
    }
  
    public void setRelease(Solenoid release){
        _release=release;
    }
   
    public void setEngage(Solenoid engage){
        _engage=engage;
    }
    
    public Talon getWinch(){
        return _winch;
    }
  
    public Encoder getWinchEncoder(){
        return _winchEncoder;
    }
  
    public Solenoid getRelease(){
        return _release;
    }
   
    public Solenoid getEngage(){
        return _engage;
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
    
    public void setReleaseState(boolean release){
        _release.set(release);
    }
    
    public void setEngageState(boolean engage){
        _engage.set(engage);
    }
}
