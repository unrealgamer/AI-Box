/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.neuralnet;

/**
 *
 * @author Shane Melton
 */
public class InputNeuron extends Neuron {

    private double value;
    
    public InputNeuron(double initValue)
    {
        super(null);
        this.value = initValue;
    }
    
    public InputNeuron()
    {
        super(null);
        this.value = 0;
    }
    
    @Override
    public double getOutputValue() {
       return this.value;
    }
    
    public void setInputValue(double val)
    {
        this.value = val;
    }
}
