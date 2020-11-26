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
package com.github.vladislavsevruk.generator.test.data.generator;

import com.github.vladislavsevruk.generator.test.data.config.TestDataGenerationConfig;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

/**
 * Contains common logic for test data generators.
 *
 * @param <T> target type for test data generation.
 */
@Log4j2
public abstract class AbstractNonParameterizedTestDataGenerator<T> implements NonParameterizedTypeDataGenerator<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canGenerate(Class<?> type) {
        return type.isAssignableFrom(getTargetType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T generate(TestDataGenerationConfig testDataGenerationConfig) {
        Objects.requireNonNull(testDataGenerationConfig, "Received test data generation config shouldn't be null.");
        log.debug("Trying to generate test data for '{}'.", getTargetType().getName());
        T generatedValue = doGenerate(testDataGenerationConfig);
        log.debug("Successfully generated test data for '{}'.", getTargetType().getName());
        return generatedValue;
    }

    protected abstract T doGenerate(TestDataGenerationConfig testDataGenerationConfig);

    protected abstract Class<T> getTargetType();
}
