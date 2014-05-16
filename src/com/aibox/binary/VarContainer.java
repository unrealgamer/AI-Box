/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.binary;

/**
 *
 * @author Shane
 */
public class VarContainer {
    
    private double  theValue;
    private char    theSymbol;
    
    public VarContainer(double v, char sym)
    {
        this.theValue = v;
        this.theSymbol = sym;
    }
    
    public double getValue()
    {
        return this.theValue;
    }
    
    public void setValue(double v)
    {
        this.theValue = v;
    }
    
    public char getSymbol()
    {
        return this.theSymbol;
    }
}
