/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.binary;

import static com.aibox.binary.NodeType.values;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 1263849
 */
public class BinaryNode extends Node{

    public enum BinaryType
    {
        ADD('+') {
            @Override
            public double eval(double a, double b)
            {
                return a + b;
            }
        },
        SUB('-') {
            @Override
            public double eval(double a, double b)
            {
                return a - b;
            }
        },
        DIV('/') {
            @Override
            public double eval(double a, double b)
            {
                if (b == 0) 
                    return 0;
                return a / b;
            }
        },
        MULT('*') {
            @Override
            public double eval(double a, double b)
            {
                return a * b;
            }
        };
        
        public abstract double eval(double a, double b);
        public char symbol;
        
        private BinaryType(char symbol)
        {
            this.symbol = symbol;
        }
        
        private static final List<BinaryType> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static BinaryType randomType()  {
          return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
    
    private BinaryType  bType;
    
    private Node        leftNode;
    private Node        rightNode;
    
    public BinaryNode(Node p, BinaryType bType)
    {
        super(p, NodeType.BINARY);
        this.leftNode = this.rightNode = null;
        this.bType = bType;
    }
    
    @Override
    public double evaluate() {
        if(leftNode == null || rightNode == null)
            throw new IllegalStateException("Binary node has missing children during evaluation!");
        return bType.eval(leftNode.evaluate(), rightNode.evaluate());
    }

    @Override
    public int count() {
        if(isValid)
            return nChildren;
        
        nChildren = 0;
        
        if(leftNode != null)
            nChildren += leftNode.count();
        if(rightNode != null)
            nChildren += rightNode.count();
        
        nChildren++;
        
        isValid = true;
        
        return nChildren;
    }

    @Override
    public Node find(int i) {
        if(i == 1)
            return this;
        
        i--;
        
        if(leftNode != null && i < leftNode.count())
            return leftNode.find(i);
        
        if(leftNode != null)
            i -= leftNode.count();
        
        if(rightNode != null && i < rightNode.count())
            return rightNode.find(i);
        
        return null;
    }
    
    public void setLeft(Node l) {
        this.leftNode = l;
        invalidateCount();
    }
    
    public void setRight(Node r) {
        this.rightNode = r;
        invalidateCount();
    }
    
    @Override
    public String toString()
    {
        return "( " + this.leftNode.toString() + " " + this.bType.symbol + " " + this.rightNode.toString() + " )";
    }
    
}
