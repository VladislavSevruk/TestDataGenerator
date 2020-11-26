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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestDataGenerationContextImplTest {

    @Mock
    private CustomFieldMappingStorage customFieldMappingStorage;
    @Mock
    private ExecutableTypeResolver<TypeMeta<?>> executableTypeResolver;
    @Mock
    private FieldTypeResolver<TypeMeta<?>> fieldTypeResolver;
    @Mock
    private SetterMapper setterMapper;
    @Mock
    private TestDataGenerationEngine testDataGenerationEngine;
    @Mock
    private TestDataGeneratorPicker testDataGeneratorPicker;
    @Mock
    private TestDataGeneratorStorage testDataGeneratorStorage;

    @Test
    void customCustomFieldMappingStorageFactoryMethodReturnsNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(context -> null, null,
                null, null, null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customCustomFieldMappingStorageTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(
                context -> customFieldMappingStorage, null, null, null, null, null, null);
        Assertions.assertEquals(customFieldMappingStorage, testDataGenerationContext.getCustomFieldMappingStorage());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customExecutableTypeResolverFactoryMethodReturnsNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, context -> null,
                null, null, null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customExecutableTypeResolverTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null,
                context -> executableTypeResolver, null, null, null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(executableTypeResolver, testDataGenerationContext.getExecutableTypeResolver());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customFieldTypeResolverFactoryMethodReturnsNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null,
                context -> null, null, null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customFieldTypeResolverTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null,
                context -> fieldTypeResolver, null, null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions.assertEquals(fieldTypeResolver, testDataGenerationContext.getFieldTypeResolver());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customModulesFactoryMethodReturnNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(context -> null,
                context -> null, context -> null, context -> null, context -> null, context -> null, context -> null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customModulesTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(
                context -> customFieldMappingStorage, context -> executableTypeResolver, context -> fieldTypeResolver,
                context -> setterMapper, context -> testDataGenerationEngine, context -> testDataGeneratorPicker,
                context -> testDataGeneratorStorage);
        Assertions.assertEquals(customFieldMappingStorage, testDataGenerationContext.getCustomFieldMappingStorage());
        Assertions.assertEquals(executableTypeResolver, testDataGenerationContext.getExecutableTypeResolver());
        Assertions.assertEquals(fieldTypeResolver, testDataGenerationContext.getFieldTypeResolver());
        Assertions.assertEquals(setterMapper, testDataGenerationContext.getSetterMapper());
        Assertions.assertEquals(testDataGenerationEngine, testDataGenerationContext.getTestDataGenerationEngine());
        Assertions.assertEquals(testDataGeneratorPicker, testDataGenerationContext.getTestDataGeneratorPicker());
        Assertions.assertEquals(testDataGeneratorStorage, testDataGenerationContext.getTestDataGeneratorStorage());
    }

    @Test
    void customSetterMapperFactoryMethodReturnsNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null,
                context -> null, null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customSetterMapperTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null,
                context -> setterMapper, null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(setterMapper, testDataGenerationContext.getSetterMapper());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customTestDataGenerationEngineFactoryMethodReturnsNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null, null,
                context -> null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customTestDataGenerationEngineTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null, null,
                context -> testDataGenerationEngine, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(testDataGenerationEngine, testDataGenerationContext.getTestDataGenerationEngine());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customTestDataGeneratorPickerFactoryMethodReturnsNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null, null,
                null, context -> null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customTestDataGeneratorPickerTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null, null,
                null, context -> testDataGeneratorPicker, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(testDataGeneratorPicker, testDataGenerationContext.getTestDataGeneratorPicker());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customTestDataGeneratorStorageFactoryMethodReturnsNullTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null, null,
                null, null, context -> null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }

    @Test
    void customTestDataGeneratorStorageTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null, null,
                null, null, context -> testDataGeneratorStorage);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(testDataGeneratorStorage, testDataGenerationContext.getTestDataGeneratorStorage());
    }

    @Test
    void defaultModulesTest() {
        TestDataGenerationContext testDataGenerationContext = new TestDataGenerationContextImpl(null, null, null, null,
                null, null, null);
        Assertions.assertEquals(CustomFieldMappingStorageImpl.class,
                testDataGenerationContext.getCustomFieldMappingStorage().getClass());
        Assertions.assertEquals(ExecutableTypeMetaResolver.class,
                testDataGenerationContext.getExecutableTypeResolver().getClass());
        Assertions
                .assertEquals(FieldTypeMetaResolver.class, testDataGenerationContext.getFieldTypeResolver().getClass());
        Assertions.assertEquals(SetterMapperImpl.class, testDataGenerationContext.getSetterMapper().getClass());
        Assertions.assertEquals(TestDataGenerationEngineImpl.class,
                testDataGenerationContext.getTestDataGenerationEngine().getClass());
        Assertions.assertEquals(TestDataGeneratorPickerImpl.class,
                testDataGenerationContext.getTestDataGeneratorPicker().getClass());
        Assertions.assertEquals(TestDataGeneratorStorageImpl.class,
                testDataGenerationContext.getTestDataGeneratorStorage().getClass());
    }
}
