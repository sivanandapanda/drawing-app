package com.assignment.draw.session;

import com.assignment.draw.command.CommandFactory;
import com.assignment.draw.command.DrawCommand;
import com.assignment.draw.exception.CommandNotFoundException;
import com.assignment.draw.exception.InvalidCommandException;
import com.assignment.draw.model.Canvas;
import com.assignment.draw.service.DrawingService;

import java.util.Objects;
import java.util.Stack;

public class SimpleDrawingSession implements DrawingSession {

    private Canvas canvas = null;
    private final DrawingService drawingService;
    private final CommandFactory commandFactory;

    private final Stack<Canvas> previousVersions = new Stack<>();

    public SimpleDrawingSession(DrawingService drawingService, CommandFactory commandFactory) {
        this.drawingService = drawingService;
        this.commandFactory = commandFactory;
    }

    @Override
    public void draw(String commandString) throws InvalidCommandException, CommandNotFoundException {
        if (Objects.isNull(commandString) || commandString.isEmpty()) {
            throw new InvalidCommandException("No command string passed to draw.");
        }

        String[] tokens = commandString.split(" ");

        var command = commandFactory.getCommand(tokens[0])
                .orElseThrow(() -> new CommandNotFoundException("Command '" + commandString + "' not supported."));

        if (command instanceof DrawCommand) {
            DrawCommand drawCommand = (DrawCommand) command;
            drawCommand.setCanvas(canvas);
            drawCommand.execute(tokens);
            canvas = drawCommand.getCanvas();

            drawingService.draw(canvas);
        } else {
            command.execute(tokens);
        }
    }
}