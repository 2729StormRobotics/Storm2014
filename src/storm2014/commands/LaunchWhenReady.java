/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2014.Robot;
/**
 *
 * @author Matthew
 */
public class LaunchWhenReady extends Command {
    Command _launch;
     boolean _hasRun;
    public LaunchWhenReady(Command launch){
        _launch = launch;
    }

    protected void initialize() {
        
    }

    protected void execute() {
         if (Robot.catapult.isFinishedPreLaunch()) {
            _launch.start();
            _hasRun =  true;
        }
    }

    protected boolean isFinished() {
         return _hasRun && !_launch.isRunning();
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
}
