package com.assignment.draw.command;

import com.assignment.draw.model.Canvas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransposeTest {

    @Test
    void should_transpose_a_canvas() {
        char[][] input = {
                {'-', '-', '-', '-', '-'},
                {'|',  0,  'o',  0,  '|'},
                {'|',  0,  'o',  0, '|'},
                {'-', '-', '-', '-', '-'}};

        char[][] expected = {
                {'-', '-', '-', '-'},
                {'|',  0,   0,  '|'},
                {'|', 'o', 'o', '|'},
                {'|',  0,   0,  '|'},
                {'-', '-', '-', '-'}};

        var transpose = new Transpose();
        transpose.setCanvas(new Canvas(input));

        transpose.drawShape(new String[]{});

        Assertions.assertNotNull(transpose.getCanvas());
        Assertions.assertNotNull(transpose.getCanvas().getCurrentShape());
        Assertions.assertArrayEquals(expected, transpose.getCanvas().getCurrentShape());
    }

}