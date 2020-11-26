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
package com.github.vladislavsevruk.generator.test.data.mapping;

import com.github.vladislavsevruk.generator.test.data.config.TestDataGenerationConfig;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * Stores custom test data generators that should be used for test data generation for specific fields.
 */
public interface CustomFieldMappingStorage {

    /**
     * Adds custom test data generation function that will be used for test data generation for received field.
     *
     * @param field         <code>Field</code> to add custom test data generation function for.
     * @param valueFunction <code>Function</code> that will be used for test data generation for received field.
     */
    void addMapping(Field field, Function<TestDataGenerationConfig, ?> valueFunction);

    /**
     * Returns custom test data generator that is associated with received field.
     *
     * @param field <code>Field</code> to get custom test data generator for.
     * @return custom <code>NonParameterizedTypeDataGenerator</code> that is associated with received field or
     * <code>null</code> if received field has no associated custom test data generators.
     */
    @SuppressWarnings("java:S1452")
    NonParameterizedTypeDataGenerator<?> getMapping(Field field);

    /**
     * Checks if storage contains custom mapping for received field.
     *
     * @param field <code>Field</code> to check custom mappings for.
     * @return <code>true</code> if storage contains custom mapping for received field, <code>false</code> otherwise.
     */
    boolean hasMapping(Field field);
}
