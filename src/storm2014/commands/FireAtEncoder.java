/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Tim
 */
public class FireAtEncoder extends Command{

    int _end;
    
    public FireAtEncoder(int encoderGoTo){
        _end = encoderGoTo;
    }
    
    protected void initialize() {
        
    }

    protected void execute() {
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void interrupted() {
        //throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
