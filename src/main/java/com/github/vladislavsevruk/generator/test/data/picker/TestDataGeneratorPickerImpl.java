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
package com.github.vladislavsevruk.generator.test.data.picker;

import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.generator.DataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.ParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.ParameterizedTypeDataGeneratorAdapter;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import com.github.vladislavsevruk.resolver.util.PrimitiveWrapperUtil;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

/**
 * Implementation of <code>TestDataGeneratorPicker</code>.
 *
 * @see TestDataGeneratorPicker
 */
@Log4j2
public final class TestDataGeneratorPickerImpl implements TestDataGeneratorPicker {

    private final TestDataGenerationContext conversionContext;

    public TestDataGeneratorPickerImpl(TestDataGenerationContext conversionContext) {
        this.conversionContext = conversionContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> NonParameterizedTypeDataGenerator<T> pickGenerator(TypeMeta<T> typeMeta) {
        log.debug(
                () -> String.format("Trying to find matching generator for '%s' type.", typeMeta.getType().getName()));
        NonParameterizedTypeDataGenerator<T> pickedGenerator = doPickGenerator(typeMeta);
        log.debug(() -> Objects.isNull(pickedGenerator) ? "Didn't find any matching generator."
                : "Found matching generator: " + pickedGenerator.getClass().getName());
        return pickedGenerator;
    }

    private <T> NonParameterizedTypeDataGenerator<T> doPickGenerator(TypeMeta<T> typeMeta) {
        Class<?> type = PrimitiveWrapperUtil.wrap(typeMeta.getType());
        for (DataGenerator generator : conversionContext.getTestDataGeneratorStorage().getAll()) {
            if (generator.canGenerate(type)) {
                return getNonParameterizedTypeTestDataGenerator(generator, typeMeta);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> NonParameterizedTypeDataGenerator<T> getNonParameterizedTypeTestDataGenerator(DataGenerator generator,
            TypeMeta<T> typeMeta) {
        if (NonParameterizedTypeDataGenerator.class.isAssignableFrom(generator.getClass())) {
            return (NonParameterizedTypeDataGenerator<T>) generator;
        }
        return new ParameterizedTypeDataGeneratorAdapter<>((ParameterizedTypeDataGenerator<T>) generator, typeMeta);
    }
}
