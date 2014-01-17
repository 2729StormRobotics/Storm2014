/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 *
 * @author evan1026
 */



public class LEDStrip extends Subsystem {
    
    public static final int SolidWhiteMode        = 0;
    public static final int MarqueeMode           = 1;
    public static final int ColorCycleMode        = 2;
    public static final int PewMode               = 3;
    public static final int RainbowDancePartyMode = 4;
    public static final int StormSpiritMode       = 5;
    public static final int BounceMode            = 6;
    
    private static final String serverIP   = "10.27.29.100";
    private static final int    serverPort = 1025;
    
    public static int currentMode = 0;

    protected void initDefaultCommand() {}
    
    public void setMode(int mode){
        try {
            SocketConnection sc = (SocketConnection) Connector.open("socket://10.27.29.100:1025");
            OutputStream os = sc.openOutputStream();
            os.write(mode);
            os.close();
            sc.close();
            currentMode = mode;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
