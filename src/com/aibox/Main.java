package com.aibox;

import com.aibox.binary.Population;
import java.util.ArrayList;


/**
 * 
 * @author Shane M
 */
public class Main {
    
    public static void main(String[] args)
    {
        //NeuralNetwork t = new NeuralNetwork(2,3,2,1,0.0001);
        /*double[][] inputs = {{0, 1}, {1, 1}, {1,0}, {0,0}};
        double[][] outputs = {{1}, {0}, {1}, {0}};
        double[] in = { 1, 1};
        for(int i = 0; i < 10; i++)
        {
            NeuralNetwork t = new NeuralNetwork(2,3,2,1,0.0001);
            t.trainNetwork(inputs, outputs);
            for(int a = 0; a < inputs.length; a++)
            {
                System.out.println("{"+inputs[a][0]+","+inputs[a][1]+"} = " + Math.round(t.runNetwork(inputs[a])[0]));
            }
            System.out.println("Iteration: " + i);
        }*/
        Population pop = new Population(50);
    }

    
}
