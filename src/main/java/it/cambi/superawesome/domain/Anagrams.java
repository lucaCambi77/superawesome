/**
 *
 */
package it.cambi.superawesome.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author luca
 *
 */
public class Anagrams {

    /**
     * Letter to primitive map
     */
    private static final Map<Character, Integer> alphabetMap = new HashMap<Character, Integer>() {
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
    private final HashMap<BigInteger, LinkedHashSet<String>> resultMap = new HashMap<>();

    /**
     * @param file path
     * @return
     * @throws IOException
     */
    public void printFromInput(String file) throws IOException {

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            String line = in.readLine();

            while (line != null) {

                AtomicReference<BigInteger> r = intReference(line);

                LinkedHashSet<String> set = resultMap.getOrDefault(r.get(), new LinkedHashSet<>());
                set.add(line);
                resultMap.put(r.get(), set);

                line = in.readLine();
            }
        }

        flush(resultMap.values());
    }

    /**
     * @param line of the input file
     * @return
     */
    protected AtomicReference<BigInteger> intReference(String line) {
        AtomicReference<BigInteger> valueHolder = new AtomicReference<>();
        valueHolder.set(new BigInteger("1"));

        line.chars()
            .forEach(c -> valueHolder.getAndAccumulate(BigInteger.valueOf(alphabetMap.get(Character.toLowerCase((char) c))), BigInteger::multiply));

        return valueHolder;
    }

    public void flush(Collection<LinkedHashSet<String>> l) {
        l.forEach(value -> System.out.println(String.join(",", value)));
    }
}
