package storm2014.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 * A class for interfacing with the addressable LED strip on the arms.
 */
public class LEDStrip extends Subsystem implements NamedSendable {

    public static final int DisabledMode          =  0;
    public static final int MarqueeMode           =  1;
    public static final int TeleopMode            =  2;
    public static final int PewMode               =  3;
    public static final int RainbowDancePartyMode =  4;
    public static final int StormSpiritMode       =  5;
    public static final int BounceMode            =  6;
    public static final int USAMode               =  7;
    public static final int SetColorMode          =  8;
    public static final int ParticleCollisionMode =  9;
    public static final int PileMode              = 10;
    public static final int OneWayPileMode        = 11;
    
    public static final int AutonomousMode        = USAMode;
    
    private static final byte _allianceBlue    = 0;
    private static final byte _allianceRed     = 1;
    private static final byte _allianceInvalid = 2;
    private static final byte _allianceError   = 3;

    private static final String _serverIP   = "socket://10.27.29.100:1025";

    private static int        _currentMode = 0;
    //private static final long _readTimeout = 5000;

    private ITable _table;
    
    private SocketConnection _socket;
    private OutputStream     _outstream;
    //private InputStream      _instream;
    
    private boolean _disconnected = false;
    private boolean _changing     = false;
    
    public LEDStrip() {}

    protected void initDefaultCommand() {}

    public void setMode(int mode){
        setMode(mode, (byte) 0, (byte) 0, (byte) 0);
    }
    /**
     * @param mode The LED mode to set
     * @param red The red value if the mode is SetColorMode
     * @param green The green value if the mode is SetColorMode
     * @param blue The blue value if the mode is SetColorMode
     */
    public void setMode(final int mode, final byte red, final byte green, final byte blue){
        new Thread() {
            boolean _changed = false;
            int timesRun = 0;
            public void run() {
                System.out.println("Run time: " + timesRun);
                while(!_changed){
                    if (!_changing){
                        _changing = true;
                        try {
                            _socket = (SocketConnection) Connector.open(_serverIP);
                            _outstream = _socket.openOutputStream();
                            //_instream  = _socket.openInputStream();

                            _outstream.write(mode);
                            if (mode == SetColorMode){
                                _outstream.write(red);
                                _outstream.write(green);
                                _outstream.write(blue);
                            }
                            else if (mode == TeleopMode){
                                DriverStation.Alliance color = DriverStation.getInstance().getAlliance();
                                if (color == DriverStation.Alliance.kBlue){
                                    _outstream.write(_allianceBlue);
                                }
                                else if (color == DriverStation.Alliance.kRed){
                                    _outstream.write(_allianceRed);
                                }
                                else if (color == DriverStation.Alliance.kInvalid){
                                    _outstream.write(_allianceInvalid);
                                }
                                else{
                                    _outstream.write(_allianceError);
                                }
                            }

                            /*long timeoutTime = System.currentTimeMillis() + _readTimeout;
                            int newMode = -1;
                            while(newMode == -1 && System.currentTimeMillis() < timeoutTime){
                                while (_instream.available() > 0){
                                    byte bytes[] = new byte[_instream.available()];
                                    int bytesRead = _instream.read(bytes);

                                    newMode = bytes[bytesRead - 1];
                                }
                            }*/

                            _outstream.close();
                            //_instream.close();
                            _socket.close();

                            _currentMode = mode;
                            _table.putNumber("Mode", _currentMode);
                            System.out.println("LEDMode was set to " + _currentMode);

                            /*if (_currentMode != newMode){
                                System.out.println("... Except that it's not. The LED strip kinda ignored the new mode. It's acutally " + newMode);
                            } else {
                                System.out.println(".");
                            }*/
                            _disconnected = false;
                        } catch (IOException ex) {
                            if (!_disconnected){
                                System.out.println("[ERROR] Arduino not responding.");
                                _disconnected = true;
                            }
                        }
                        _changing = false;
                        _changed = true;
                    }
                }
                timesRun++;
            }
        }.start();
    }

    public String getSmartDashboardType(){
        return "LEDStrip";
    }

    public void initTable(ITable table){
        this._table = table;
        if(table!=null){
            table.putNumber("Mode", DisabledMode);
            table.putNumber("Red", 0);
            table.putNumber("Green", 0);
            table.putNumber("Blue", 0);
            table.addTableListener(listener);
        }
    }

    private final ITableListener listener = new ITableListener() {
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
        return _table;
    }
}
