/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storm2014.utilities;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * One static LED (bottom of the robot), i.e., either red, green, or blue.
 */
public class StaticLED {
    
    private static final int FREQUENCY = 100;
    
    private final DigitalOutput _out;
    
    private short _value = 0;
    
    /**
     * @param channel The channel for the DigitalOutput.
     */
    public StaticLED(int channel){
        _out = new DigitalOutput(channel);
        initDigitalOutput();
    }
    
    /**
     * @param module The module for the DigitalOutput.
     * @param channel The channel for the DigitalOutput.
     */
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
    
    /**
     * @param value From 0 to 255
     */
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
