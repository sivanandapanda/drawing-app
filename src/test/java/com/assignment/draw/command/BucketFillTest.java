package com.assignment.draw.command;

import com.assignment.draw.exception.InvalidCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BucketFillTest {

    @Test
    void should_not_fill_when_canvas_is_not_created() {
        assertThrows(InvalidCommandException.class, () -> new BucketFill().execute(new String[]{"B", "6", "3", "a"}));
    }

    @Test
    void should_not_fill_when_insufficient_params_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawCanvas.getCanvas());
        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "6", "3"}));
    }

    @Test
    void should_not_fill_when_invalid_parameters_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "6", "a", "6"}));
        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "a", "3", "o"}));
        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "6", "3", ""}));
        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "6", "3", "aa"}));
        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "6", "3", "11"}));
    }

    @Test
    void should_not_fill_when_out_of_bound_coordinates_are_provided() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawCanvas.getCanvas());

        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "16", "1", "o"}));
        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "6", "13", "o"}));
        assertThrows(InvalidCommandException.class, () -> bucketFill.execute(new String[]{"B", "61", "31", "o"}));
    }

    @Test
    void should_fill_on_empty_canvas() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawCanvas.getCanvas());
        bucketFill.execute(new String[]{"B", "1", "1", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_with_another_char_when_canvas_is_already_filled() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawCanvas.getCanvas());
        bucketFill.execute(new String[]{"B", "1", "1", "o"});

        var bucketFillOverridden = new BucketFill();
        bucketFillOverridden.setCanvas(bucketFill.getCanvas());
        bucketFillOverridden.execute(new String[]{"B", "1", "1", "A"});

        var shape = bucketFillOverridden.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'|','A','A','A','A','A','A','A','A','A','A','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_when_a_point_inside_rectangle_is_selected() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());
        drawRectangle.execute(new String[]{"R", "2", "1", "6", "9"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawRectangle.getCanvas());
        bucketFill.execute(new String[]{"B", "3", "2", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,'x','x','x','x','x',0,0,0,0,'|'},
                {'|',0,'x','o','o','o','x',0,0,0,0,'|'},
                {'|',0,'x','o','o','o','x',0,0,0,0,'|'},
                {'|',0,'x','o','o','o','x',0,0,0,0,'|'},
                {'|',0,'x','o','o','o','x',0,0,0,0,'|'},
                {'|',0,'x','o','o','o','x',0,0,0,0,'|'},
                {'|',0,'x','o','o','o','x',0,0,0,0,'|'},
                {'|',0,'x','o','o','o','x',0,0,0,0,'|'},
                {'|',0,'x','x','x','x','x',0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_when_a_point_outside_rectangle_is_selected() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());
        drawRectangle.execute(new String[]{"R", "2", "1", "6", "9"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawRectangle.getCanvas());
        bucketFill.execute(new String[]{"B", "1", "1", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|','o','x','x','x','x','x','o','o','o','o','|'},
                {'|','o','x', 0,  0,  0, 'x','o','o','o','o','|'},
                {'|','o','x', 0,  0,  0, 'x','o','o','o','o','|'},
                {'|','o','x', 0,  0,  0, 'x','o','o','o','o','|'},
                {'|','o','x', 0,  0,  0, 'x','o','o','o','o','|'},
                {'|','o','x', 0,  0,  0, 'x','o','o','o','o','|'},
                {'|','o','x', 0,  0,  0, 'x','o','o','o','o','|'},
                {'|','o','x', 0,  0,  0, 'x','o','o','o','o','|'},
                {'|','o','x','x','x','x','x','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_when_a_point_on_rectangle_is_selected() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawRectangle = new DrawRectangle();
        drawRectangle.setCanvas(drawCanvas.getCanvas());
        drawRectangle.execute(new String[]{"R", "2", "1", "6", "9"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawRectangle.getCanvas());
        bucketFill.execute(new String[]{"B", "2", "2", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,'o','o','o','o','o',0,0,0,0,'|'},
                {'|',0,'o', 0,  0,  0, 'o',0,0,0,0,'|'},
                {'|',0,'o', 0,  0,  0, 'o',0,0,0,0,'|'},
                {'|',0,'o', 0,  0,  0, 'o',0,0,0,0,'|'},
                {'|',0,'o', 0,  0,  0, 'o',0,0,0,0,'|'},
                {'|',0,'o', 0,  0,  0, 'o',0,0,0,0,'|'},
                {'|',0,'o', 0,  0,  0, 'o',0,0,0,0,'|'},
                {'|',0,'o', 0,  0,  0, 'o',0,0,0,0,'|'},
                {'|',0,'o','o','o','o','o',0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_when_a_vertical_line_is_drawn_and_a_point_is_selected() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLine = new DrawLine();
        drawLine.setCanvas(drawCanvas.getCanvas());
        drawLine.execute(new String[]{"L", "6", "8", "9", "8"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawLine.getCanvas());
        bucketFill.execute(new String[]{"B", "1", "1", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','x','x','x','x','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_when_a_vertical_and_horizontal_line_is_drawn_and_a_point_outside_is_selected() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLineHorizontal = new DrawLine();
        drawLineHorizontal.setCanvas(drawCanvas.getCanvas());
        drawLineHorizontal.execute(new String[]{"L", "6", "8", "10", "8"});

        var drawLineVertical = new DrawLine();
        drawLineVertical.setCanvas(drawLineHorizontal.getCanvas());
        drawLineVertical.execute(new String[]{"L", "6", "1", "6", "8"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawLineVertical.getCanvas());
        bucketFill.execute(new String[]{"B", "1", "1", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|','o','o','o','o','o','x', 0,  0,  0,  0,'|'},
                {'|','o','o','o','o','o','x', 0,  0,  0,  0,'|'},
                {'|','o','o','o','o','o','x', 0,  0,  0,  0,'|'},
                {'|','o','o','o','o','o','x', 0,  0,  0,  0,'|'},
                {'|','o','o','o','o','o','x', 0,  0,  0,  0,'|'},
                {'|','o','o','o','o','o','x', 0,  0,  0,  0,'|'},
                {'|','o','o','o','o','o','x', 0,  0,  0,  0,'|'},
                {'|','o','o','o','o','o','x','x','x','x','x','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'|','o','o','o','o','o','o','o','o','o','o','|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_when_a_vertical_and_horizontal_line_is_drawn_and_a_point_inside_is_selected() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLineHorizontal = new DrawLine();
        drawLineHorizontal.setCanvas(drawCanvas.getCanvas());
        drawLineHorizontal.execute(new String[]{"L", "6", "8", "10", "8"});

        var drawLineVertical = new DrawLine();
        drawLineVertical.setCanvas(drawLineHorizontal.getCanvas());
        drawLineVertical.execute(new String[]{"L", "6", "1", "6", "8"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawLineVertical.getCanvas());
        bucketFill.execute(new String[]{"B", "7", "2", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());

        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,0,0,0,0,'x','o','o','o','o','|'},
                {'|',0,0,0,0,0,'x','o','o','o','o','|'},
                {'|',0,0,0,0,0,'x','o','o','o','o','|'},
                {'|',0,0,0,0,0,'x','o','o','o','o','|'},
                {'|',0,0,0,0,0,'x','o','o','o','o','|'},
                {'|',0,0,0,0,0,'x','o','o','o','o','|'},
                {'|',0,0,0,0,0,'x','o','o','o','o','|'},
                {'|',0,0,0,0,0,'x','x','x','x','x','|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }

    @Test
    void should_fill_when_a_vertical_and_horizontal_line_is_drawn_and_a_point_online_is_selected() throws InvalidCommandException {
        var drawCanvas = new DrawCanvas();
        drawCanvas.execute(new String[]{"C", "10", "10"});

        var drawLineHorizontal = new DrawLine();
        drawLineHorizontal.setCanvas(drawCanvas.getCanvas());
        drawLineHorizontal.execute(new String[]{"L", "6", "8", "10", "8"});

        var drawLineVertical = new DrawLine();
        drawLineVertical.setCanvas(drawLineHorizontal.getCanvas());
        drawLineVertical.execute(new String[]{"L", "6", "1", "6", "8"});

        var bucketFill = new BucketFill();
        bucketFill.setCanvas(drawLineVertical.getCanvas());
        bucketFill.execute(new String[]{"B", "6", "2", "o"});

        var shape = bucketFill.getCanvas();
        assertNotNull(shape);
        assertNotNull(shape.getCurrentShape());



        char[][] expected = {{'-','-','-','-','-','-','-','-','-','-','-','-'},
                {'|',0,0,0,0,0,'o', 0,  0,  0,  0,'|'},
                {'|',0,0,0,0,0,'o', 0,  0,  0,  0,'|'},
                {'|',0,0,0,0,0,'o', 0,  0,  0,  0,'|'},
                {'|',0,0,0,0,0,'o', 0,  0,  0,  0,'|'},
                {'|',0,0,0,0,0,'o', 0,  0,  0,  0,'|'},
                {'|',0,0,0,0,0,'o', 0,  0,  0,  0,'|'},
                {'|',0,0,0,0,0,'o', 0,  0,  0,  0,'|'},
                {'|',0,0,0,0,0,'o','o','o','o','o','|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'|',0,0,0,0,0,0,0,0,0,0,'|'},
                {'-','-','-','-','-','-','-','-','-','-','-','-'}};

        Assertions.assertArrayEquals(expected, shape.getCurrentShape());
    }
}