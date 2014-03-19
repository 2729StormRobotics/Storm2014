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
 * @author Tim
 */
public class WaitForEncoder extends Command{

    int _end;
    
    public WaitForEncoder(int encoderGoTo){
        _end = encoderGoTo;
    }
    
    protected void initialize() {}

    protected void execute() {}

    protected boolean isFinished() {
        return Robot.driveTrain.getLeftDistance() >= _end;
    }

    protected void end() {}

    protected void interrupted() {}
    
}
