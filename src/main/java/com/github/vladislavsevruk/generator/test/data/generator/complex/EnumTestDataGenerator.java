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
import com.github.vladislavsevruk.generator.test.data.generator.AbstractParameterizedTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.ParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.util.RandomUtil;
import com.github.vladislavsevruk.resolver.type.TypeMeta;

/**
 * Implements <code>TypeTestDataGenerator</code> for Boolean type.
 *
 * @see ParameterizedTypeDataGenerator
 */
@SuppressWarnings("java:S3740")
public class EnumTestDataGenerator extends AbstractParameterizedTestDataGenerator<Enum> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canGenerate(Class<?> type) {
        return type.isEnum();
    }

    @Override
    protected Enum doGenerate(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<? extends Enum> typeMeta) {
        Enum[] enumConstants = typeMeta.getType().getEnumConstants();
        return RandomUtil.getItem(enumConstants);
    }

    @Override
    protected Class<Enum> getTargetType() {
        return Enum.class;
    }
}
