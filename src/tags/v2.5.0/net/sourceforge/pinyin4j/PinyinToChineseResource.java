/**
 * This file is part of pinyin4j (http://sourceforge.net/projects/pinyin4j/) 
 * and distributed under GNU GENERAL PUBLIC LICENSE (GPL).
 * 
 * pinyin4j is free software; you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 2 of the License, or 
 * (at your option) any later version. 
 * 
 * pinyin4j is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 * GNU General Public License for more details. 
 * 
 * You should have received a copy of the GNU General Public License 
 * along with pinyin4j.
 */

/**
 * 
 */
package net.sourceforge.pinyin4j;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Li Min (xmlerlimin@gmail.com)
 * 
 */
public class PinyinToChineseResource
{
    void initializePinyinToChineseTable()
    {
        // create a treemap
        setPinyinToChineseTable(new TreeMap());

        // assign values
        ChineseToPinyinResource Ch2py = ChineseToPinyinResource.getInstance();

        // get all Chinese characters
        Enumeration chineseCharEnum = Ch2py.getAllChineseFromResource();

        for (; chineseCharEnum.hasMoreElements();)
        {
            Integer chineseCodePoint = Integer.valueOf((String) chineseCharEnum.nextElement(), 16);

            String[] pinyinArray = Ch2py.getHanyuPinyinStringArray((char) chineseCodePoint.intValue());

            if (null != pinyinArray)
            {
                String chineseCharStr = String.valueOf((char) chineseCodePoint.intValue());

                for (int i = 0; i < pinyinArray.length; i++)
                {
                    if (!getPinyinToChineseTable().containsKey(pinyinArray[i]))
                    {
                        getPinyinToChineseTable().put(pinyinArray[i], new TreeSet(new ChineseCharacterFrequenceComparator()));
                    }

                    TreeSet charSet = (TreeSet) getPinyinToChineseTable().get(pinyinArray[i]);
                    charSet.add(new ChineseCharacter(chineseCharStr.charAt(0)));
                }
            }
        }
    }

    private Map pinyinToChineseTable;

    /**
     * Private constructor as part of the singleton pattern.
     */
    private PinyinToChineseResource()
    {
        initializePinyinToChineseTable();
    }

    char[] pinyinToChinese(String pinyinStr)
    {
        TreeSet chineseCharSet = (TreeSet) getPinyinToChineseTable().get((Object) pinyinStr);

        if (null != chineseCharSet)
        {
            Object[] objArray = chineseCharSet.toArray();

            char[] charArray = new char[objArray.length];

            for (int i = 0; i < objArray.length; i++)
            {
                charArray[objArray.length - 1 - i] = ((ChineseCharacter) objArray[i]).getChineseCharacter();
            }

            return charArray;

        } else
            return null;
    }

    /**
     * Singleton factory method.
     * 
     * @return the one and only MySingleton.
     */
    static PinyinToChineseResource getInstance()
    {
        return PinyinToChineseResourceHolder.theInstance;
    }

    /**
     * @param pinyinToChineseTable
     *            The pinyinToChineseTable to set.
     */
    private void setPinyinToChineseTable(Map pinyinToChineseTable)
    {
        this.pinyinToChineseTable = pinyinToChineseTable;
    }

    /**
     * @return Returns the pinyinToChineseTable.
     */
    private Map getPinyinToChineseTable()
    {
        return pinyinToChineseTable;
    }

    /**
     * Singleton implementation helper.
     */
    private static class PinyinToChineseResourceHolder
    {
        static final PinyinToChineseResource theInstance = new PinyinToChineseResource();
    }
}
