/**
 * 
 */
package it.cambi.superawesome.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author luca
 *
 */
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class AnagramsTest
{
    private PrintStream out;

    @BeforeEach
    public void setUpStreams()
    {
        out = mock(PrintStream.class);
        System.setOut(out);
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

        verify(out).println("abc");
        verify(out, times(2)).println("fun");
        verify(out).println("bac");
        verify(out).println("unf");
        verify(out).println("hello");
        verify(out).println("cba");

    }

    private HashMap<BigInteger, TreeSet<String>> resultMap = new HashMap<>();

    @SuppressWarnings("serial")
    private Map<Character, Integer> alphabetMap = new HashMap<Character, Integer>()
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
    @ValueSource(strings = { "src/main/resources/task/data/example1.txt" })
    @Order(2)
    public void testAnagrams2(String file) throws IOException
    {

        InputStream is = new FileInputStream(file);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();

        checkLine(line);

        int currentLength = line.length();

        while (line != null)
        {
            if (line.toCharArray().length > currentLength)
            {
                flush(resultMap);
                currentLength = line.toCharArray().length;
                resultMap = new HashMap<>();
            }

            checkLine(line);

            line = buf.readLine();

        }

        flush(resultMap);

        buf.close();

        verify(out).println("fun,unf");
        verify(out).println("abc,bac,cba");
        verify(out).println("hello");
    }

    /**
     * @param line
     * @return
     */
    private void checkLine(String line)
    {
        AtomicReference<BigInteger> valueHolder = new AtomicReference<>();
        valueHolder.set(new BigInteger("1"));

        line.chars().forEach(c -> {
            valueHolder.getAndAccumulate(BigInteger.valueOf(alphabetMap.get((char) c)), (previous, x) -> previous.multiply(x));
        });

        TreeSet<String> set = resultMap.getOrDefault(valueHolder.get(), new TreeSet<String>());
        set.add(line);
        resultMap.put(valueHolder.get(), set);
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
