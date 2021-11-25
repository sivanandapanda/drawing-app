package com.assignment.draw;

import com.assignment.draw.command.*;
import com.assignment.draw.exception.CommandNotFoundException;
import com.assignment.draw.exception.ExitException;
import com.assignment.draw.exception.InvalidCommandException;
import com.assignment.draw.service.ConsoleDrawingService;
import com.assignment.draw.session.SimpleDrawingSession;
import com.assignment.draw.util.DrawingUtils;
import com.assignment.siva.draw.command.*;

import java.util.Scanner;

public class DrawingApplication {

    public static void main(String[] args) throws InvalidCommandException {

        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        commandFactory.registerCommand("L", DrawLine::new);
        commandFactory.registerCommand("R", DrawRectangle::new);
        commandFactory.registerCommand("B", BucketFill::new);
        commandFactory.registerCommand("Q", Quit::new);

        var drawingSession = new SimpleDrawingSession(new ConsoleDrawingService(), commandFactory);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Welcome to the drawing app, Please enter one of the below commands to begin.\n" + DrawingUtils.getHelpText());
            while (true) {
                try {
                    drawingSession.draw(scanner.nextLine());
                } catch (InvalidCommandException | CommandNotFoundException e) {
                    System.err.println(e.getMessage() + " Please enter one of the below commands.\n" + DrawingUtils.getHelpText());
                } catch (ExitException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                } catch (Exception e) {
                    System.err.println("Unexpected error occurred, please try again with one of the below commands.\n" + DrawingUtils.getHelpText());
                }
            }
        }
    }
}