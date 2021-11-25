package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrawLineTest {

    @Test
    void should_not_draw_line_when_canvas_is_not_created() {
        assertThrows(InvalidCommandException.class, () -> new DrawLine().execute(new String[]{"L", "6", "3", "6", "8"}));
    }

    @Test
    void should_not_draw_line_when_insufficient_params_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "3", "6"}));
    }

    @Test
    void should_not_draw_line_when_non_numeric_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "a", "6", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "a", "3", "6", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "3", "a", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "3", "2", "a"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "c", "v", "b", "a"}));
    }

    @Test
    void should_not_draw_line_when_out_of_bound_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "16", "1", "6", "1"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "13", "6", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "3", "11", "3"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "3", "6", "12"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "1", "0", "1", "9"}));
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "7", "9", "0", "9"}));
    }

    @Test
    void should_not_draw_line_when_sloped_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());
        assertThrows(InvalidCommandException.class, () -> drawLine.execute(new String[]{"L", "6", "3", "7", "8"}));
    }

    @Test
    void should_draw_line_when_vertical_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());
        drawLine.execute(new String[]{"L", "6", "8", "9", "8"});

        var shape = drawLine.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x','x','x','x',0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_draw_line_when_horizontal_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());
        drawLine.execute(new String[]{"L", "6", "1", "6", "8"});

        var shape = drawLine.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_override_and_draw_line_when_horizontal_coordinates_are_provided_on_an_existing_line() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());
        drawLine.execute(new String[]{"L", "6", "1", "6", "8"});

        var drawLineOverridden = new DrawLine();
        drawLineOverridden.setCanvas(drawLine.getCanvas());
        drawLineOverridden.execute(new String[]{"L", "6", "5", "6", "10"});

        var shape = drawLineOverridden.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,'x',0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }
}