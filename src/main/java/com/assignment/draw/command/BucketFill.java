package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;

import java.util.Objects;

import static com.assignment.draw.util.DrawingUtils.isInteger;
import static java.lang.Integer.parseInt;

public class BucketFill extends AbstractDrawCommand {

    @Override
    public void drawShape(String[] commandTokens) {
        var x = parseInt(commandTokens[1]);
        var y = parseInt(commandTokens[2]);
        var fillChar = commandTokens[3].charAt(0);

        var currentShape = this.canvas.getCurrentShape();

        fill(x, y, fillChar, currentShape, currentShape[y][x]);
    }

    private void fill(int x, int y, char fillChar, char[][] shape, char currentChar) {
        if (x < 0 || y < 0 || x >= shape[0].length || y >= shape.length || shape[y][x] != currentChar) {
            return;
        }

        if (shape[y][x] == currentChar) {
            shape[y][x] = fillChar;
        }

        fill(x + 1, y, fillChar, shape, currentChar);
        fill(x - 1, y, fillChar, shape, currentChar);
        fill(x, y - 1, fillChar, shape, currentChar);
        fill(x, y + 1, fillChar, shape, currentChar);
    }

    @Override
    public void validate(String[] commandTokens) throws InvalidCommandException {
        if(Objects.isNull(this.canvas)) {
            throw new InvalidCommandException("Canvas has not yet been created, please create canvas first.");
        }

        if(commandTokens.length != 4) {
            throw new InvalidCommandException("Insufficient number of arguments passed for BucketFill command.");
        }

        if(!(isInteger(commandTokens[1]) && isInteger(commandTokens[2]) && commandTokens[3].length() == 1)) {
            throw new InvalidCommandException("Invalid arguments passed for BucketFill command.");
        }

        if(parseInt(commandTokens[1]) <= 0 || parseInt(commandTokens[2]) <= 0 ||
                parseInt(commandTokens[1]) > this.canvas.getWidth() || parseInt(commandTokens[2]) > this.canvas.getHeight()) {
            throw new InvalidCommandException("Please provide valid coordinates.");
        }
    }
}
