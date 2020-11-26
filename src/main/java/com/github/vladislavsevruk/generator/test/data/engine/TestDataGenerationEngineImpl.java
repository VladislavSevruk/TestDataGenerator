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
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContextManager;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

/**
 * Implementation of <code>TestDataGenerationEngine</code>.
 *
 * @see TestDataGenerationEngine
 */
@Log4j2
public class TestDataGenerationEngineImpl implements TestDataGenerationEngine {

    private final TestDataGenerationContext context;

    public TestDataGenerationEngineImpl() {
        this(TestDataGenerationContextManager.getContext());
    }

    public TestDataGenerationEngineImpl(TestDataGenerationContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T generate(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<T> typeMeta) {
        NonParameterizedTypeDataGenerator<T> generator = context.getTestDataGeneratorPicker().pickGenerator(typeMeta);
        return Objects.nonNull(generator) ? generator.generate(testDataGenerationConfig) : null;
    }
}
