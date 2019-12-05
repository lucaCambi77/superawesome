/**
 * 
 */
package it.cambi.superawesome.domain;

import java.util.HashSet;

/**
 * @author luca
 *
 */
public class HashSetAnagrams extends AbstractAnagrams<HashSet<String>>
{

    @Override
    HashSet<String> getNewInstance()
    {
        return new HashSet<String>();
    }
}
