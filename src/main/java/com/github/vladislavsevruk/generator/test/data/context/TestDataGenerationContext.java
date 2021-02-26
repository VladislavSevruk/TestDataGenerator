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
package com.github.vladislavsevruk.generator.test.data.context;

import com.github.vladislavsevruk.generator.test.data.engine.TestDataGenerationEngine;
import com.github.vladislavsevruk.generator.test.data.mapping.CustomFieldMappingStorage;
import com.github.vladislavsevruk.generator.test.data.mapping.SetterMapper;
import com.github.vladislavsevruk.generator.test.data.picker.TestDataGeneratorPicker;
import com.github.vladislavsevruk.generator.test.data.storage.PostGenerationHookStorage;
import com.github.vladislavsevruk.generator.test.data.storage.TestDataGeneratorStorage;
import com.github.vladislavsevruk.resolver.resolver.executable.ExecutableTypeResolver;
import com.github.vladislavsevruk.resolver.resolver.field.FieldTypeResolver;
import com.github.vladislavsevruk.resolver.type.TypeMeta;

/**
 * Test data generation context with replaceable modules.
 */
public interface TestDataGenerationContext {

    /**
     * Returns current instance of <code>CustomFieldMappingStorage</code> stored at context.
     */
    CustomFieldMappingStorage getCustomFieldMappingStorage();

    /**
     * Returns current instance of <code>ExecutableTypeResolver</code> stored at context.
     */
    @SuppressWarnings("java:S1452")
    ExecutableTypeResolver<TypeMeta<?>> getExecutableTypeResolver();

    /**
     * Returns current instance of <code>FieldTypeResolver</code> stored at context.
     */
    @SuppressWarnings("java:S1452")
    FieldTypeResolver<TypeMeta<?>> getFieldTypeResolver();

    /**
     * Returns current instance of <code>PostGenerationHookStorage</code> stored at context.
     */
    PostGenerationHookStorage getPostGenerationHookStorage();

    /**
     * Returns current instance of <code>SetterMapper</code> stored at context.
     */
    SetterMapper getSetterMapper();

    /**
     * Returns current instance of <code>TestDataGenerationEngine</code> stored at context.
     */
    TestDataGenerationEngine getTestDataGenerationEngine();

    /**
     * Returns current instance of <code>TestDataGeneratorPicker</code> stored at context.
     */
    TestDataGeneratorPicker getTestDataGeneratorPicker();

    /**
     * Returns current instance of <code>TestDataGeneratorStorage</code> stored at context.
     */
    TestDataGeneratorStorage getTestDataGeneratorStorage();
}
