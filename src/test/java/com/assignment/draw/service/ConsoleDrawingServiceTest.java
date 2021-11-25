package com.assignment.draw.service;

import com.assignment.draw.command.CommandFactory;
import com.assignment.draw.command.DrawCanvas;
import com.assignment.draw.exception.CommandNotFoundException;
import com.assignment.draw.exception.InvalidCommandException;
import com.assignment.draw.session.SimpleDrawingSession;
import org.junit.jupiter.api.Test;

class ConsoleDrawingServiceTest {

    @Test
    void should_draw_when_valid_shape_is_provided() throws InvalidCommandException, CommandNotFoundException {
        var drawingService = new ConsoleDrawingService();
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        var simpleDrawingSession = new SimpleDrawingSession(drawingService, commandFactory);

        simpleDrawingSession.draw("C 20 10");
    }
}