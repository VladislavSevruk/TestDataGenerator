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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    private PostGenerationHookStorage postGenerationHookStorage;
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

    @BeforeEach
    @AfterEach
    void resetModulesAndContext() {
        ContextUtil.resetModulesAndContext();
    }

    @Test
    void autoRefreshContextAfterAllModulesUpdatesTest() {
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationModuleFactory.replacePostGenerationHookStorage(context -> postGenerationHookStorage);
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertSame(customFieldMappingStorage, testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertSame(executableTypeResolver, testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertSame(fieldTypeResolver, testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(postGenerationHookStorage, testDataGenerationContext2.getPostGenerationHookStorage());
        Assertions.assertSame(setterMapper, testDataGenerationContext2.getSetterMapper());
        Assertions.assertSame(testDataGenerationEngine, testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertSame(testDataGeneratorPicker, testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertSame(testDataGeneratorStorage, testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void autoRefreshContextAfterContextEngineUpdatesTest() {
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
    void autoRefreshContextAfterPostGenerationHookStorageUpdatesTest() {
        TestDataGenerationContextManager.enableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replacePostGenerationHookStorage(context -> postGenerationHookStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(postGenerationHookStorage, testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationModuleFactory.replacePostGenerationHookStorage(context -> postGenerationHookStorage);
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertSame(customFieldMappingStorage, testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertSame(executableTypeResolver, testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertSame(postGenerationHookStorage, testDataGenerationContext2.getPostGenerationHookStorage());
        Assertions.assertSame(fieldTypeResolver, testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(setterMapper, testDataGenerationContext2.getSetterMapper());
        Assertions.assertSame(testDataGenerationEngine, testDataGenerationContext2.getTestDataGenerationEngine());
        Assertions.assertSame(testDataGeneratorPicker, testDataGenerationContext2.getTestDataGeneratorPicker());
        Assertions.assertSame(testDataGeneratorStorage, testDataGenerationContext2.getTestDataGeneratorStorage());
    }

    @Test
    void newContextAfterRefreshAfterContextTestDataGenerationEngineUpdatesTest() {
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
    void newContextAfterRefreshAfterPostGenerationHookStorageUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replacePostGenerationHookStorage(context -> postGenerationHookStorage);
        TestDataGenerationContextManager.refreshContext();
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertNotSame(testDataGenerationContext1, testDataGenerationContext2);
        Assertions.assertNotSame(testDataGenerationContext1.getCustomFieldMappingStorage(),
                testDataGenerationContext2.getCustomFieldMappingStorage());
        Assertions.assertNotSame(testDataGenerationContext1.getExecutableTypeResolver(),
                testDataGenerationContext2.getExecutableTypeResolver());
        Assertions.assertNotSame(testDataGenerationContext1.getFieldTypeResolver(),
                testDataGenerationContext2.getFieldTypeResolver());
        Assertions.assertSame(postGenerationHookStorage, testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        Assertions.assertNotSame(testDataGenerationContext1.getPostGenerationHookStorage(),
                testDataGenerationContext2.getPostGenerationHookStorage());
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
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterExecutableTypeResolverUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterFieldTypeResolverUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterRefreshAfterAllModulesUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceCustomFieldMappingStorage(context -> customFieldMappingStorage);
        TestDataGenerationModuleFactory.replaceExecutableTypeResolver(context -> executableTypeResolver);
        TestDataGenerationModuleFactory.replaceFieldTypeResolver(context -> fieldTypeResolver);
        TestDataGenerationModuleFactory.replacePostGenerationHookStorage(context -> postGenerationHookStorage);
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationModuleFactory.replaceTestDataGeneratorStorage(context -> testDataGeneratorStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterPostGenerationHookStorageUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replacePostGenerationHookStorage(context -> postGenerationHookStorage);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterSetterMapperUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceSetterMapper(context -> setterMapper);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterTestDataGenerationEngineUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGenerationEngine(context -> testDataGenerationEngine);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterTestDataGeneratorPickerUpdatesTest() {
        TestDataGenerationContextManager.disableContextAutoRefresh();
        TestDataGenerationContext testDataGenerationContext1 = TestDataGenerationContextManager.getContext();
        TestDataGenerationModuleFactory.replaceTestDataGeneratorPicker(context -> testDataGeneratorPicker);
        TestDataGenerationContext testDataGenerationContext2 = TestDataGenerationContextManager.getContext();
        Assertions.assertSame(testDataGenerationContext1, testDataGenerationContext2);
    }

    @Test
    void sameContextIsReturnedIfAutoRefreshDisabledAfterTestDataGeneratorStorageUpdatesTest() {
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
}
