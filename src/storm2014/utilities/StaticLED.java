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
    
    private short _value = 0;
    
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
    
    private double calculateDutyCycle(short value){
        if(value > 255) {
            value = 255;
        } else if(value < 0) {
            value = 0;
        }
        return (double) value / 255;
    }
    
    public void setValue(short value){
        _value = value;
        updateDutyCycle();
    }
    
    public short getValue(){
        return _value;
    }
    
    public int getFrequency(){
        return FREQUENCY;
    }
    
    public double getDutyCycle(){
        return calculateDutyCycle(_value);
    }
}
