package com.assignment.draw.command;

import com.assignment.draw.model.Canvas;

public class Transpose extends AbstractDrawCommand {
    @Override
    public void validate(String[] commandTokens) {
    }

    @Override
    public void drawShape(String[] commandTokens) {
        var currentCanvas = getCanvas();
        var currentShape = currentCanvas.getCurrentShape();

        var drawCanvas = new DrawCanvas();
        drawCanvas.drawShape(new String[]{"C", String.valueOf(currentCanvas.getHeight()), String.valueOf(currentCanvas.getWidth())});

        var newCanvas = drawCanvas.getCanvas();
        var newShape = newCanvas.getCurrentShape();

        for (int i = 1; i < currentShape[0].length - 1; i++) {
            for (int j = 1; j < currentShape.length - 1; j++) {
                newShape[i][j] = currentShape[j][i];
            }
        }

        setCanvas(new Canvas(newShape));
    }
}
