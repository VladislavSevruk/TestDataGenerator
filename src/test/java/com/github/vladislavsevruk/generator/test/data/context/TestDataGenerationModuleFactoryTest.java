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
import com.github.vladislavsevruk.generator.test.data.storage.TestDataGeneratorStorage;
import com.github.vladislavsevruk.resolver.resolver.executable.ExecutableTypeResolver;
import com.github.vladislavsevruk.resolver.resolver.field.FieldTypeResolver;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestDataGenerationModuleFactoryTest {

    private static boolean initialAutoRefreshContext;

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

    @BeforeAll
    static void disableContextRefresh() {
        initialAutoRefreshContext = TestDataGenerationContextManager.isAutoRefreshContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
    }

    @AfterAll
    static void setInitialAutoContextRefresh() {
        if (initialAutoRefreshContext) {
            TestDataGenerationContextManager.enableContextAutoRefresh();
        }
    }

    @Test
    void replaceCustomFieldMappingStorageTest() {
        TestDataGenerationModuleFactoryMethod<CustomFieldMappingStorage> factoryMethod
                = context -> customFieldMappingStorage;
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(factoryMethod);
        Assertions.assertEquals(factoryMethod, TestDataGenerationModuleFactory.customFieldMappingStorage());
    }

    @Test
    void replaceExecutableTypeResolverTest() {
        TestDataGenerationModuleFactoryMethod<ExecutableTypeResolver<TypeMeta<?>>> factoryMethod
                = context -> executableTypeResolver;
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(factoryMethod);
        Assertions.assertEquals(factoryMethod, TestDataGenerationModuleFactory.executableTypeResolver());
    }

    @Test
    void replaceFieldTypeResolverTest() {
        TestDataGenerationModuleFactoryMethod<FieldTypeResolver<TypeMeta<?>>> factoryMethod
                = context -> fieldTypeResolver;
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(factoryMethod);
        Assertions.assertEquals(factoryMethod, TestDataGenerationModuleFactory.fieldTypeResolver());
    }

    @Test
    void replaceGetterSetterMapperTest() {
        TestDataGenerationModuleFactoryMethod<SetterMapper> factoryMethod = context -> setterMapper;
        TestDataGenerationModuleFactory.replaceSetterMapper(factoryMethod);
        Assertions.assertEquals(factoryMethod, TestDataGenerationModuleFactory.setterMapper());
    }

    @Test
    void replaceTestDataGenerationEngineTest() {
        TestDataGenerationModuleFactoryMethod<TestDataGenerationEngine> factoryMethod
                = context -> testDataGenerationEngine;
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(factoryMethod);
        Assertions.assertEquals(factoryMethod, TestDataGenerationModuleFactory.testDataGenerationEngine());
    }

    @Test
    void replaceTestDataGeneratorPickerTest() {
        TestDataGenerationModuleFactoryMethod<TestDataGeneratorPicker> factoryMethod
                = context -> testDataGeneratorPicker;
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(factoryMethod);
        Assertions.assertEquals(factoryMethod, TestDataGenerationModuleFactory.testDataGeneratorPicker());
    }

    @Test
    void replaceTypeConverterStorageTest() {
        TestDataGenerationModuleFactoryMethod<TestDataGeneratorStorage> factoryMethod
                = context -> testDataGeneratorStorage;
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(factoryMethod);
        Assertions.assertEquals(factoryMethod, TestDataGenerationModuleFactory.testDataGeneratorStorage());
    }
}
