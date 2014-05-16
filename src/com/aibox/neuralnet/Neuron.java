package com.aibox.neuralnet;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Shane M
 */
public class Neuron {
    
    private Neuron[]    inputNeurons;
    private double[]    weights;
    private double[]    momentum;
    
    public Neuron(Neuron[] inputs)
    {
        this.inputNeurons = inputs;
        if(inputs != null)
        {
            this.initRandomWeights();
            this.momentum = new double[this.inputNeurons.length];
            Arrays.fill(momentum, 1);
        }
    }
   
    private void initRandomWeights()
    {
        this.weights = new double[this.inputNeurons.length];
        
        Random rand = new Random();
        
        for(int i = 0; i < this.weights.length; i++)
           this.weights[i] = rand.nextDouble();
    }
    
    public double getOutputValue()
    {
        return 1 / ( 1 + Math.pow(Math.E, -(sumInputs())));
    }
    
    public void setWeight(int index, double weight)
    {
        if(index < 0 || index >= this.weights.length)
            throw new ArrayIndexOutOfBoundsException("The requested weight does not exists at index = " + index);
        this.weights[index] = weight;
    }
    
    public double getWeight(int index)
    {
        if(index < 0 || index >= this.weights.length)
            throw new ArrayIndexOutOfBoundsException("The requested weight does not exists at index = " + index);
        return this.weights[index];
    }
    
    public void setMomentum(int index, double momentum)
    {
        if(index < 0 || index >= this.momentum.length)
            throw new ArrayIndexOutOfBoundsException("The requested momentum does not exists at index = " + index);
        this.momentum[index] = momentum;
    }
    
    public double getMomentum(int index)
    {
        if(index < 0 || index >= this.momentum.length)
            throw new ArrayIndexOutOfBoundsException("The requested momentum does not exists at index = " + index);
        return this.momentum[index];
    }
    
    public double getInputAt(int index)
    {
        return this.inputNeurons[index].getOutputValue();
    }
    
    public int getNumInputs()
    {
        return this.inputNeurons.length;
    }
    
    private double sumInputs()
    {
        double t = 0;
        for(int i = 0; i < inputNeurons.length; i++)
        {
            t += inputNeurons[i].getOutputValue() * weights[i];
        }
        return t;
    }
    
    public String toString()
    {
        String out = "Neuron: {";
        if(inputNeurons != null)
        {
            for(int i = 0; i < inputNeurons.length; i++)
            {
                out += weights[i] + ", ";
            }
        }
        out += "} ";
        
        return out;
    }
}
