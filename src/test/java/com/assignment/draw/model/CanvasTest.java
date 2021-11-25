package com.assignment.draw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanvasTest {

    @Test
    void should_clone_array() {

        char[][] currentShape = new char[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                currentShape[i][j] = 'x';
            }
        }

        char[][] newShape = new char[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newShape[i][j] = currentShape[i][j];
            }
        }

        assertArrayEquals(newShape, currentShape);

        newShape[1][1]= 'Y';
        assertNotEquals(newShape[1][1], currentShape[1][1]);

        assertEquals(newShape[1][2], currentShape[1][2]);
    }

}