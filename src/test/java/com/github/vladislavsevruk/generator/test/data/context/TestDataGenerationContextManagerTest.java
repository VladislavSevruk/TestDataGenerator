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
class TestDataGenerationContextManagerTest {

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
    static void enableContextAutoRefresh() {
        TestDataGenerationContextManager.enableContextAutoRefresh();
    }

    @AfterAll
    static void setInitialAutoContextRefresh() {
        resetModulesAndContext();
    }

    @Test
    void autoRefreshContextAfterAllModulesUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertSame(customFieldMappingStorage, testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertSame(executableTypeResolver, testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertSame(fieldTypeResolver, testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(setterMapper, testDataGenerationContext2.getSetterMapper());
        Assertions.assertSame(testDataGenerationEngine, testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertSame(testDataGeneratorPicker, testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertSame(testDataGeneratorStorage, testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterContextEngineUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertSame(testDataGenerationEngine, testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterCustomFieldMappingStorageUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertSame(customFieldMappingStorage, testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterExecutableTypeResolverUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertSame(executableTypeResolver, testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterFieldTypeResolverUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertSame(fieldTypeResolver, testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterSetterMapperUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(setterMapper, testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterTestDataGeneratorPickerUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertSame(testDataGeneratorPicker, testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterTestDataGeneratorStorageUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertSame(testDataGeneratorStorage, testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void equalContextAfterRefreshWithoutUpdatesTest() {
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void newContextAfterRefreshAfterAllModulesUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertSame(customFieldMappingStorage, testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertSame(executableTypeResolver, testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertSame(fieldTypeResolver, testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(setterMapper, testDataGenerationContext2.getSetterMapper());
        Assertions.assertSame(testDataGenerationEngine, testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertSame(testDataGeneratorPicker, testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertSame(testDataGeneratorStorage, testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterContextTestDataGenerationEngineUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertSame(testDataGenerationEngine, testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterCustomFieldMappingStorageUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertSame(customFieldMappingStorage, testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterExecutableTypeResolverUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertSame(executableTypeResolver, testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterFieldTypeResolverUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertSame(fieldTypeResolver, testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterSetterMapperUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(setterMapper, testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterTestDataGeneratorPickerUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertSame(testDataGeneratorPicker, testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorStorage(),
                testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterTestDataGeneratorStorageUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getSetterMapper(),
                testDataGenerationContext2.getSetterMapper());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGenerationEngine(),
                testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertNotSame(testDataGenerationContext1.getTestDataGeneratorPicker(),
                testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertSame(testDataGeneratorStorage, testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterCustomFieldMappingStorageUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterExecutableTypeResolverUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterFieldTypeResolverUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterRefreshAfterAllModulesUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterSetterMapperUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterTestDataGenerationEngineUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterTestDataGeneratorPickerUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterTestDataGeneratorStorageUpdatesTest() {
        resetModulesAndContext();
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedTest() {
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    private static void resetModulesAndContext() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(null);
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(null);
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(null);
        TestDataGenerationModuleFactory.replaceSetterMapper(null);
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(null);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(null);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(null);
        TestDataGenerationContextManager.refreshContext();
    }
}
