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
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

        assertEquals("abc\n" + "fun\n" + "bac\n" + "fun\n" + "cba\n" + "unf\n" + "hello\n", outContent.toString());

    }

    private HashMap<BigInteger, TreeSet<String>> resultMap = new HashMap<>();

    @SuppressWarnings("serial")
    Map<Character, Integer> alphabetMap = new HashMap<Character, Integer>()
    {
        {

            put('a', 2);
            put('b', 3);
            put('c', 5);
            put('d', 7);
            put('e', 11);
            put('f', 13);
            put('g', 17);
            put('h', 19);
            put('i', 23);
            put('j', 29);
            put('k', 31);
            put('l', 37);
            put('m', 41);
            put('n', 43);
            put('o', 47);
            put('p', 53);
            put('q', 59);
            put('r', 61);
            put('s', 67);
            put('t', 71);
            put('u', 73);
            put('v', 79);
            put('w', 83);
            put('x', 89);
            put('y', 97);
            put('z', 101);

        }
    };

    @ParameterizedTest
    @ValueSource(strings = { "src/main/resources/task/data/example1.txt", "src/main/resources/task/data/example2.txt" })
    @Order(2)
    public void testAnagrams2(String file) throws IOException
    {

        InputStream is = new FileInputStream(file);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();

        checkLine(line, alphabetMap);

        int currentLength = line.length();

        while (line != null)
        {
            if (line.toCharArray().length > currentLength)
            {
                flush(resultMap);
                currentLength = line.toCharArray().length;
                resultMap = new HashMap<>();
            }

            checkLine(line, alphabetMap);

            line = buf.readLine();

        }

        flush(resultMap);

        buf.close();

    }

    /**
     * @param line
     * @param map
     * @return
     */
    private void checkLine(String line, Map<Character, Integer> map)
    {
        BigInteger value = new BigInteger("1");

        char[] array = line.toCharArray();

        for (int i = 0; i < array.length; i++)
            value = value.multiply(BigInteger.valueOf(map.get(line.charAt(i))));

        TreeSet<String> set = resultMap.getOrDefault(value, new TreeSet<String>());
        set.add(line);
        resultMap.put(value, set);
    }

    /**
     * @param map1
     * 
     */
    private void flush(HashMap<BigInteger, TreeSet<String>> map1)
    {
        map1.entrySet().forEach(m -> {
            System.out.println(m.getValue().stream().collect(Collectors.joining(",")));
        });
    }
}
