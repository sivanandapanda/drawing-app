package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;
import com.assignment.draw.model.Canvas;

import java.util.Objects;

import static com.assignment.draw.util.DrawingUtils.isInteger;
import static java.lang.Integer.parseInt;

public class DrawCanvas extends AbstractDrawCommand {

    @Override
    public void drawShape(String[] commandTokens) {
        var width = parseInt(commandTokens[1]);
        var height = parseInt(commandTokens[2]);

        char[][] currentShape = new char[height+2][width+2];

        var shape = new Canvas(currentShape);
        this.setCanvas(shape);

        drawLine(0, 0, width + 1, 0, '-');
        drawLine(0, 1, 0, height + 1, '|');
        drawLine(width +1, 1, width + 1, height + 1, '|');
        drawLine(0, height + 1, width + 1, height + 1, '-');
    }

    @Override
    public void validate(String[] commandTokens) throws InvalidCommandException {
        if(Objects.nonNull(this.canvas)) {
            throw new InvalidCommandException("A Canvas is already created, please use other commands.");
        }

        if(commandTokens.length != 3) {
            throw new InvalidCommandException("Insufficient number of arguments passed for BucketFill command.");
        }

        if(!(isInteger(commandTokens[1]) && isInteger(commandTokens[2]))) {
            throw new InvalidCommandException("Invalid arguments passed for DrawCanvas command.");
        }

        if(parseInt(commandTokens[1]) <= 0 || parseInt(commandTokens[2]) <= 0) {
            throw new InvalidCommandException("Please provide valid coordinates to draw Canvas.");
        }
    }
}
