/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.genetics;

import java.util.ArrayList;

/**
 *
 * @author 1263849
 */
public class BinaryPop extends Population{

    public ArrayList<String> myPop;
    
    public BinaryPop()
    {
        myPop = new ArrayList<>();
        setPopulationSize(250);
    }
    
    @Override
    public void generateUniquePopulation() {
        myChromosomes.clear();
        for(int i = 0; i < getPopulationSize(); i++)
        {
            BinaryIndi indi = new BinaryIndi();
            indi.generateRandomChromosome();
            myChromosomes.add(indi);
        }
        setGeneration(0);
    }
    
}
