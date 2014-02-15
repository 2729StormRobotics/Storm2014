package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import storm2014.OI;
import storm2014.Robot;
import storm2014.commands.control.WaitForButton;
import storm2014.subsystems.Catapult;
//import storm2014.subsystems.LEDStrip;

// Tested by Joe Doyle on 2014 Robot on 2/15/14
public class PreLaunch extends CommandGroup {
    public PreLaunch() {
        addSequential(new SetEngagedRatchet(true));
        addSequential(new WaitForButton());
        addSequential(new SetWinchEngaged(true));
        addSequential(new WaitForButton());
        addSequential(new Command() {
            protected void initialize() {
                Robot.catapult.setFinishedPreLaunch(true);
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        addSequential(new WaitForButton());
//        addSequential(new PullBack(100));
    }
}
