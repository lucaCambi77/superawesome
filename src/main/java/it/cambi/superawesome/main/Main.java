/**
 * 
 */
package it.cambi.superawesome.main;

import java.io.File;
import java.io.IOException;

import it.cambi.superawesome.domain.Errors;
import it.cambi.superawesome.domain.HashSetAnagrams;

/**
 * @author luca
 *
 */
public class Main
{

    /**
     * @param args
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException
    {
        if (args.length == 0)
        {
            System.out.println(Errors.ERR_NO_FILE_INPUT);
            return;
        }

        File file = new File(args[0]);

        if (!file.exists())
        {
            System.out.println(Errors.ERR_FILE_NOT_EXISTS);
            return;
        }

        anagrams(file.getPath());

    }

    /**
     * @param path
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static void anagrams(String file)
            throws IOException, InstantiationException, IllegalAccessException
    {

        HashSetAnagrams anagrams = new HashSetAnagrams();
        anagrams.groupFromInput(file);
    }

}
