/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.binary;

/**
 *
 * @author 1263849
 */
public abstract class Node {
    
    protected Node      parent;
    protected int       nChildren;
    protected boolean   isValid;
    
    private double      fitness;
    private NodeType    type;
    
    public abstract double  evaluate();
    public abstract int     count();
    public abstract Node    find(int i);
    
    public Node(Node p, NodeType t)
    {
        this.parent = p;
        this.nChildren = 0;
        this.isValid = false;
        this.fitness = 0;
        this.type = t;
    }
    
    public void invalidateCount()
    {
        isValid = false;
        if(parent != null)
            parent.invalidateCount();
    }
    
    public double getFitness()
    {
        return this.fitness;
    }
    
    public void setFitness(double fitness)
    {
        this.fitness = fitness;
    }
    
    public NodeType getType()
    {
        return this.type;
    }
}
