package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;
import com.assignment.draw.model.Canvas;

import java.util.Arrays;

abstract class AbstractDrawCommand implements DrawCommand {

    protected Canvas canvas = null;

    @Override
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void execute(String[] commandTokens) throws InvalidCommandException {
        validate(commandTokens);
        drawShape(commandTokens);
    }

    public abstract void validate(String[] commandTokens) throws InvalidCommandException;
    public abstract void drawShape(String[] commandTokens);

    protected void drawLine(int x1, int y1, int x2, int y2, char aChar) {
        char[][] currentShape = canvas.getCurrentShape();
        if (x1 == x2) { // vertical line
            for (int i = y1; i <= y2; i++) {
                currentShape[i][x1] = aChar;
            }
        } else { // horizontal line
            Arrays.fill(currentShape[y1], x1, x2 + 1, aChar);
        }

        canvas = new Canvas(currentShape);
    }
}
