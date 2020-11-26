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
package com.github.vladislavsevruk.generator.test.data.generator.complex;

import com.github.vladislavsevruk.generator.test.data.config.TestDataGenerationConfig;
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.exception.TypeGenerationException;
import com.github.vladislavsevruk.generator.test.data.generator.AbstractParameterizedTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.util.InstanceCreationUtil;
import com.github.vladislavsevruk.generator.test.data.util.RandomUtil;
import com.github.vladislavsevruk.resolver.type.TypeMeta;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements <code>TypeTestDataGenerator</code> for Map type.
 *
 * @see NonParameterizedTypeDataGenerator
 */
@SuppressWarnings("java:S3740")
public class MapTestDataGenerator extends AbstractParameterizedTestDataGenerator<Map> {

    private final TestDataGenerationContext context;

    public MapTestDataGenerator(TestDataGenerationContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canGenerate(Class<?> type) {
        return getTargetType().isAssignableFrom(type) || type.isAssignableFrom(getTargetType());
    }

    @SuppressWarnings({ "unchecked", "java:S3740" })
    @Override
    protected Map doGenerate(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<? extends Map> typeMeta) {
        int itemsNumber = RandomUtil.getInteger(testDataGenerationConfig.minItemsForCollections(),
                testDataGenerationConfig.maxItemsForCollections() + 1);
        TypeMeta<?> keyType = typeMeta.getGenericTypes()[0];
        TypeMeta<?> valueType = typeMeta.getGenericTypes()[1];
        Map map = createItem(typeMeta.getType());
        for (int i = 0; i < itemsNumber; ++i) {
            map.put(context.getTestDataGenerationEngine().generate(testDataGenerationConfig, keyType),
                    context.getTestDataGenerationEngine().generate(testDataGenerationConfig, valueType));
        }
        return map;
    }

    @Override
    protected Class<Map> getTargetType() {
        return Map.class;
    }

    @Override
    protected void validateTypeMeta(TypeMeta<? extends Map> toMeta) {
        super.validateTypeMeta(toMeta);
        int innerTypesNumber = toMeta.getGenericTypes().length;
        if (innerTypesNumber != 0 && innerTypesNumber != 2) {
            String message = String
                    .format("Expected type meta size for target type: 0 or 2 but was: %d.", innerTypesNumber);
            throw new TypeGenerationException(message);
        }
    }

    private Map<?, ?> createItem(Class<?> type) {
        if (type.isAssignableFrom(AbstractMap.class)) {
            return new HashMap<>();
        }
        return (Map<?, ?>) InstanceCreationUtil.createItem(type);
    }
}
