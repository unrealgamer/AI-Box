/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.binary;

/**
 *
 * @author Shane
 */
public class ConstantNode extends Node {

    private final double theValue;
    
    public ConstantNode(Node p, double value)
    {
        super(p, NodeType.TERMINAL_CONSTANT);
        this.theValue = value;
    }
    
    @Override
    public double evaluate() {
        return this.theValue;
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public Node find(int i) {
        if(i == 1)
            return this;
        return null;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(this.theValue);
    }
    
}
