package com.assignment.draw.session;

import com.assignment.draw.command.*;
import com.assignment.draw.exception.CommandNotFoundException;
import com.assignment.draw.exception.ExitException;
import com.assignment.draw.exception.InvalidCommandException;
import com.assignment.draw.model.Canvas;
import com.assignment.draw.service.DrawingService;
import com.assignment.siva.draw.command.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDrawingSessionTest {

    @Test
    void should_create_canvas_when_valid_arguments_are_provided() throws InvalidCommandException, CommandNotFoundException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);

        var drawingSession = new SimpleDrawingSession(shape -> {
            assertNotNull(shape);
            assertNotNull(shape.getCurrentShape());
            Assertions.assertEquals(4, shape.getHeight());
            Assertions.assertEquals(20, shape.getWidth());

            char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
                    {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                    {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                    {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                    {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                    {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};

            Assertions.assertArrayEquals(expected, shape.getCurrentShape());

        }, commandFactory);

        drawingSession.draw("C 20 4");
    }

    @Test
    void should_create_canvas_and_then_line_when_valid_arguments_are_provided() throws InvalidCommandException, CommandNotFoundException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        commandFactory.registerCommand("L", DrawLine::new);

        var drawingSession = new SimpleDrawingSession(new DrawingService() {
            int counter = 0;
            @Override
            public void draw(Canvas shape) {
                assertNotNull(shape);
                assertNotNull(shape.getCurrentShape());

                if(counter == 0) {
                    assertEquals(20, shape.getWidth());
                    assertEquals(4, shape.getHeight());
                } else if(counter == 1) {
                    char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
                            {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'|','x','x','x','x','x','x',0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
                    assertArrayEquals(expected, shape.getCurrentShape());
                } else {
                    char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
                            {'|',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'|','x','x','x','x','x','x',0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'|', 0,  0,  0,  0,  0, 'x',0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'|', 0,  0,  0,  0,  0, 'x',0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}}
                            ;
                    assertArrayEquals(expected, shape.getCurrentShape());
                }

                counter++;
            }
        }, commandFactory);

        drawingSession.draw("C 20 4");
        drawingSession.draw("L 1 2 6 2");
        drawingSession.draw("L 6 3 6 4");
    }

    @Test
    void should_create_canvas_and_then_line_and_then_rectangle_when_valid_arguments_are_provided() throws InvalidCommandException, CommandNotFoundException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        commandFactory.registerCommand("L", DrawLine::new);
        commandFactory.registerCommand("R", DrawRectangle::new);

        var drawingSession = new SimpleDrawingSession(new DrawingService() {
            int counter = 0;
            @Override
            public void draw(Canvas shape) {
                assertNotNull(shape);
                assertNotNull(shape.getCurrentShape());

                if(counter == 0) {
                    assertEquals(20, shape.getWidth());
                    assertEquals(4, shape.getHeight());
                } else if (counter == 3) {
                    char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
                            {'|', 0,  0,  0,  0,  0,  0, 0,0,0,0,0,0,0,'x','x','x','x','x',0,0,'|'},
                            {'|','x','x','x','x','x','x',0,0,0,0,0,0,0,'x', 0,  0,  0, 'x',0,0,'|'},
                            {'|', 0,  0,  0,  0,  0, 'x',0,0,0,0,0,0,0,'x','x','x','x','x',0,0,'|'},
                            {'|', 0,  0,  0,  0,  0, 'x',0,0,0,0,0,0,0,0,0,0,0,0,0,0,'|'},
                            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
                    assertArrayEquals(expected, shape.getCurrentShape());
                }

                counter++;
            }
        }, commandFactory);

        drawingSession.draw("C 20 4");
        drawingSession.draw("L 1 2 6 2");
        drawingSession.draw("L 6 3 6 4");
        drawingSession.draw("R 14 1 18 3");
    }

    @Test
    void should_create_canvas_and_then_line_and_then_rectangle_then_bucket_fill_when_valid_arguments_are_provided() throws InvalidCommandException, CommandNotFoundException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        commandFactory.registerCommand("L", DrawLine::new);
        commandFactory.registerCommand("R", DrawRectangle::new);
        commandFactory.registerCommand("B", BucketFill::new);

        var drawingSession = new SimpleDrawingSession(new DrawingService() {
            int counter = 0;
            @Override
            public void draw(Canvas shape) {
                assertNotNull(shape);
                assertNotNull(shape.getCurrentShape());

                if(counter == 0) {
                    assertEquals(20, shape.getWidth());
                    assertEquals(4, shape.getHeight());
                } else if (counter == 4) {
                    char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
                            {'|','A','A','A','A','A','A','A','A','A','A','A','A','A','x','x','x','x','x','A','A','|'},
                            {'|','x','x','x','x','x','x','A','A','A','A','A','A','A','x', 0,  0,  0, 'x','A','A','|'},
                            {'|', 0,  0,  0,  0,  0, 'x','A','A','A','A','A','A','A','x','x','x','x','x','A','A','|'},
                            {'|', 0,  0,  0,  0,  0, 'x','A','A','A','A','A','A','A','A','A','A','A','A','A','A','|'},
                            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
                    assertArrayEquals(expected, shape.getCurrentShape());
                }

                counter++;
            }
        }, commandFactory);

        drawingSession.draw("C 20 4");
        drawingSession.draw("L 1 2 6 2");
        drawingSession.draw("L 6 3 6 4");
        drawingSession.draw("R 14 1 18 3");
        drawingSession.draw("B 10 3 A");
    }

    @Test
    void should_create_canvas_and_then_bucket_fill_then_line_and_then_rectangle_when_valid_arguments_are_provided() throws InvalidCommandException, CommandNotFoundException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        commandFactory.registerCommand("L", DrawLine::new);
        commandFactory.registerCommand("R", DrawRectangle::new);
        commandFactory.registerCommand("B", BucketFill::new);

        var drawingSession = new SimpleDrawingSession(new DrawingService() {
            int counter = 0;
            @Override
            public void draw(Canvas shape) {
                assertNotNull(shape);
                assertNotNull(shape.getCurrentShape());

                if(counter == 0) {
                    assertEquals(20, shape.getWidth());
                    assertEquals(4, shape.getHeight());
                } else if (counter == 4) {
                    char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
                            {'|','O','O','O','O','O','O','O','O','O','O','O','O','O','x','x','x','x','x','O','O','|'},
                            {'|','x','x','x','x','x','x','O','O','O','O','O','O','O','x','O','O','O','x','O','O','|'},
                            {'|','O','O','O','O','O','x','O','O','O','O','O','O','O','x','x','x','x','x','O','O','|'},
                            {'|','O','O','O','O','O','x','O','O','O','O','O','O','O','O','O','O','O','O','O','O','|'},
                            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
                    assertArrayEquals(expected, shape.getCurrentShape());
                }

                counter++;
            }
        }, commandFactory);

        drawingSession.draw("C 20 4");
        drawingSession.draw("B 10 3 O");
        drawingSession.draw("L 1 2 6 2");
        drawingSession.draw("L 6 3 6 4");
        drawingSession.draw("R 14 1 18 3");
    }

    @Test
    void should_create_canvas_and_then_quit_when_valid_arguments_are_provided() throws InvalidCommandException, CommandNotFoundException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        commandFactory.registerCommand("Q", Quit::new);

        var drawingSession = new SimpleDrawingSession(new DrawingService() {
            int counter = 0;
            @Override
            public void draw(Canvas shape) {
                assertNotNull(shape);
                if(counter == 0) {
                    assertEquals(10, shape.getHeight());
                    assertEquals(20, shape.getWidth());
                }

                counter++;
            }
        }, commandFactory);

        drawingSession.draw("C 20 10");
        assertThrows(ExitException.class, () -> drawingSession.draw("q"));
    }

    @Test
    void should_not_draw_when_invalid_command_is_provided() {
        var drawingSession = new SimpleDrawingSession(shape -> System.out.println("Draw shape!"), new CommandFactory());
        assertThrows(CommandNotFoundException.class, () -> drawingSession.draw("A 20 10"));
    }

    @Test
    void should_not_draw_when_no_command_is_provided() {
        var drawingSession = new SimpleDrawingSession(shape -> System.out.println("Draw shape!"), new CommandFactory());
        assertThrows(InvalidCommandException.class, () -> drawingSession.draw(null));
    }

    @Test
    void should_fail_when_create_canvas_is_invoked_and_a_canvas_is_already_created() throws InvalidCommandException, CommandNotFoundException {
        var commandFactory = new CommandFactory();
        commandFactory.registerCommand("C", DrawCanvas::new);
        var drawingSession = new SimpleDrawingSession(shape -> System.out.println("Draw shape!"), commandFactory);
        drawingSession.draw("C 20 10");
        assertThrows(InvalidCommandException.class, () -> drawingSession.draw("C 10 10"));
    }
}