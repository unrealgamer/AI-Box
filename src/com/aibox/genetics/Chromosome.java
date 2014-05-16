package com.aibox.genetics;

/**
 * A base class that is used to represent an individual chromosome in a population for use with a genetic algorithm.
 * User: Shane
 * Date: 5/15/14
 */
public abstract class Chromosome {

    /**
     * Returns this chromosome's fitness
     * @return This chromosome's fitness
     */
    public abstract double getFitness();

    /**
     * Generates a random chromosome for use in a population.
     */
    public abstract void generateRandomChromosome();

    /**
     * Crosses over this chromosome with another based on a crossOverRate. Both this chromosome and the other will be modified
     * after this method's execution.
     * @param chromosome The other chromosome to cross over with
     * @param crossOverRate The rate a which cross overs should occur, typically between 0.0 and 1.0
     */
    public abstract void crossOver(Chromosome chromosome, double crossOverRate);

    /**
     * Mutates this chromosome based on the mutation rate. Typically mutations should not occur too frequently.
     * @param mutationRate The rate a which this chromosome will mutate, typically between 0.0 and 1.0
     */
    public abstract void mutate(double mutationRate);
}
