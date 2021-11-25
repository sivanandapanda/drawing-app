package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;

import java.util.Objects;

import static com.assignment.draw.util.DrawingUtils.isInteger;
import static java.lang.Integer.parseInt;

public class DrawRectangle extends AbstractDrawCommand {

    @Override
    public void drawShape(String[] commandTokens) {
        var x1 = Integer.parseInt(commandTokens[1]);
        var y1 = Integer.parseInt(commandTokens[2]);
        var x2 = Integer.parseInt(commandTokens[3]);
        var y2 = Integer.parseInt(commandTokens[4]);

        drawLine(x1, y1, x2, y1, 'x');
        drawLine(x1, y1, x1, y2, 'x');
        drawLine(x2, y1, x2, y2, 'x');
        drawLine(x1, y2, x2, y2, 'x');
    }

    @Override
    public void validate(String[] commandTokens) throws InvalidCommandException {
        if(Objects.isNull(this.canvas)) {
            throw new InvalidCommandException("Canvas has not yet been created, please create canvas first.");
        }

        if(commandTokens.length != 5) {
            throw new InvalidCommandException("Insufficient number of arguments passed for DrawRectangle command.");
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
            throw new InvalidCommandException("Please provide valid coordinates to draw rectangle.");
        }

        if(x1 == x2 || y1 == y2) {
            throw new InvalidCommandException("Please provide different points to draw rectangle.");
        }

        if(x1 > x2) {
            throw new InvalidCommandException("first point should be less than third point.");
        }

        if(y1 > y2) {
            throw new InvalidCommandException("second point should be less than fourth point.");
        }
    }
}
