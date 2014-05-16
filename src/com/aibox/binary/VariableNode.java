/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.binary;

/**
 *
 * @author Shane
 */
public class VariableNode extends Node{

    private VarContainer        theVar;
    private char                symbol;
    
    public VariableNode(Node p, VarContainer var)
    {
        super(p, NodeType.TERMINAL_VARIABLE);
        this.theVar = var;
    }
    
    @Override
    public double evaluate() {
        return this.theVar.getValue();
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
    
    public char getSymbol()
    {
        return this.symbol;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(this.theVar.getSymbol());
    }
    
}
