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

/**
 * @author Li Min (xmlerlimin@gmail.com)
 * 
 */
class ChineseCharacter
{
    ChineseCharacter(char chineseChar)
    {
        setChineseCharacter(chineseChar);
    }

    double getCharacterFrequence()
    {
        return getCharacterFrequence(getChineseCharacter());
    }

    static double getCharacterFrequence(char ch)
    {
        double gbFrequenceOfChar = ChineseCharacterFrequence.getGbFrequence(ch);
        double big5FrequenceOfChar = ChineseCharacterFrequence.getBig5Frequence(ch);

        // if this character exists in both table, return bigger one
        return (gbFrequenceOfChar > big5FrequenceOfChar) ? gbFrequenceOfChar
                : big5FrequenceOfChar;
    }

    private char chineseCharacter;

    /**
     * @return Returns the chineseCharacter.
     */
    char getChineseCharacter()
    {
        return chineseCharacter;
    }

    /**
     * @param chineseCharacter
     *            The chineseCharacter to set.
     */
    private void setChineseCharacter(char chineseCharacter)
    {
        this.chineseCharacter = chineseCharacter;
    }
}
