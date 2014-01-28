/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 *
 * @author evan1026
 */
public class LEDStrip extends Subsystem implements NamedSendable {

    public static final int DisabledMode          =  0;
    public static final int MarqueeMode           =  1;
    public static final int ColorCycleMode        =  2;
    public static final int PewMode               =  3;
    public static final int RainbowDancePartyMode =  4;
    public static final int StormSpiritMode       =  5;
    public static final int BounceMode            =  6;
    public static final int USAMode               =  7;
    public static final int SetColorMode          =  8;
    public static final int ParticleCollisionMode =  9;
    public static final int PileMode              = 10;

    private static final String serverIP   = "socket://10.27.29.100:1025";

    public static int currentMode = 0;

    private ITable table;
    
    private boolean disconnected = false;

    protected void initDefaultCommand() {}

    public void setMode(int mode){
        setMode(mode, (byte) 0, (byte) 0, (byte) 0);
    }
    public void setMode(final int mode, final byte red, final byte green, final byte blue){
        new Thread() {
            public void run() {
                try {
                    SocketConnection sc = (SocketConnection) Connector.open(serverIP);
                    OutputStream os = sc.openOutputStream();
                    os.write(mode);
                    if (mode == SetColorMode){
                        os.write(red);
                        os.write(green);
                        os.write(blue);
                    }
                    else if (mode == DisabledMode){
                        DriverStation.Alliance color = DriverStation.getInstance().getAlliance();
                        if (color == DriverStation.Alliance.kBlue){
                            os.write('b');
                        }
                        else if (color == DriverStation.Alliance.kRed){
                            os.write('r');
                        }
                        else if (color == DriverStation.Alliance.kInvalid){
                            os.write('i');
                        }
                        else{
                            os.write('e');
                        }
                    }

                    os.close();
                    sc.close();
                    currentMode = mode;
                    table.putNumber("Mode", currentMode);
                    System.out.println("Mode is now " + currentMode);
                    disconnected = false;
                } catch (IOException ex) {
                    if (!disconnected){
                        ex.printStackTrace();
                        disconnected = true;
                    }
                }
            }
        }.start();
    }

    public String getSmartDashboardType(){
        return "LEDStrip";
    }

    public void initTable(ITable table){
        this.table = table;
        if(table!=null){
            table.putNumber("Mode", DisabledMode);
            table.putNumber("Red", 0);
            table.putNumber("Green", 0);
            table.putNumber("Blue", 0);
            table.addTableListener(listener);
        }
    }

    private ITableListener listener = new ITableListener() {
        public void valueChanged(ITable table, String key, Object value, boolean isNew) {
            if (key.equals("Mode")) {
                int newMode = (int) table.getNumber("Mode");
                if (newMode != SetColorMode) setMode(newMode);
                else {
                    int red    = (int) table.getNumber("Red");
                    int green  = (int) table.getNumber("Green");
                    int blue   = (int) table.getNumber("Blue");

                    if (red > 255) red = 255;
                    if (red < 0)   red = 0;

                    if (green > 255) green = 255;
                    if (green < 0)   green = 0;

                    if (blue > 255) blue = 255;
                    if (blue < 0)   blue = 0;

                    setMode(newMode, (byte) red, (byte) green, (byte) blue);
                }
            }
            if (key.equals("Red") || key.equals("Green") || key.equals("Blue")){
                if ((int) table.getNumber("Mode") == SetColorMode){
                    int red    = (int) table.getNumber("Red");
                    int green  = (int) table.getNumber("Green");
                    int blue   = (int) table.getNumber("Blue");

                    if (red > 255) red = 255;
                    if (red < 0)   red = 0;

                    if (green > 255) green = 255;
                    if (green < 0)   green = 0;

                    if (blue > 255) blue = 255;
                    if (blue < 0)   blue = 0;

                    setMode(SetColorMode, (byte) red, (byte) green, (byte) blue);
                }
            }
        }
    };

    public ITable getTable(){
        return table;
    }
}
