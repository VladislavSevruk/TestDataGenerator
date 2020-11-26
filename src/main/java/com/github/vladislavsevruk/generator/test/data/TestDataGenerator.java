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
package com.github.vladislavsevruk.generator.test.data;

import com.github.vladislavsevruk.generator.test.data.config.TestDataGenerationConfig;
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContextManager;
import com.github.vladislavsevruk.generator.test.data.exception.InstanceCreationException;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import com.github.vladislavsevruk.resolver.type.TypeProvider;
import com.github.vladislavsevruk.resolver.util.PrimitiveWrapperUtil;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

/**
 * This tool is designed for testing data generation for POJOs using public constructors without arguments and public
 * setters.
 */
@Log4j2
public class TestDataGenerator {

    private final TestDataGenerationConfig testDataGenerationConfig;
    private final TestDataGenerationContext testDataGenerationContext;

    /**
     * Creates new instance with default context.
     */
    public TestDataGenerator() {
        this(TestDataGenerationContextManager.getContext());
    }

    /**
     * Creates new instance with provided context.
     *
     * @param testDataGenerationContext <code>TestDataGenerationContext</code> that will be used for test data
     *                                  generation operations.
     */
    public TestDataGenerator(TestDataGenerationContext testDataGenerationContext) {
        this(testDataGenerationContext, TestDataGenerationConfig.builder().build());
    }

    /**
     * Creates new instance with provided configuration.
     *
     * @param testDataGenerationConfig <code>TestDataGenerationConfig</code> with generation parameters that will be
     *                                 used for test data generation operations.
     */
    public TestDataGenerator(TestDataGenerationConfig testDataGenerationConfig) {
        this(TestDataGenerationContextManager.getContext(), testDataGenerationConfig);
    }

    /**
     * Creates new instance with provided context and configuration.
     *
     * @param testDataGenerationContext <code>TestDataGenerationContext</code> that will be used for test data
     *                                  generation operations.
     * @param testDataGenerationConfig  <code>TestDataGenerationConfig</code> with generation parameters that will be
     *                                  used for test data generation operations.
     */
    public TestDataGenerator(TestDataGenerationContext testDataGenerationContext,
            TestDataGenerationConfig testDataGenerationConfig) {
        this.testDataGenerationContext = testDataGenerationContext;
        this.testDataGenerationConfig = testDataGenerationConfig;
    }

    /**
     * Generates testing data into model of received type. Creates instance of received type using public constructor
     * without arguments and fills it using public setters from target model.
     *
     * @param type target model class.
     * @param <T>  the type represented by target class.
     * @return model instance with generated testing data.
     * @throws InstanceCreationException if target class is abstract or has no public constructor without arguments.
     */
    public <T> T generate(Class<T> type) {
        Objects.requireNonNull(type, "Target type should not be null.");
        Class<T> typeWrapper = PrimitiveWrapperUtil.wrap(type);
        TypeMeta<T> typeMeta = new TypeMeta<>(typeWrapper);
        return testDataGenerationContext.getTestDataGenerationEngine().generate(testDataGenerationConfig, typeMeta);
    }

    /**
     * Generates testing data into model of received type. Uses descendants of <code>TypeProvider</code> for receiving
     * meta information of generic types. Creates instance of received type using public constructor without arguments
     * and fills it using public setters from target model.
     *
     * @param typeProvider <code>TypeProvider</code> that provides generic type of target model.
     * @param <T>          target type represented by <code>TypeProvider</code>.
     * @return model instance with generated testing data.
     * @throws InstanceCreationException if target class is abstract or has no public constructor without arguments.
     * @see TypeProvider
     */
    @SuppressWarnings("unchecked")
    public <T> T generate(TypeProvider<T> typeProvider) {
        Objects.requireNonNull(typeProvider, "Target type provider should not be null.");
        TypeMeta<?> typeMeta = typeProvider.getTypeMeta();
        return testDataGenerationContext.getTestDataGenerationEngine()
                .generate(testDataGenerationConfig, (TypeMeta<T>) typeMeta);
    }
}
