/**
 *
 */
package it.cambi.superawesome.main;

import java.io.File;
import java.io.IOException;

import it.cambi.superawesome.domain.Anagrams;
import it.cambi.superawesome.domain.Errors;

/**
 * @author luca
 *
 */
public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println(Errors.ERR_NO_FILE_INPUT);
            return;
        }

        File file = new File(args[0]);

        if (!file.exists()) {
            System.out.println(Errors.ERR_FILE_NOT_EXISTS);
            return;
        }

        anagrams(file.getPath());

    }

    /**
     * @param file path
     * @throws IOException
     */
    private static void anagrams(String file) throws IOException {

        Anagrams anagrams = new Anagrams();
        anagrams.printFromInput(file);
    }
}
