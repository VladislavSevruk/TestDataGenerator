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
import com.github.vladislavsevruk.generator.test.data.engine.TestDataGenerationEngineImpl;
import com.github.vladislavsevruk.generator.test.data.mapping.CustomFieldMappingStorage;
import com.github.vladislavsevruk.generator.test.data.mapping.CustomFieldMappingStorageImpl;
import com.github.vladislavsevruk.generator.test.data.mapping.SetterMapper;
import com.github.vladislavsevruk.generator.test.data.mapping.SetterMapperImpl;
import com.github.vladislavsevruk.generator.test.data.picker.TestDataGeneratorPicker;
import com.github.vladislavsevruk.generator.test.data.picker.TestDataGeneratorPickerImpl;
import com.github.vladislavsevruk.generator.test.data.storage.TestDataGeneratorStorage;
import com.github.vladislavsevruk.generator.test.data.storage.TestDataGeneratorStorageImpl;
import com.github.vladislavsevruk.resolver.resolver.executable.ExecutableTypeMetaResolver;
import com.github.vladislavsevruk.resolver.resolver.executable.ExecutableTypeResolver;
import com.github.vladislavsevruk.resolver.resolver.field.FieldTypeMetaResolver;
import com.github.vladislavsevruk.resolver.resolver.field.FieldTypeResolver;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

/**
 * Implementation of <code>TestDataGenerationContext</code>.
 *
 * @see TestDataGenerationContext
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Log4j2
final class TestDataGenerationContextImpl implements TestDataGenerationContext {

    CustomFieldMappingStorage customFieldMappingStorage;
    ExecutableTypeResolver<TypeMeta<?>> executableTypeResolver;
    FieldTypeResolver<TypeMeta<?>> fieldTypeResolver;
    SetterMapper setterMapper;
    TestDataGenerationEngine testDataGenerationEngine;
    TestDataGeneratorPicker testDataGeneratorPicker;
    TestDataGeneratorStorage testDataGeneratorStorage;

    /**
     * Creates new instance using received modules or default implementations for nulls.
     *
     * @param executableTypeResolverFactoryMethod   factory method for <code>ExecutableTypeResolver</code> module
     *                                              implementation.
     * @param fieldTypeResolverFactoryMethod        factory method for <code>FieldTypeResolver</code> module
     *                                              implementation.
     * @param setterMapperFactoryMethod             factory method for <code>SetterMapper</code> module implementation.
     * @param testDataGenerationEngineFactoryMethod factory method for <code>TestDataGenerationEngine</code> module
     *                                              implementation.
     * @param testDataGeneratorPickerFactoryMethod  factory method for <code>TestDataGeneratorPicker</code> module
     *                                              implementation.
     * @param testDataGeneratorStorageFactoryMethod factory method for <code>TestDataGeneratorStorage</code> module
     *                                              implementation.
     */
    TestDataGenerationContextImpl(
            TestDataGenerationModuleFactoryMethod<CustomFieldMappingStorage> customFieldMappingStorageFactoryMethod,
            TestDataGenerationModuleFactoryMethod<ExecutableTypeResolver<TypeMeta<?>>> executableTypeResolverFactoryMethod,
            TestDataGenerationModuleFactoryMethod<FieldTypeResolver<TypeMeta<?>>> fieldTypeResolverFactoryMethod,
            TestDataGenerationModuleFactoryMethod<SetterMapper> setterMapperFactoryMethod,
            TestDataGenerationModuleFactoryMethod<TestDataGenerationEngine> testDataGenerationEngineFactoryMethod,
            TestDataGenerationModuleFactoryMethod<TestDataGeneratorPicker> testDataGeneratorPickerFactoryMethod,
            TestDataGenerationModuleFactoryMethod<TestDataGeneratorStorage> testDataGeneratorStorageFactoryMethod) {
        this.testDataGenerationEngine = orDefault(testDataGenerationEngineFactoryMethod,
                TestDataGenerationEngineImpl::new);
        log.debug(() -> String
                .format("Using '%s' as test data generation engine.", testDataGenerationEngine.getClass().getName()));
        this.customFieldMappingStorage = orDefault(customFieldMappingStorageFactoryMethod,
                context -> new CustomFieldMappingStorageImpl());
        log.debug(() -> String
                .format("Using '%s' as custom field mapping storage.", customFieldMappingStorage.getClass().getName()));
        this.executableTypeResolver = orDefault(executableTypeResolverFactoryMethod,
                context -> new ExecutableTypeMetaResolver());
        log.debug(() -> String
                .format("Using '%s' as method type resolver.", executableTypeResolver.getClass().getName()));
        this.fieldTypeResolver = orDefault(fieldTypeResolverFactoryMethod, context -> new FieldTypeMetaResolver());
        log.debug(() -> String.format("Using '%s' as field type resolver.", fieldTypeResolver.getClass().getName()));
        this.testDataGeneratorStorage = orDefault(testDataGeneratorStorageFactoryMethod,
                TestDataGeneratorStorageImpl::new);
        log.debug(() -> String
                .format("Using '%s' as test data generator storage.", testDataGeneratorStorage.getClass().getName()));
        this.testDataGeneratorPicker = orDefault(testDataGeneratorPickerFactoryMethod,
                TestDataGeneratorPickerImpl::new);
        log.debug(() -> String.format("Using '%s' as type test data generator picker.",
                testDataGeneratorPicker.getClass().getName()));
        this.setterMapper = orDefault(setterMapperFactoryMethod, SetterMapperImpl::new);
        log.debug(() -> String.format("Using '%s' as setter mapper.", setterMapper.getClass().getName()));
    }

    private <T> T orDefault(TestDataGenerationModuleFactoryMethod<T> factoryMethod,
            TestDataGenerationModuleFactoryMethod<T> defaultFactoryMethod) {
        if (factoryMethod != null) {
            T module = factoryMethod.get(this);
            if (module != null) {
                return module;
            }
        }
        return defaultFactoryMethod.get(this);
    }
}
