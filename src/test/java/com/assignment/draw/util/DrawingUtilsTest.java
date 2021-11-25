package com.assignment.draw.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawingUtilsTest {

    @Test
    void should_identify_as_integer_when_integer_string_is_provided() {
        assertTrue(DrawingUtils.isInteger("1"));
    }

    @Test
    void should_not_identify_as_integer_when_double_string_is_provided() {
        assertFalse(DrawingUtils.isInteger("1.1"));
    }

    @Test
    void should_not_identify_as_integer_when_long_string_is_provided() {
        assertFalse(DrawingUtils.isInteger("1l"));
    }

    @Test
    void should_not_identify_as_integer_when_char_string_is_provided() {
        assertFalse(DrawingUtils.isInteger("a"));
    }

    @Test
    void should_not_identify_as_integer_when_null_is_provided() {
        assertFalse(DrawingUtils.isInteger(null));
    }

    @Test
    void should_get_help_text() {
        assertNotNull(DrawingUtils.getHelpText());
    }
}