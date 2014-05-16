/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.binary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 1263849
 */
public class UnaryNode extends Node {
    
    public enum UnaryType
    {
        ABS('|') {
            @Override
            public double eval(double a)
            {
                return Math.abs(a);
            }
        },
        SIN('$') {
            @Override
            public double eval(double a)
            {
                return Math.sin(a);
            }
        },
        COS('C') {
            @Override
            public double eval(double a)
            {
                return Math.cos(a);
            }
        };
        
        public abstract double eval(double a);
        public char symbol;
        
        private UnaryType(char symbol)
        {
            this.symbol = symbol;
        }
        
        private static final List<UnaryType> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static UnaryType randomType()  {
          return VALUES.get(RANDOM.nextInt(SIZE));
        }
        
    }
    
    private UnaryType uType;
    
    private Node child;
    
    public UnaryNode(Node p, UnaryType uType)
    {
        super(p, NodeType.UNARY);
        this.child = null;
        this.uType = uType;
    }
    
    @Override
    public double evaluate() {
        if(child == null)
            throw new IllegalStateException("Child node is null during evaluation!");
        return uType.eval(child.evaluate());
    }

    @Override
    public int count() {
        if(isValid)
            return nChildren;
        
        nChildren = 0;
        
        if(child != null)
            nChildren += child.count();
        
        nChildren++;
        
        isValid = true;
        
        return nChildren;   
    }

    @Override
    public Node find(int i) {
        
        if(i == 1)
            return this;
        
        i--;
        
        if(child != null)
            return child.find(i);
        
        return null;
    }
    
    public void setChild(Node child) {
        this.child = child;
        invalidateCount();
    }
    
    @Override
    public String toString()
    {
        return this.uType.toString() + "(" + this.child.toString() + ")";
    }
    
}
