package uk.co.test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import uk.co.wowcher.ClockConverter;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClockConverterTest {

    private ClockConverter converter;

    @Before
    public void setUp() {
        converter = new ClockConverter();
        converter.initialize();
    }

    @Test
    public void testA() {
        String time = "12:12";
        String result = converter.convertToWords(time);
        assertEquals("It's midday twelve", result);
    }

    @Test
    public void testB() {
        String time = "00:24";
        String result = converter.convertToWords(time);
        assertEquals("It's midnight twenty four", result);
    }

    @Test
    public void testC() {
        String time = "15:59";
        String result = converter.convertToWords(time);
        assertEquals("It's fifteen fifty nine", result);
    }

    @Test
    public void testD() {
        String time = "888:99";
        String result = converter.convertToWords(time);
        assertEquals("Time is not valid", result);
    }

    @Test
    public void testE() {
        String result = converter.convertToWords("");
        assertEquals("Time is not valid", result);
    }

    @Test
    public void testF() {
        String result = converter.convertToWords(null);
        assertEquals("Time is not valid", result);
    }
}
