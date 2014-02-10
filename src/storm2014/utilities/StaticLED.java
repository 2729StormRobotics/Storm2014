/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.utilities;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 * @author evan1026
 */
public class StaticLED {
    
    private static final int FREQUENCY = 100;
    
    private DigitalOutput _out;
    
    private byte _value = 0;
    
    public StaticLED(int channel){
        _out = new DigitalOutput(channel);
        initDigitalOutput();
    }
    
    public StaticLED(int module, int channel){
        _out = new DigitalOutput(module, channel);
        initDigitalOutput();
    }
    
    private void initDigitalOutput(){
        _out.setPWMRate(FREQUENCY);
        _out.enablePWM(calculateDutyCycle(_value));
    }
    
    private void updateDutyCycle(){
        _out.updateDutyCycle(calculateDutyCycle(_value));
    }
    
    private double calculateDutyCycle(byte value){
        return (double) value / 255;
    }
    
    public void setValue(byte value){
        _value = value;
        updateDutyCycle();
    }
    
    public byte getValue(){
        return _value;
    }
    
    public int getFrequency(){
        return FREQUENCY;
    }
    
    public double getDutyCycle(){
        return calculateDutyCycle(_value);
    }
}
