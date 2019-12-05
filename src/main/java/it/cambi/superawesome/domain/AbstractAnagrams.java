/**
 * 
 */
package it.cambi.superawesome.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author luca
 *
 */
public abstract class AbstractAnagrams<T extends AbstractSet<String>>
{

    /**
     * Letter to primitive map
     */
    @SuppressWarnings("serial")
    private static Map<Character, Integer> alphabetMap = new HashMap<Character, Integer>()
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

    /**
     * Final result map -> string as integer representation to collection of grouped words
     */
    private HashMap<BigInteger, T> resultMap = new HashMap<>();

    /**
     * To keep track of the groups
     */
    private int groups;

    /**
     * 
     * @param file
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void groupFromInput(String file) throws IOException, InstantiationException, IllegalAccessException
    {

        try (BufferedReader in = new BufferedReader(new FileReader(file)))
        {

            String line = in.readLine();

            if (line == null)
                throw new IllegalArgumentException(Errors.ERR_INPUT_NOT_EMPTY);

            int currentLength = line.length();

            if (currentLength < 2)
                throw new IllegalArgumentException(Errors.ERR_INPUT_WORD_ATLEAST_TWO_LETTER);

            groups = 1;

            check(line);

            line = in.readLine();

            while (line != null)
            {

                int lineLength = line.length();
                if (lineLength > 0)
                {

                    check(line);

                    if (lineLength > currentLength)
                    {

                        groups++;
                        currentLength = lineLength;
                    }
                }

                line = in.readLine();
            }

            if (!resultMap.isEmpty())
                flush();

        }

    }

    /**
     * @param line
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    protected void check(String line) throws InstantiationException, IllegalAccessException
    {
        AtomicReference<BigInteger> valueHolder = new AtomicReference<>();
        valueHolder.set(new BigInteger("1"));

        line.chars().forEach(c -> {
            valueHolder.getAndAccumulate(BigInteger.valueOf(alphabetMap.get(Character.toLowerCase((char) c))), (previous, x) -> previous.multiply(x));
        });

        T set = resultMap.getOrDefault(valueHolder.get(), getNewInstance());
        set.add(line);
        resultMap.put(valueHolder.get(), set);
    }

    abstract T getNewInstance();

    /**
     * @param map1
     * 
     */
    public void flush()
    {
        resultMap.entrySet().forEach(m -> {
            System.out.println(m.getValue().stream().collect(Collectors.joining(",")));
        });

    }

    public int getGroups()
    {
        return groups;
    }
}
