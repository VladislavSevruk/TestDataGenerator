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
package com.github.vladislavsevruk.generator.test.data.engine;

import com.github.vladislavsevruk.generator.test.data.config.TestDataGenerationConfig;
import com.github.vladislavsevruk.resolver.type.TypeMeta;

/**
 * Generates testing values for POJO model according to test data generation configuration parameters.
 */
public interface TestDataGenerationEngine {

    /**
     * Generates testing values for received model according to received configuration parameters.
     *
     * @param testDataGenerationConfig <code>TestDataGenerationConfig</code> with configuration values for test data
     *                                 generation.
     * @param typeMeta                 <code>TypeMeta</code> with metadata of model to generate testing values for.
     * @param <T>                      type of model to generate test values for.
     * @return generated model with test values set.
     */
    <T> T generate(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<T> typeMeta);
}
