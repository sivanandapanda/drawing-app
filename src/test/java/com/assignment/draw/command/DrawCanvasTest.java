package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;
import com.assignment.draw.model.Canvas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawCanvasTest {
    @Test
    void should_draw_canvas_when_valid_arguments_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "4"});

        assertNotNull(drawCanvas.getCanvas());
        assertNotNull(drawCanvas.getCanvas().getCurrentShape());
        assertEquals(10, drawCanvas.getCanvas().getWidth());
        assertEquals(4, drawCanvas.getCanvas().getHeight());

        var shape = drawCanvas.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_not_draw_canvas_when_a_canvas_is_already_created() {
        var drawCanvas = new DrawCanvas();
        drawCanvas.setCanvas(new Canvas(new char[][]{}));
        assertThrows(InvalidCommandException.class, () -> drawCanvas.execute(new String[]{"C", "20", "10"}));
    }

    @Test
    void should_not_draw_canvas_when_insufficient_params_are_provided() {
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "20"}));
    }

    @Test
    void should_not_draw_canvas_when_invalid_width_is_provided() {
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "0", "10"}));
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "-1", "10"}));
    }

    @Test
    void should_not_draw_canvas_when_invalid_height_is_provided() {
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "1", "0"}));
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "1", "-1"}));
    }

    @Test
    void should_not_draw_canvas_when_non_numeric_height_or_width_is_provided() {
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "20", "a"}));
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "b", "10"}));
        assertThrows(InvalidCommandException.class, () -> new DrawCanvas().execute(new String[]{"C", "b", "z"}));
    }
}