/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.genetics;

import java.util.Random;

/**
 *
 * @author 1263849
 */
public class BinaryIndi extends Chromosome{

    private String data;
    private String desired = "11000000011111110000000000";
    
    public BinaryIndi()
    {
        data = "";
    }
    
    @Override
    public double getFitness() {
        
        double fitness = 0.0;
        
        for(int i = 0; i < data.length(); i++)
        {
            fitness += (data.charAt(i) == desired.charAt(i)) ? 1.0 : 0.0;
        }
        
        return (fitness / data.length());
    }

    @Override
    public void generateRandomChromosome() {
        Random rand = new Random();
        char[] d = new char[desired.length()];
        for(int i = 0; i < desired.length(); i++)
        {
            d[i] = ((rand.nextInt(2) == 1) ? '1' : '0');
        }
        data = String.valueOf(d);
    }

    @Override
    public void crossOver(Chromosome chromosome, double crossOverRate) {
        if(!(chromosome instanceof BinaryIndi))
        {
            return;
        }
        
        BinaryIndi other = (BinaryIndi)chromosome;
        Random rand = new Random();
        
        char[] myData = data.toCharArray();
        char[] otherData = other.getData().toCharArray();
        
        for(int i = 0; i < data.length(); i++)
        {
            if(rand.nextDouble() > crossOverRate)
            {
                char t = myData[i];
                myData[i] = otherData[i];
                otherData[i] = t;
            }
        }
        
        setData(String.valueOf(myData));
        other.setData(String.valueOf(otherData));
    }

    @Override
    public void mutate(double mutationRate) {
        char[] myData = data.toCharArray();
        Random rand = new Random();
        for(int i = 0; i < myData.length; i++)
        {
            if(rand.nextDouble() > mutationRate)
                myData[i] = ((myData[i] == '1') ? '0' : '1');
        }
        setData(String.valueOf(myData));
    }
    
    public String getData()
    {
        return this.data;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
    
    public String toString()
    {
        return "Fitness: " + (int)(getFitness()*100) + "% DATA: " + this.data;
    }
}
