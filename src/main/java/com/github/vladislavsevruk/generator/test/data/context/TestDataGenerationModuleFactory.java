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
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Provides replaceable modules schemas required for test data generation mechanism.
 */
@Log4j2
public final class TestDataGenerationModuleFactory {

    private static final ReadWriteLock EXECUTABLE_TYPE_RESOLVER_LOCK = new ReentrantReadWriteLock();
    private static final ReadWriteLock FIELD_MAPPING_STORAGE_LOCK = new ReentrantReadWriteLock();
    private static final ReadWriteLock FIELD_TYPE_RESOLVER_LOCK = new ReentrantReadWriteLock();
    private static final ReadWriteLock GENERATION_ENGINE_LOCK = new ReentrantReadWriteLock();
    private static final ReadWriteLock POST_GENERATION_HOOK_STORAGE_LOCK = new ReentrantReadWriteLock();
    private static final ReadWriteLock SETTER_MAPPER_LOCK = new ReentrantReadWriteLock();
    private static final ReadWriteLock TYPE_GENERATOR_PICKER_LOCK = new ReentrantReadWriteLock();
    private static final ReadWriteLock TYPE_GENERATOR_STORAGE_LOCK = new ReentrantReadWriteLock();
    private static TestDataGenerationModuleFactoryMethod<CustomFieldMappingStorage> customFieldMappingStorage;
    private static TestDataGenerationModuleFactoryMethod<ExecutableTypeResolver<TypeMeta<?>>> executableTypeResolver;
    private static TestDataGenerationModuleFactoryMethod<FieldTypeResolver<TypeMeta<?>>> fieldTypeResolver;
    private static TestDataGenerationModuleFactoryMethod<PostGenerationHookStorage> postGenerationHookStorage;
    private static TestDataGenerationModuleFactoryMethod<SetterMapper> setterMapper;
    private static TestDataGenerationModuleFactoryMethod<TestDataGenerationEngine> testDataGenerationEngine;
    private static TestDataGenerationModuleFactoryMethod<TestDataGeneratorPicker> testDataGeneratorPicker;
    private static TestDataGenerationModuleFactoryMethod<TestDataGeneratorStorage> testDataGeneratorStorage;

    private TestDataGenerationModuleFactory() {
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     * <code>CustomFieldMappingStorage</code>.
     */
    public static TestDataGenerationModuleFactoryMethod<CustomFieldMappingStorage> customFieldMappingStorage() {
        FIELD_MAPPING_STORAGE_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<CustomFieldMappingStorage> storageToReturn
                = TestDataGenerationModuleFactory.customFieldMappingStorage;
        FIELD_MAPPING_STORAGE_LOCK.readLock().unlock();
        return storageToReturn;
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     * <code>ExecutableTypeResolver</code>.
     */
    @SuppressWarnings("java:S1452")
    public static TestDataGenerationModuleFactoryMethod<ExecutableTypeResolver<TypeMeta<?>>> executableTypeResolver() {
        EXECUTABLE_TYPE_RESOLVER_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<ExecutableTypeResolver<TypeMeta<?>>> executableTypeResolverToReturn
                = TestDataGenerationModuleFactory.executableTypeResolver;
        EXECUTABLE_TYPE_RESOLVER_LOCK.readLock().unlock();
        return executableTypeResolverToReturn;
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     * <code>FieldTypeResolver</code>.
     */
    @SuppressWarnings("java:S1452")
    public static TestDataGenerationModuleFactoryMethod<FieldTypeResolver<TypeMeta<?>>> fieldTypeResolver() {
        FIELD_TYPE_RESOLVER_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<FieldTypeResolver<TypeMeta<?>>> fieldTypeResolverToReturn
                = TestDataGenerationModuleFactory.fieldTypeResolver;
        FIELD_TYPE_RESOLVER_LOCK.readLock().unlock();
        return fieldTypeResolverToReturn;
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     * <code>PostGenerationHookStorage</code>.
     */
    public static TestDataGenerationModuleFactoryMethod<PostGenerationHookStorage> postGenerationHookStorage() {
        POST_GENERATION_HOOK_STORAGE_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<PostGenerationHookStorage> postGenerationHookStorageToReturn
                = TestDataGenerationModuleFactory.postGenerationHookStorage;
        POST_GENERATION_HOOK_STORAGE_LOCK.readLock().unlock();
        return postGenerationHookStorageToReturn;
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>CustomFieldMappingStorage</code>.
     * All further generations will use new instance.
     *
     * @param storage new instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     *                <code>CustomFieldMappingStorage</code>.
     */
    public static void replaceCustomFieldMappingStorage(
            TestDataGenerationModuleFactoryMethod<CustomFieldMappingStorage> storage) {
        FIELD_MAPPING_STORAGE_LOCK.writeLock().lock();
        log.info(() -> String.format("Replacing CustomFieldMappingStorage by '%s'.",
                storage == null ? null : storage.getClass().getName()));
        TestDataGenerationModuleFactory.customFieldMappingStorage = storage;
        FIELD_MAPPING_STORAGE_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>ExecutableTypeResolver</code>.
     * All further generations will use new instance.
     *
     * @param resolver new instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     *                 <code>ExecutableTypeResolver</code>.
     */
    public static void replaceExecutableTypeResolver(
            TestDataGenerationModuleFactoryMethod<ExecutableTypeResolver<TypeMeta<?>>> resolver) {
        EXECUTABLE_TYPE_RESOLVER_LOCK.writeLock().lock();
        log.info(() -> String.format("Replacing ExecutableTypeResolver by '%s'.",
                resolver == null ? null : resolver.getClass().getName()));
        TestDataGenerationModuleFactory.executableTypeResolver = resolver;
        EXECUTABLE_TYPE_RESOLVER_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>FieldTypeResolver</code>. All
     * further generations will use new instance.
     *
     * @param resolver new instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     *                 <code>FieldTypeResolver</code>.
     */
    public static void replaceFieldTypeResolver(
            TestDataGenerationModuleFactoryMethod<FieldTypeResolver<TypeMeta<?>>> resolver) {
        FIELD_TYPE_RESOLVER_LOCK.writeLock().lock();
        log.info(() -> String.format("Replacing FieldTypeResolver by '%s'.",
                resolver == null ? null : resolver.getClass().getName()));
        TestDataGenerationModuleFactory.fieldTypeResolver = resolver;
        FIELD_TYPE_RESOLVER_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>PostGenerationHookStorage</code>.
     * All further generations will use new instance.
     *
     * @param storage new instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     *                <code>PostGenerationHookStorage</code>.
     */
    public static void replacePostGenerationHookStorage(
            TestDataGenerationModuleFactoryMethod<PostGenerationHookStorage> storage) {
        POST_GENERATION_HOOK_STORAGE_LOCK.writeLock().lock();
        log.info(() -> String.format("Replacing PostGenerationHookStorage by '%s'.",
                storage == null ? null : storage.getClass().getName()));
        TestDataGenerationModuleFactory.postGenerationHookStorage = storage;
        POST_GENERATION_HOOK_STORAGE_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>SetterMapper</code>. All
     * further generations will use new instance.
     *
     * @param mapper new instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>SetterMapper</code>.
     */
    public static void replaceSetterMapper(TestDataGenerationModuleFactoryMethod<SetterMapper> mapper) {
        SETTER_MAPPER_LOCK.writeLock().lock();
        log.info(() -> String
                .format("Replacing SetterMapper by '%s'.", mapper == null ? null : mapper.getClass().getName()));
        TestDataGenerationModuleFactory.setterMapper = mapper;
        SETTER_MAPPER_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>TestDataGenerationEngine</code>.
     * All further generations will use new instance.
     *
     * @param engine new instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     *               <code>TestDataGenerationEngine</code>.
     */
    public static void replaceTestDataGenerationEngine(
            TestDataGenerationModuleFactoryMethod<TestDataGenerationEngine> engine) {
        GENERATION_ENGINE_LOCK.writeLock().lock();
        log.info(() -> String.format("Replacing TestDataGenerationEngine by '%s'.",
                engine == null ? null : engine.getClass().getName()));
        TestDataGenerationModuleFactory.testDataGenerationEngine = engine;
        GENERATION_ENGINE_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>TestDataGeneratorPicker</code>.
     * All further generations will use new instance.
     *
     * @param picker new instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     *               <code>TestDataGeneratorPicker</code>.
     */
    public static void replaceTestDataGeneratorPicker(
            TestDataGenerationModuleFactoryMethod<TestDataGeneratorPicker> picker) {
        TYPE_GENERATOR_PICKER_LOCK.writeLock().lock();
        log.info(() -> String.format("Replacing TestDataGeneratorPicker by '%s'.",
                picker == null ? null : picker.getClass().getName()));
        TestDataGenerationModuleFactory.testDataGeneratorPicker = picker;
        TYPE_GENERATOR_PICKER_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Replaces instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>TestDataGeneratorStorage</code>.
     * All further generations will use new instance.
     *
     * @param storage new instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     *                <code>TestDataGeneratorStorage</code>.
     */
    public static void replaceTestDataGeneratorStorage(
            TestDataGenerationModuleFactoryMethod<TestDataGeneratorStorage> storage) {
        TYPE_GENERATOR_STORAGE_LOCK.writeLock().lock();
        log.info(() -> String.format("Replacing TestDataGeneratorStorage by '%s'.",
                storage == null ? null : storage.getClass().getName()));
        TestDataGenerationModuleFactory.testDataGeneratorStorage = storage;
        TYPE_GENERATOR_STORAGE_LOCK.writeLock().unlock();
        if (TestDataGenerationContextManager.isAutoRefreshContext()) {
            TestDataGenerationContextManager.refreshContext();
        }
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for <code>SetterMapper</code>.
     */
    public static TestDataGenerationModuleFactoryMethod<SetterMapper> setterMapper() {
        SETTER_MAPPER_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<SetterMapper> setterMapperToReturn
                = TestDataGenerationModuleFactory.setterMapper;
        SETTER_MAPPER_LOCK.readLock().unlock();
        return setterMapperToReturn;
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     * <code>TestDataGenerationEngine</code>.
     */
    public static TestDataGenerationModuleFactoryMethod<TestDataGenerationEngine> testDataGenerationEngine() {
        GENERATION_ENGINE_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<TestDataGenerationEngine> engineToReturn
                = TestDataGenerationModuleFactory.testDataGenerationEngine;
        GENERATION_ENGINE_LOCK.readLock().unlock();
        return engineToReturn;
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     * <code>TestDataGeneratorPicker</code>.
     */
    public static TestDataGenerationModuleFactoryMethod<TestDataGeneratorPicker> testDataGeneratorPicker() {
        TYPE_GENERATOR_PICKER_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<TestDataGeneratorPicker> pickerToReturn
                = TestDataGenerationModuleFactory.testDataGeneratorPicker;
        TYPE_GENERATOR_PICKER_LOCK.readLock().unlock();
        return pickerToReturn;
    }

    /**
     * Returns current instance of <code>TestDataGenerationModuleFactoryMethod</code> for
     * <code>TestDataGeneratorStorage</code>.
     */
    public static TestDataGenerationModuleFactoryMethod<TestDataGeneratorStorage> testDataGeneratorStorage() {
        TYPE_GENERATOR_STORAGE_LOCK.readLock().lock();
        TestDataGenerationModuleFactoryMethod<TestDataGeneratorStorage> storageToReturn
                = TestDataGenerationModuleFactory.testDataGeneratorStorage;
        TYPE_GENERATOR_STORAGE_LOCK.readLock().unlock();
        return storageToReturn;
    }
}