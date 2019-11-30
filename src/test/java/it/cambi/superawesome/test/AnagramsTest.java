/**
 * 
 */
package it.cambi.superawesome.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author luca
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class AnagramsTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams()
    {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @ParameterizedTest
    @CsvSource({ "src/main/resources/task/data/example1.txt" })
    @Order(1)
    public void testAnagrams(String file) throws IOException
    {

        InputStream is = new FileInputStream(file);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();

        while (line != null)
        {
            System.out.println(line);
            line = buf.readLine();

        }

        buf.close();

        assertEquals("abc\n" +
                "fun\n" +
                "bac\n" +
                "fun\n" +
                "cba\n" +
                "unf\n" +
                "hello\n", outContent.toString());
    }
}
