/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2014.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.communication.Semaphore;
import edu.wpi.first.wpilibj.communication.SemaphoreException;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.io.IOException;
import java.io.InputStream;
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
    public static final int OneWayPileMode        = 11;
    
    private static final byte _allianceBlue    = 0;
    private static final byte _allianceRed     = 1;
    private static final byte _allianceInvalid = 2;
    private static final byte _allianceError   = 3;

    private static final String _serverIP   = "socket://10.27.29.100:1025";

    private static int     _lastMode    = 0;
    private static int     _currentMode = 0; 
    private static byte    _red         = 0; //For setColorMode
    private static byte    _green       = 0; //For setColorMode
    private static byte    _blue        = 0; //For setColorMode

    private ITable table;
    
    private boolean disconnected = false;
    
    private Semaphore.Options _semaphoreOptions;
    private Semaphore         _semaphore;
    
    public LEDStrip(){
        //_semaphoreOptions.setDeleteSafe(true);
        //_semaphore = new Semaphore(_semaphoreOptions);
        _networkThread.start();
    }
    
    protected void initDefaultCommand() {}

    public void setMode(int mode){
        setMode(mode, (byte) 0, (byte) 0, (byte) 0);
    }
    public void setMode(int mode, byte red, byte green, byte blue){
        _currentMode = mode;
        _red   = red;
        _green = green;
        _blue  = blue;
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
    
    Thread _networkThread = new Thread() {
            public void run() {
                int tempMode = _currentMode;
                try {
                    SocketConnection sc = (SocketConnection) Connector.open(_serverIP);
                    if (tempMode != _lastMode){
                        OutputStream os = sc.openOutputStream();
                        
                        os.write(tempMode);
                        if (tempMode == SetColorMode){
                            os.write(_red);
                            os.write(_green);
                            os.write(_blue);
                        }
                        else if (tempMode == DisabledMode){
                            DriverStation.Alliance color = DriverStation.getInstance().getAlliance();
                            if (color == DriverStation.Alliance.kBlue){
                                os.write(_allianceBlue);
                            }
                            else if (color == DriverStation.Alliance.kRed){
                                os.write(_allianceRed);
                            }
                            else if (color == DriverStation.Alliance.kInvalid){
                                os.write(_allianceInvalid);
                            }
                            else{
                                os.write(_allianceError);
                            }
                        }

                        os.close();
                    }
                    
                    InputStream is = sc.openInputStream();
                    
                    int newMode = tempMode;
                    while (is.available() > 0){
                        byte input[] = new byte[is.available()];
                        int bytesRead = is.read(input);
                        
                        newMode = (int) input[bytesRead - 1];
                    }
                    
                    if (newMode != tempMode){
                        _lastMode    = newMode; //Because we've already taken care of the mode change, i.e. the change was from the arduino not from the dashboard, so we don't need to check that against _currentMode next time
                        _currentMode = newMode;
                    }
                    
                    
                    is.close();
                    sc.close();
                    if (table != null) table.putNumber("Mode", _currentMode);
                    System.out.println("Mode is now " + _currentMode);
                    disconnected = false;
                } catch (IOException ex) {
                    if (!disconnected){
                        ex.printStackTrace();
                        disconnected = true;
                    }
                }
            }
        };
}
