/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2014.RobotMap;
import storm2014.commands.CatapultReload;

/**
 *
 * @author Matthew Rassmann
 */
public class Catapult extends Subsystem {
    //Encoders
    //Motor Puller back thing
    //release
    Encoder _reloadEncoder =  new Encoder(RobotMap.PORT_ENCODER_RELOADENCODER_1,RobotMap.PORT_ENCODER_RELOADENCODER_2);
    public Catapult(){
        
    }
    protected void initDefaultCommand() {
        setDefaultCommand(new CatapultReload());
    }
    
}
