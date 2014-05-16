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
public enum NodeType {
    BINARY,
    UNARY,
    TERMINAL_CONSTANT,
    TERMINAL_VARIABLE;
    
    private static final List<NodeType> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static NodeType randomType()  {
      return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
