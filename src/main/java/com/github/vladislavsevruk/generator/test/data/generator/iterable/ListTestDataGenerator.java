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
import com.github.vladislavsevruk.generator.test.data.util.InstanceCreationUtil;
import com.github.vladislavsevruk.generator.test.data.util.RandomUtil;
import com.github.vladislavsevruk.resolver.type.TypeMeta;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements <code>TypeTestDataGenerator</code> for List type.
 *
 * @see NonParameterizedTypeDataGenerator
 */
@SuppressWarnings("java:S3740")
public class ListTestDataGenerator extends AbstractParameterizedTestDataGenerator<List> {

    private final TestDataGenerationContext context;

    public ListTestDataGenerator(TestDataGenerationContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canGenerate(Class<?> type) {
        return getTargetType().isAssignableFrom(type) || type.isAssignableFrom(getTargetType());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected List<?> doGenerate(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<? extends List> typeMeta) {
        int itemsNumber = RandomUtil.getInteger(testDataGenerationConfig.minItemsForCollections(),
                testDataGenerationConfig.maxItemsForCollections() + 1);
        TypeMeta<?> innerType = typeMeta.getGenericTypes()[0];
        List list = createItem(typeMeta.getType(), itemsNumber);
        for (int i = 0; i < itemsNumber; ++i) {
            list.add(context.getTestDataGenerationEngine().generate(testDataGenerationConfig, innerType));
        }
        return list;
    }

    @SuppressWarnings("java:S3740")
    @Override
    protected Class<List> getTargetType() {
        return List.class;
    }

    @Override
    protected void validateTypeMeta(TypeMeta<? extends List> toMeta) {
        super.validateTypeMeta(toMeta);
        int innerTypesNumber = toMeta.getGenericTypes().length;
        if (innerTypesNumber > 1) {
            String message = String
                    .format("Expected type meta size for target type: 0 or 1 but was: %d.", innerTypesNumber);
            throw new TypeGenerationException(message);
        }
    }

    private List<?> createItem(Class<?> type, int itemsNumber) {
        if (type.isAssignableFrom(AbstractList.class)) {
            return new ArrayList<>(itemsNumber);
        }
        return (List<?>) InstanceCreationUtil.createItem(type);
    }
}
