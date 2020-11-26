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
package com.github.vladislavsevruk.generator.test.data.generator.iterable;

import com.github.vladislavsevruk.generator.test.data.config.TestDataGenerationConfig;
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.exception.TypeGenerationException;
import com.github.vladislavsevruk.generator.test.data.generator.AbstractParameterizedTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.util.RandomUtil;
import com.github.vladislavsevruk.resolver.type.TypeMeta;

import java.lang.reflect.Array;

/**
 * Implements <code>TypeTestDataGenerator</code> for array type.
 *
 * @see NonParameterizedTypeDataGenerator
 */
public class ArrayTestDataGenerator extends AbstractParameterizedTestDataGenerator<Object[]> {

    private final TestDataGenerationContext context;

    public ArrayTestDataGenerator(TestDataGenerationContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canGenerate(Class<?> type) {
        return type.isArray();
    }

    @Override
    protected Object[] doGenerate(TestDataGenerationConfig testDataGenerationConfig,
            TypeMeta<? extends Object[]> typeMeta) {
        int itemsNumber = RandomUtil.getInteger(testDataGenerationConfig.minItemsForCollections(),
                testDataGenerationConfig.maxItemsForCollections() + 1);
        TypeMeta<?> innerType = typeMeta.getGenericTypes()[0];
        Object[] array = createItem(innerType.getType(), itemsNumber);
        for (int i = 0; i < itemsNumber; ++i) {
            array[i] = context.getTestDataGenerationEngine().generate(testDataGenerationConfig, innerType);
        }
        return array;
    }

    @Override
    protected Class<Object[]> getTargetType() {
        return Object[].class;
    }

    @Override
    protected void validateTypeMeta(TypeMeta<? extends Object[]> toMeta) {
        super.validateTypeMeta(toMeta);
        int innerTypesNumber = toMeta.getGenericTypes().length;
        if (innerTypesNumber > 1) {
            String message = String
                    .format("Expected type meta size for target type: 0 or 1 but was: %d.", innerTypesNumber);
            throw new TypeGenerationException(message);
        }
    }

    private Object[] createItem(Class<?> type, int itemsNumber) {
        return (Object[]) Array.newInstance(type, itemsNumber);
    }
}
