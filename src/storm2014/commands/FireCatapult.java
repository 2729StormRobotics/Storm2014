/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
import storm2014.RobotMap;

/**
 *
 * @author Tim
 */
public class FireCatapult extends Command{
    
    private final boolean _fire;
    
    //true fires the solenoid in reverse, which should fire the catapult
    
    public FireCatapult(boolean fire){
        requires(Robot.catapult);
        _fire = fire;
    }
    
    protected void initialize() {
        if(_fire){
            Robot.catapult.fireCatapult();
        }else{
            Robot.catapult.resetCatapult();
        }
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {}
    protected void interrupted() {}
}
