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
package com.github.vladislavsevruk.generator.test.data.storage;

import com.github.vladislavsevruk.generator.test.data.generator.DataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.ParameterizedTypeDataGenerator;

import java.util.List;

/**
 * Contains generators for test data generation for specific types.
 *
 * @see NonParameterizedTypeDataGenerator
 */
public interface TestDataGeneratorStorage {

    /**
     * Adds new <code>NonParameterizedTypeTestDataGenerator</code> to list. Generator will not be added provided it is
     * <code>null</code> or list already contains generator of such type.
     *
     * @param customGenerator custom generator to add.
     */
    void add(NonParameterizedTypeDataGenerator<?> customGenerator);

    /**
     * Adds new <code>ParameterizedTypeDataGenerator</code> to list. Generator will not be added provided it is
     * <code>null</code> or list already contains generator of such type.
     *
     * @param customGenerator custom generator to add.
     */
    void add(ParameterizedTypeDataGenerator<?> customGenerator);

    /**
     * Adds new <code>NonParameterizedTypeDataGenerator</code> to list after specified <code>DataGenerator</code> or at
     * list end provided target <code>DataGenerator</code> is missed. New generator will not be added provided it is
     * <code>null</code> or list already contains generator of such type.
     *
     * @param customGenerator custom generator to add.
     * @param targetType      type after which new generator should be added.
     */
    void addAfter(NonParameterizedTypeDataGenerator<?> customGenerator, Class<? extends DataGenerator> targetType);

    /**
     * Adds new <code>ParameterizedTypeDataGenerator</code> to list after specified <code>DataGenerator</code> or at
     * list end provided target <code>DataGenerator</code> is missed. New generator will not be added provided it is
     * <code>null</code> or list already contains generator of such type.
     *
     * @param customGenerator custom generator to add.
     * @param targetType      type after which new generator should be added.
     */
    void addAfter(ParameterizedTypeDataGenerator<?> customGenerator, Class<? extends DataGenerator> targetType);

    /**
     * Adds new <code>NonParameterizedTypeDataGenerator</code> to list before specified <code>DataGenerator</code> or at
     * list end provided target <code>DataGenerator</code> is missed. New generator will not be added provided it is
     * <code>null</code> or list already contains generator of such type.
     *
     * @param customGenerator custom generator to add.
     * @param targetType      type after which new generator should be added.
     */
    void addBefore(NonParameterizedTypeDataGenerator<?> customGenerator, Class<? extends DataGenerator> targetType);

    /**
     * Adds new <code>ParameterizedTypeDataGenerator</code> to list before specified <code>DataGenerator</code> or at
     * list end provided target <code>DataGenerator</code> is missed. New generator will not be added provided it is
     * <code>null</code> or list already contains generator of such type.
     *
     * @param customGenerator custom generator to add.
     * @param targetType      type after which new generator should be added.
     */
    void addBefore(ParameterizedTypeDataGenerator<?> customGenerator, Class<? extends DataGenerator> targetType);

    /**
     * Returns list of all <code>DataGenerator</code>-s that are present at storage.
     */
    List<DataGenerator> getAll();
}
