package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrawRectangleTest {

    @Test
    void should_not_draw_rectangle_when_canvas_is_not_created() {
        assertThrows(InvalidCommandException.class, () -> new DrawRectangle().execute(new String[]{"R", "6", "3", "6", "8"}));
    }

    @Test
    void should_not_draw_rectangle_when_insufficient_params_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "3", "6"}));
    }

    @Test
    void should_not_draw_rectangle_when_non_numeric_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "a", "6", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "a", "3", "6", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "3", "a", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "3", "2", "a"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "c", "v", "b", "a"}));
    }

    @Test
    void should_not_draw_rectangle_when_out_of_bound_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "16", "1", "6", "1"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "13", "6", "8"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "3", "11", "3"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "3", "6", "12"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "1", "0", "1", "9"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "7", "9", "0", "9"}));
    }

    @Test
    void should_not_draw_rectangle_when_first_point_is_far_right_of_second_point() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "1", "2", "9"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "1", "9", "6", "3"}));
    }

    @Test
    void should_not_draw_rectangle_when_identical_points_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "1", "6", "1"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "1", "6", "9"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "6", "9", "2", "9"}));
        assertThrows(InvalidCommandException.class, () -> drawRectangle.execute(new String[]{"R", "1", "1", "1", "1"}));
    }

    @Test
    void should_draw_rectangle_when_valid_parameters_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());

        drawRectangle.execute(new String[]{"R", "2", "1", "6", "9"});

        var shape = drawRectangle.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,'x','x','x','x','x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x','x','x','x','x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_redraw_rectangle_over_existing_rectangle_when_valid_parameters_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());
        drawRectangle.execute(new String[]{"R", "2", "1", "6", "9"});

        var drawRectangleOverridden = new DrawRectangle();
        drawRectangleOverridden.setCanvas(drawRectangle.getCanvas());
        drawRectangleOverridden.execute(new String[]{"R", "4", "3", "8", "6"});

        var shape = drawRectangleOverridden.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,'x','x','x','x','x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0, 'x','x','x','x','x',0,0,'|'},
                {'|',0,'x', 0, 'x', 0, 'x', 0, 'x',0,0,'|'},
                {'|',0,'x', 0, 'x', 0, 'x', 0, 'x',0,0,'|'},
                {'|',0,'x', 0, 'x','x','x','x','x',0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x', 0,  0,  0, 'x',0,0,0,0,'|'},
                {'|',0,'x','x','x','x','x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }
}