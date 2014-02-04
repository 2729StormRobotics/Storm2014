/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Erik
 */
public class TurnAndTurnBack extends CommandGroup {
    
    public TurnAndTurnBack(double power, double distance, double angle) {
        addSequential(new Turn(angle, power));
        addSequential(new WaitCommand(5));
        addSequential(new Turn(-angle, power));
    }
}