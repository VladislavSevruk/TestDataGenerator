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
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Implementation of <code>CustomFieldMappingStorage</code>.
 *
 * @see CustomFieldMappingStorage
 */
@Log4j2
public class CustomFieldMappingStorageImpl implements CustomFieldMappingStorage {

    private final Map<Field, NonParameterizedTypeDataGenerator<?>> mappings = new ConcurrentHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMapping(Field field, Function<TestDataGenerationConfig, ?> valueFunction) {
        mappings.put(field, convertToGenerator(valueFunction));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NonParameterizedTypeDataGenerator<?> getMapping(Field field) {
        return mappings.get(field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasMapping(Field field) {
        return mappings.containsKey(field);
    }

    private <T> NonParameterizedTypeDataGenerator<T> convertToGenerator(
            Function<TestDataGenerationConfig, T> valueFunction) {
        return new NonParameterizedTypeDataGenerator<T>() {

            @Override
            public boolean canGenerate(Class<?> type) {
                // not required for custom generators
                return false;
            }

            @Override
            public T generate(TestDataGenerationConfig testDataGenerationConfig) {
                return valueFunction.apply(testDataGenerationConfig);
            }
        };
    }
}
