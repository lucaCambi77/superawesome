/**
 * 
 */
package it.cambi.superawesome.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import it.cambi.superawesome.domain.TreeSetAnagrams;

/**
 * @author luca
 *
 */
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class AnagramsTest
{
    private PrintStream out;

    private @Spy TreeSetAnagrams anagrams = new TreeSetAnagrams();

    private static final String fileDir = "src/test/resources/task/data";

    @BeforeEach
    public void setUpStreams()
    {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @ParameterizedTest
    @CsvSource({ fileDir + "/example1.txt" })
    @Order(1)
    public void shouldPassReadFromFile(String file) throws IOException
    {

        try (BufferedReader buf = new BufferedReader(new FileReader(file)))
        {
            String line = buf.readLine();

            while (line != null)
            {
                System.out.println(line);
                line = buf.readLine();

            }
        }

        verify(out).println("abc");
        verify(out, times(2)).println("fun");
        verify(out).println("bac");
        verify(out).println("unf");
        verify(out).println("hello");
        verify(out).println("cba");

    }

    @ParameterizedTest
    @ValueSource(strings = { fileDir + "/example1.txt" })
    @Order(2)
    public void shouldPassExample1(String file) throws IOException, InstantiationException, IllegalAccessException
    {

        anagrams.groupFromInput(file);

        verify(out).println("fun,unf");
        verify(out).println("abc,bac,cba");
        verify(out).println("hello");

        assertEquals(2, anagrams.getGroups());
        verify(out, times(3)).println(anyString());
        verify(anagrams, times(1)).flush();

    }

    @ParameterizedTest
    @ValueSource(strings = { fileDir + "/example2.txt" })
    @Order(3)
    public void shouldPassExample2(String file) throws IOException, InstantiationException, IllegalAccessException
    {

        anagrams.groupFromInput(file);

        verify(out, times(156476)).println(anyString());
        verify(anagrams, times(1)).flush();

        assertEquals(26, anagrams.getGroups());
    }

    @ParameterizedTest
    @Order(4)
    @ValueSource(strings = { fileDir + "/longestAnagram.txt" })
    public void shouldPassLongestAnagram(String file) throws IOException, InstantiationException, IllegalAccessException
    {

        anagrams.groupFromInput(file);

        verify(out, times(1)).println("hydroxydeoxycorticosterones,hydroxydesoxycorticosterone");
        verify(anagrams, times(1)).flush();

    }

    @ParameterizedTest
    @Order(5)
    @ValueSource(strings = { fileDir + "/caseSensitive.txt" })
    public void shouldPassCaseSensitiveWithEmptyLines(String file) throws IOException, InstantiationException, IllegalAccessException
    {

        anagrams.groupFromInput(file);

        verify(out, times(1)).println("Hello,hello");
        verify(anagrams, times(1)).flush();

    }

    @ParameterizedTest
    @Order(6)
    @CsvSource({ fileDir + "/empty.txt," + fileDir + "/oneLetterWord.txt" })
    public void shouldFailOnEmptyOrOneLetterWordFile(String empty, String oneLetterWord) throws IOException
    {

        assertThrows(IllegalArgumentException.class, () -> {
            anagrams.groupFromInput(empty);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            anagrams.groupFromInput(oneLetterWord);
        });

        verify(anagrams, times(0)).flush();

    }
}
