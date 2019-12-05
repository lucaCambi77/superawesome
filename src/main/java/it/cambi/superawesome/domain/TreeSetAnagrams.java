/**
 * 
 */
package it.cambi.superawesome.domain;

import java.util.TreeSet;

/**
 * @author luca
 *
 */
public class TreeSetAnagrams extends AbstractAnagrams<TreeSet<String>>
{

    @Override
    TreeSet<String> getNewInstance()
    {
        return new TreeSet<String>();
    }
}
