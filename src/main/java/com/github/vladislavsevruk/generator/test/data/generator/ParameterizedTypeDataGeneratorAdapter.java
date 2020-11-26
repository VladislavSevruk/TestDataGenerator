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
import com.github.vladislavsevruk.resolver.type.TypeMeta;

/**
 * Adapts <code>ParameterizedTypeDataGenerator</code> to <code>NonParameterizedTypeDataGenerator</code>.
 *
 * @param <T> target type for test data generation.
 * @see NonParameterizedTypeDataGenerator
 * @see ParameterizedTypeDataGenerator
 */
public class ParameterizedTypeDataGeneratorAdapter<T> implements NonParameterizedTypeDataGenerator<T> {

    private final ParameterizedTypeDataGenerator<T> parameterizedGenerator;
    private final TypeMeta<? extends T> typeMeta;

    public ParameterizedTypeDataGeneratorAdapter(ParameterizedTypeDataGenerator<T> parameterizedGenerator,
            TypeMeta<? extends T> typeMeta) {
        this.parameterizedGenerator = parameterizedGenerator;
        this.typeMeta = typeMeta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canGenerate(Class<?> type) {
        return parameterizedGenerator.canGenerate(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T generate(TestDataGenerationConfig testDataGenerationConfig) {
        return parameterizedGenerator.generate(testDataGenerationConfig, typeMeta);
    }
}
