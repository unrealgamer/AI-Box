package com.aibox.neuralnet;

import java.util.Random;

/**
 *
 * @author Shane M
 */
public class NeuralNetwork {
    
    private InputNeuron[]       inputNeurons;
    private Neuron[][]          hiddenNeurons;
    private Neuron[]            outputNeurons;
    
    private double              errorThreshold;
    
    private static final int    TRAINING_LENGTH = 10000;
    private static final double LEARNING_RATE   = 1;
    
    /**
     * Create a new randomly weighted neural network
     * @param in The number of input neurons
     * @param hid The number of hidden neurons per layer
     * @param layers The number of hidden layers
     * @param out The number of output neurons
     * @param errorThreshold  The error threshold for learning
     */
    public NeuralNetwork(int in, int hid, int layers, int out, double errorThreshold)
    {
        this.inputNeurons = new InputNeuron[in];
        this.hiddenNeurons = new Neuron[layers][hid];
        this.outputNeurons = new Neuron[out];
        
        this.errorThreshold = errorThreshold;
        
        initNetwork();
    }
    
    private void initNetwork()
    {
        for(int i = 0; i < this.inputNeurons.length; i++)
        {
            this.inputNeurons[i] = new InputNeuron();
        }
        
        for(int l = 0; l < this.hiddenNeurons.length; l++)
        {
            for(int h = 0; h < this.hiddenNeurons[l].length; h++)
            {
                if(l == 0)
                    this.hiddenNeurons[l][h] = new Neuron(this.inputNeurons);
                else
                    this.hiddenNeurons[l][h] = new Neuron(this.hiddenNeurons[l-1]);
            }
        }
        
        //TEST
        /*this.hiddenNeurons[0][0].setWeight(0, 0.1);
        this.hiddenNeurons[0][0].setWeight(1, 0.8);
        
        this.hiddenNeurons[0][1].setWeight(0, 0.4);
        this.hiddenNeurons[0][1].setWeight(1, 0.6);*/
        
        for(int o = 0; o < this.outputNeurons.length; o++)
        {
            this.outputNeurons[o] = new Neuron(this.hiddenNeurons[this.hiddenNeurons.length-1]);
        }
        
       /* this.outputNeurons[0].setWeight(0, 0.3);
        this.outputNeurons[0].setWeight(1, 0.9);*/
    }
    
    public double[] runNetwork(double[] inputs)
    {
        if(inputs.length != this.inputNeurons.length)
            throw new IllegalArgumentException("Provided inputs do not match the dimensions of the netowrk!");
        for(int i = 0; i < inputs.length; i++)
            this.inputNeurons[i].setInputValue(inputs[i]);
        
        double[] output = new double[this.outputNeurons.length];
        
        for(int o = 0; o < this.outputNeurons.length; o++)
            output[o] = this.outputNeurons[o].getOutputValue();
        
        return output;
    }
    
    public void trainNetwork(double[][] inputs, double[][] outputs)
    {
        if(inputs.length != outputs.length || inputs.length == 0 || outputs.length == 0)
           throw new IllegalArgumentException("Training sets do not match in length or are empty!");
        
        int c = 0;
        double error = 0;
        
        do
        {
            Random rand = new Random();
            int i = rand.nextInt(inputs.length);
            int last = (i == 0) ? inputs.length-1 : i-1;
            boolean done = false;
            while(!done)
            {
                if(inputs[i].length != this.inputNeurons.length || outputs[i].length != this.outputNeurons.length)
                    throw new IllegalArgumentException("Training set ("+i+") does not match the dimensions of the network!");
                trainSet(inputs[i], outputs[i]);
                error += getError(inputs[i], outputs[i]);
                
                if(i == last)
                    done = true;
                if(i == inputs.length-1)
                    i = -1;
                i++;
            }
            c++;
            error /= inputs.length;
            
        } while(error > this.errorThreshold && c < TRAINING_LENGTH);
    }
    
    private double getError(double[] input, double[] dOutput)
    {  
        if (dOutput.length != this.outputNeurons.length)
            throw new IllegalArgumentException("Desired output does not match dimensions of the network!");
        
        double[] tOutput = runNetwork(input);
        
        double error = 0;
        
        for(int i = 0; i < tOutput.length; i++)
        {
            error += Math.abs(dOutput[i]-tOutput[i]);//Math.abs((tOutput[i])*(1.0-tOutput[i])*(dOutput[i]-tOutput[i]));
        }
        return error / (double)(tOutput.length);
    }
    
    private void trainSet(double[] input, double[] desiredOutput)
    {
        double[] output = runNetwork(input);
        double[] outputError = new double[output.length];
        
        for(int i = 0; i < output.length; i++)
        {
            outputError[i] = output[i] * (1 - output[i]) * (desiredOutput[i] - output[i]);
        }
        
        for(int i = 0; i < this.outputNeurons.length; i++)
        {
            Neuron out = this.outputNeurons[i];
            for(int j = 0; j < out.getNumInputs(); j++)
            {
                double deltaW = LEARNING_RATE * outputError[i] * out.getInputAt(j) + out.getMomentum(j) * .5;
                out.setWeight(j, out.getWeight(j) + deltaW);
                out.setMomentum(j, deltaW);
            }
        }
        
        double[] previousError = outputError;
        double[] currentError;// = new double[this.hiddenNeurons[this.hiddenNeurons.length-1].length];
        for(int layer = this.hiddenNeurons.length-1; layer >= 0; layer--)
        {
            currentError = new double[this.hiddenNeurons[layer].length];
            for(int i = 0; i < this.hiddenNeurons[layer].length; i++)
            {
                Neuron n = this.hiddenNeurons[layer][i];
                double neuronOut = n.getOutputValue();
                double neuronError = 0;
                if(layer == this.hiddenNeurons.length-1)
                    neuronError = neuronOut*(1-neuronOut)*(sumLayerWeights(i, outputNeurons, previousError));
                else
                    neuronError = neuronOut*(1-neuronOut)*(sumLayerWeights(i, this.hiddenNeurons[layer+1], previousError));
                
                for(int a = 0; a < n.getNumInputs(); a++)
                {
                    double deltaW = LEARNING_RATE * neuronError * n.getInputAt(a) + n.getMomentum(a) * .5;
                    n.setWeight(a, n.getWeight(a) + deltaW);
                    n.setMomentum(a, deltaW);
                }
                
                currentError[i] = neuronError;
            }
            previousError = currentError;
        }
        
    }
    
    private double sumLayerWeights(int currentNeuron, Neuron[] layer, double[] error)
    {
        double total = 0;
        for(int i = 0; i < layer.length; i++)
        {
            total += layer[i].getWeight(currentNeuron) * error[i];
        }
        return total;
    }
    
    public String toString()
    {
        String out = "Neural Network:\n";
        
        for(int i = 0; i < inputNeurons.length; i++)
            out += inputNeurons[i].toString() + "  ";
        out += "\n";
        for(int lay = 0; lay < hiddenNeurons.length; lay++)
            for(int i = 0; i < hiddenNeurons[lay].length; i++)
                out += hiddenNeurons[lay][i].toString() + "  ";
        out += "\n";
        for(int i = 0; i < outputNeurons.length; i++)
            out += outputNeurons[i].toString() + "  ";
        out += "\n";
        
        
        return out;
    }
    
}
