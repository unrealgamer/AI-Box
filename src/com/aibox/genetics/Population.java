package com.aibox.genetics;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Shane
 * Date: 5/15/14
 */
public abstract class Population {

    protected ArrayList<Chromosome> myChromosomes;

    private int myPopulationSize = 50;

    private boolean isElitism = true;

    private double crossOverRate = 0.7;
    private double mutationRate = .01;
    private double varMutationRate = .01;

    private int myGeneration = 0;

    /**
     * Default constructor; instantiates the chromosome array list.
     */
    public Population()
    {
        this.myChromosomes = new ArrayList<Chromosome>();
    }

    /**
     * Constructs the population with the provided parameters.
     * @param popSize The size of this population
     * @param elitism Will this population used elitism. (Preserving the fittest member through each generation)
     * @param crossOverRate The rate at which crossOvers will occur. [0.0 , 1.0]
     * @param mutationRate The rate at which mutations will occur. [0.0 , 1.0]
     */
    public Population(int popSize, boolean elitism, double crossOverRate, double mutationRate)
    {
        this.myPopulationSize = popSize;
        this.isElitism = elitism;
        this.crossOverRate = crossOverRate;
        this.mutationRate = mutationRate;
        this.varMutationRate = mutationRate;
        this.myChromosomes = new ArrayList<Chromosome>();
    }

    /**
     * Evolves this population on generation
     */
    public void progressToNextEpoch()
    {
        ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
        Chromosome fittest = null;
        
        if(isElitism)
        {
            fittest = getFittestChromosome();
            newPopulation.add(fittest);
            myChromosomes.remove(fittest);
        }

        while(!myChromosomes.isEmpty())
        {
            Chromosome one = rouletteSelection();
            Chromosome two = rouletteSelection();

            if(two == null && one != null)
            {
                one.mutate(mutationRate);
                newPopulation.add(one);
                continue;
            }

            one.crossOver(two,crossOverRate);
            one.mutate(mutationRate);
            two.mutate(mutationRate);

            newPopulation.add(one);
            newPopulation.add(two);
        }

        myChromosomes = newPopulation;
        
        if(isElitism)
        {
            if(fittest == getFittestChromosome() && varMutationRate <= 1)
                varMutationRate += .01;
            else
                varMutationRate = mutationRate;
        }

        myGeneration++;
    }

    /**
     * Selects a member of the population using rouletteSelection and removes it from the population.
     * @return The member of the population that was selected and removed
     */
    public Chromosome rouletteSelection()
    {
        if(myChromosomes.isEmpty())
            return null;

        double tot = getTotalFitness();
        double slice = tot * Math.random();

        double tempTot = 0.0;
        for(int i = myChromosomes.size()-1; i >= 0; i--)
        {
            tempTot += myChromosomes.get(i).getFitness();
            if(tempTot >= slice)
                return myChromosomes.remove(i);
        }
        return myChromosomes.remove(myChromosomes.size()-1);
    }

    /**
     * Calculates the population's total fitness
     * @return The population's total fitness
     */
    public double getTotalFitness()
    {
        double tot = 0.0;
        for(Chromosome chromosome : myChromosomes)
            tot += chromosome.getFitness();
        return tot;
    }

    /**
     * Searches through the population to find the fittest member
     * @return The fittest member of the population
     */
    public Chromosome getFittestChromosome()
    {
        Chromosome best = myChromosomes.get(0);
        for(Chromosome chromosome : myChromosomes)
            if(chromosome.getFitness() > best.getFitness())
                best = chromosome;
        return best;
    }

    /**
     * Generates a unique population with unique members filling the myChromosomes array with valid chromosomes
     */
    public abstract void generateUniquePopulation();

    //***********************************************
    //              GETTERS AND SETTERS
    //***********************************************

    public int getGeneration()
    {
        return this.myGeneration;
    }

    public void setGeneration(int myGeneration)
    {
        this.myGeneration = myGeneration;
    }

    public void incrementGeneration()
    {
        this.myGeneration++;
    }

    public int getPopulationSize()
    {
        return this.myPopulationSize;
    }

    public void setPopulationSize(int populationSize)
    {
        this.myPopulationSize = populationSize;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public double getCrossOverRate() {
        return crossOverRate;
    }

    public void setCrossOverRate(double crossOverRate) {
        this.crossOverRate = crossOverRate;
    }

    public boolean isElitism() {
        return isElitism;
    }

    public void setElitism(boolean elitism) {
        isElitism = elitism;
    }

}
