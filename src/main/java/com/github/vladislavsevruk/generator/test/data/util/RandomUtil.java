/*
 * MIT License
 *
 * Copyright (c) 2020 Uladzislau Seuruk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.vladislavsevruk.generator.test.data.util;

import java.util.Random;

/**
 * Contains utility methods for generation pseudorandom values for basic Java types.
 */
public final class RandomUtil {

    private static final int ZERO_SYMBOL_CODE = '0';
    private static final int NINE_SYMBOL_CODE = '9';
    private static final int LOWER_A_SYMBOL_CODE = 'a';
    private static final int LOWER_Z_SYMBOL_CODE = 'z';
    private static final int UPPER_A_SYMBOL_CODE = 'A';
    private static final int UPPER_Z_SYMBOL_CODE = 'Z';
    private static final int NUMERIC_SYMBOLS_UPPER_BOUND = NINE_SYMBOL_CODE + 1;
    private static final int UPPER_LETTER_SYMBOLS_GAP = UPPER_A_SYMBOL_CODE - NUMERIC_SYMBOLS_UPPER_BOUND;
    private static final int UPPER_LETTER_SYMBOLS_UPPER_BOUND = UPPER_Z_SYMBOL_CODE + 1 - UPPER_LETTER_SYMBOLS_GAP;
    private static final int LOWER_LETTER_SYMBOLS_GAP = LOWER_A_SYMBOL_CODE - UPPER_LETTER_SYMBOLS_UPPER_BOUND;
    private static final int LOWER_LETTER_SYMBOLS_UPPER_BOUND = LOWER_Z_SYMBOL_CODE + 1 - LOWER_LETTER_SYMBOLS_GAP;
    private static final Random RANDOM = new Random();

    private RandomUtil() {
    }

    /**
     * Returns pseudorandom <code>Boolean</code> value.
     */
    public static Boolean getBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * Returns pseudorandom <code>Byte</code> value.
     */
    public static Byte getByte() {
        return getByte(Byte.MAX_VALUE);
    }

    /**
     * Returns pseudorandom <code>Byte</code> value that conforms received upper bound value.
     *
     * @param upperBound <code>byte</code> with upper bound value (exclusive).
     * @return generated <code>Byte</code> value.
     */
    public static Byte getByte(byte upperBound) {
        return getByte((byte) 0, upperBound);
    }

    /**
     * Returns pseudorandom <code>Byte</code> value that conforms received lower and upper bound values.
     *
     * @param lowerBound <code>byte</code> with lower bound value (inclusive).
     * @param upperBound <code>byte</code> with upper bound value (exclusive).
     * @return generated <code>Byte</code> value.
     */
    public static Byte getByte(byte lowerBound, byte upperBound) {
        return getInteger(lowerBound, upperBound).byteValue();
    }

    /**
     * Returns pseudorandom <code>Character</code> value.
     */
    public static Character getCharacter() {
        return getCharacter(Character.MAX_VALUE);
    }

    /**
     * Returns pseudorandom <code>Character</code> value that conforms received upper bound value.
     *
     * @param upperBound <code>char</code> with upper bound value (exclusive).
     * @return generated <code>Character</code> value.
     */
    public static Character getCharacter(char upperBound) {
        return getCharacter((char) 0, upperBound);
    }

    /**
     * Returns pseudorandom <code>Character</code> value that conforms received lower and upper bound values.
     *
     * @param lowerBound <code>char</code> with lower bound value (inclusive).
     * @param upperBound <code>char</code> with upper bound value (exclusive).
     * @return generated <code>Character</code> value.
     */
    public static Character getCharacter(char lowerBound, char upperBound) {
        return (char) getInteger(lowerBound, upperBound).intValue();
    }

    /**
     * Returns pseudorandom <code>Double</code> value.
     */
    public static Double getDouble() {
        return getDouble(Double.MAX_VALUE);
    }

    /**
     * Returns pseudorandom <code>Double</code> value that conforms received upper bound value.
     *
     * @param upperBound <code>double</code> with upper bound value (exclusive).
     * @return generated <code>Double</code> value.
     */
    public static Double getDouble(double upperBound) {
        return getDouble(0, upperBound);
    }

    /**
     * Returns pseudorandom <code>Double</code> value that conforms received lower and upper bound values.
     *
     * @param lowerBound <code>double</code> with lower bound value (inclusive).
     * @param upperBound <code>double</code> with upper bound value (exclusive).
     * @return generated <code>Double</code> value.
     */
    public static Double getDouble(double lowerBound, double upperBound) {
        return RANDOM.doubles(1, lowerBound, upperBound).findFirst().getAsDouble();
    }

    /**
     * Returns pseudorandom <code>Float</code> value.
     */
    public static Float getFloat() {
        return getFloat(Float.MAX_VALUE);
    }

    /**
     * Returns pseudorandom <code>Float</code> value that conforms received upper bound value.
     *
     * @param upperBound <code>float</code> with upper bound value (exclusive).
     * @return generated <code>Float</code> value.
     */
    public static Float getFloat(float upperBound) {
        return getFloat(0, upperBound);
    }

    /**
     * Returns pseudorandom <code>Float</code> value that conforms received lower and upper bound values.
     *
     * @param lowerBound <code>float</code> with lower bound value (inclusive).
     * @param upperBound <code>float</code> with upper bound value (exclusive).
     * @return generated <code>Float</code> value.
     */
    public static Float getFloat(float lowerBound, float upperBound) {
        return getDouble(lowerBound, upperBound).floatValue();
    }

    /**
     * Gets random index from array.
     *
     * @param items items array.
     * @param <T>   type of items.
     * @return random index from array.
     */
    public static <T> int getIndex(T[] items) {
        if (items.length != 0) {
            return getInteger(items.length);
        }
        throw new IllegalArgumentException("Item array is empty");
    }

    /**
     * Returns pseudorandom <code>Integer</code> value.
     */
    public static Integer getInteger() {
        return getInteger(Integer.MAX_VALUE);
    }

    /**
     * Returns pseudorandom <code>Integer</code> value that conforms received upper bound value.
     *
     * @param upperBound <code>int</code> with upper bound value (exclusive).
     * @return generated <code>Integer</code> value.
     */
    public static Integer getInteger(int upperBound) {
        return getInteger(0, upperBound);
    }

    /**
     * Returns pseudorandom <code>Integer</code> value that conforms received lower and upper bound values.
     *
     * @param lowerBound <code>int</code> with lower bound value (inclusive).
     * @param upperBound <code>int</code> with upper bound value (exclusive).
     * @return generated <code>Integer</code> value.
     */
    public static Integer getInteger(int lowerBound, int upperBound) {
        return RANDOM.ints(1, lowerBound, upperBound).findFirst().getAsInt();
    }

    /**
     * Get random item from array.
     *
     * @param items items array.
     * @param <T>   type of items.
     * @return random item from array.
     */
    public static <T> T getItem(T[] items) {
        return items[getIndex(items)];
    }

    /**
     * Returns pseudorandom <code>Long</code> value.
     */
    public static Long getLong() {
        return getLong(Long.MAX_VALUE);
    }

    /**
     * Returns pseudorandom <code>Long</code> value that conforms received upper bound value.
     *
     * @param upperBound <code>long</code> with upper bound value (exclusive).
     * @return generated <code>Long</code> value.
     */
    public static Long getLong(long upperBound) {
        return getLong(0, upperBound);
    }

    /**
     * Returns pseudorandom <code>Long</code> value that conforms received lower and upper bound values.
     *
     * @param lowerBound <code>long</code> with lower bound value (inclusive).
     * @param upperBound <code>long</code> with upper bound value (exclusive).
     * @return generated <code>Long</code> value.
     */
    public static Long getLong(long lowerBound, long upperBound) {
        return RANDOM.longs(1, lowerBound, upperBound).findFirst().getAsLong();
    }

    /**
     * Returns pseudorandom <code>Short</code> value.
     */
    public static Short getShort() {
        return getShort(Short.MAX_VALUE);
    }

    /**
     * Returns pseudorandom <code>Short</code> value that conforms received upper bound value.
     *
     * @param upperBound <code>short</code> with upper bound value (exclusive).
     * @return generated <code>Short</code> value.
     */
    public static Short getShort(short upperBound) {
        return getShort((short) 0, upperBound);
    }

    /**
     * Returns pseudorandom <code>Short</code> value that conforms received lower and upper bound values.
     *
     * @param lowerBound <code>short</code> with lower bound value (inclusive).
     * @param upperBound <code>short</code> with upper bound value (exclusive).
     * @return generated <code>Short</code> value.
     */
    public static Short getShort(short lowerBound, short upperBound) {
        return getInteger(lowerBound, upperBound).shortValue();
    }

    /**
     * Returns pseudorandom <code>String</code> value with length 16.
     */
    public static String getString() {
        return getString(16);
    }

    /**
     * Returns pseudorandom <code>String</code> value with received length.
     *
     * @param numberOfSymbols <code>int</code> with string length.
     * @return generated <code>String</code> value.
     */
    public static String getString(int numberOfSymbols) {
        StringBuilder stringBuilder = new StringBuilder();
        RANDOM.ints(ZERO_SYMBOL_CODE, LOWER_LETTER_SYMBOLS_UPPER_BOUND).limit(numberOfSymbols)
                .map(RandomUtil::makeShiftsForLetters).forEach(stringBuilder::appendCodePoint);
        return stringBuilder.toString();
    }

    private static int makeShiftsForLetters(int value) {
        if (value >= UPPER_LETTER_SYMBOLS_UPPER_BOUND) {
            return value + LOWER_LETTER_SYMBOLS_GAP;
        }
        return value >= NUMERIC_SYMBOLS_UPPER_BOUND ? value + UPPER_LETTER_SYMBOLS_GAP : value;
    }
}
