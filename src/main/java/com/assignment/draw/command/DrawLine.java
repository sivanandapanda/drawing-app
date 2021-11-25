package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;

import java.util.Objects;

import static com.assignment.draw.util.DrawingUtils.isInteger;
import static java.lang.Integer.parseInt;

public class DrawLine extends AbstractDrawCommand {

    @Override
    public void drawShape(String[] commandTokens) {
        drawLine(parseInt(commandTokens[1]), parseInt(commandTokens[2]), parseInt(commandTokens[3]), parseInt(commandTokens[4]), 'x');
    }

    @Override
    public void validate(String[] commandTokens) throws InvalidCommandException {
        if(Objects.isNull(this.canvas)) {
            throw new InvalidCommandException("Canvas has not yet been created, please create canvas first.");
        }

        if(commandTokens.length != 5) {
            throw new InvalidCommandException("Insufficient number of arguments passed for DrawLine command.");
        }

        if(!(isInteger(commandTokens[1]) && isInteger(commandTokens[2]) && isInteger(commandTokens[3]) && isInteger(commandTokens[4]))) {
            throw new InvalidCommandException("Invalid arguments passed for DrawCanvas command.");
        }

        var x1 = parseInt(commandTokens[1]);
        var y1 = parseInt(commandTokens[2]);
        var x2 = parseInt(commandTokens[3]);
        var y2 = parseInt(commandTokens[4]);

        if(x1 <= 0 || y1 <= 0 || x2 <= 0 || y2 <= 0 || x1 > this.canvas.getWidth() || x2 > this.canvas.getWidth()
                || y1 > this.canvas.getHeight() || y2 > this.canvas.getHeight()) {
            throw new InvalidCommandException("Please provide valid coordinates to draw line.");
        }

        if(!(y1 == y2 || x1 == x2)) {
            throw new InvalidCommandException("Drawing of sloped line is not supported");
        }
    }
}
