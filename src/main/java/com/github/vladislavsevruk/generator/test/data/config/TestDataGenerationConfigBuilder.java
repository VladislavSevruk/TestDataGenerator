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
package com.github.vladislavsevruk.generator.test.data.config;

import com.github.vladislavsevruk.generator.test.data.exception.GenerationConfigurationException;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * Builds <code>TestDataGenerationConfig</code> instances.
 *
 * @see TestDataGenerationConfig
 */
@Accessors(chain = true, fluent = true)
@Setter
public final class TestDataGenerationConfigBuilder {

    private int maxItemsForCollections = 5;
    private int minItemsForCollections = 1;
    private String testDataPostfix = "";
    private String testDataPrefix = "";

    TestDataGenerationConfigBuilder() {
    }

    /**
     * Verifies set parameters and builds <code>TestDataGenerationConfig</code> instance with these values.
     */
    public TestDataGenerationConfig build() {
        validateConfigurationParameters();
        return new TestDataGenerationConfig(maxItemsForCollections, minItemsForCollections, testDataPostfix,
                testDataPrefix);
    }

    private void validateConfigurationParameters() {
        if (minItemsForCollections < 0) {
            throw new GenerationConfigurationException("Min items number for collection shouldn't be less than zero.");
        }
        if (minItemsForCollections > maxItemsForCollections) {
            throw new GenerationConfigurationException(
                    "Min items number for collection shouldn't be greater than max items number.");
        }
        if (Objects.isNull(testDataPostfix)) {
            throw new GenerationConfigurationException("Test data postfix shouldn't be null.");
        }
        if (Objects.isNull(testDataPrefix)) {
            throw new GenerationConfigurationException("Test data prefix shouldn't be null.");
        }
    }
}
