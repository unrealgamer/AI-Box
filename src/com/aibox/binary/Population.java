/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aibox.binary;

import java.util.Random;


/**
 *
 * @author 1263849
 */
public class Population {
    
    private Node[]                  trees;
    private int                     popSize = 50;
    
    private VarContainer[]          variables;
    
    public Population(int popSize)
    {
        this.popSize = popSize;
        this.trees = new Node[this.popSize];
        
        variables = new VarContainer[2];
        variables[0] = new VarContainer(1, 'X');
        variables[1] = new VarContainer(10, 'Y');
        
        for(int i = 0; i < this.popSize; i++)
        {
            this.trees[i] = new BinaryNode(null, BinaryNode.BinaryType.randomType());
            if(this.trees[i].getType() == NodeType.BINARY)
                buildTree((BinaryNode)this.trees[i]);
            else if(this.trees[i].getType() == NodeType.UNARY)
                buildTree((UnaryNode)this.trees[i]);
            System.out.println(trees[i] + " = " + trees[i].evaluate());
            
        }
        
    }
    
    private Node createNode(Node parent)
    {
        NodeType type = NodeType.randomType();
        
        Node newNode = null;
        
        switch(type)
        {
            case BINARY:
            {
                newNode = new BinaryNode(parent, BinaryNode.BinaryType.randomType());
                buildTree((BinaryNode)newNode);
            }break;
            case UNARY:
            {
                newNode = new UnaryNode(parent, UnaryNode.UnaryType.randomType());
                buildTree((UnaryNode)newNode);
            }break;
            case TERMINAL_VARIABLE:
            {
                newNode = new VariableNode(parent, getRandomVar());
            }break;
            case TERMINAL_CONSTANT:
            {
                newNode = new ConstantNode(parent, getRandomConstant());
            }break;
        }
        return newNode;
    }
    
    private void buildTree(BinaryNode parent)
    {
        parent.setLeft(createNode(parent));
        parent.setRight(createNode(parent));
    }
    
    private void buildTree(UnaryNode parent)
    {
        parent.setChild(createNode(parent));
    }
    
    private double getRandomConstant()
    {
        return Math.round(new Random().nextDouble() * 100) / 100.0;
    }
    
    private VarContainer getRandomVar()
    {
        return variables[new Random().nextInt(variables.length)];
    }
    
    
}
