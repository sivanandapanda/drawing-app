package com.assignment.draw.model;

import java.util.Objects;
import java.util.Stack;

public class Canvas {
    private final char[][] currentShape;

    public Canvas(char[][] currentShape) {
        this.currentShape = clone(currentShape);
    }

    private char[][] clone(char[][] currentShape) {
        char[][] newShape = new char[currentShape.length][currentShape[0].length];

        for (int i = 0; i < currentShape.length; i++) {
            System.arraycopy(currentShape[i], 0, newShape[i], 0, currentShape[i].length);
        }
        return newShape;
    }

    public char[][] getCurrentShape() {
        return clone(currentShape);
    }

    /*public void setCurrentShape(char[][] currentShape) {
        this.currentShape = currentShape;
    }*/

    public int getWidth() {
        return (Objects.nonNull(currentShape)) && getHeight() > 0 ? currentShape[0].length-2 : -1;
    }

    public int getHeight() {
        return (Objects.nonNull(currentShape)) ? currentShape.length-2 : -1;
    }
}
