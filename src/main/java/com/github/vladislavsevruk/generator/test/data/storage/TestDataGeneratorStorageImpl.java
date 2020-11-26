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
package com.github.vladislavsevruk.generator.test.data.storage;

import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.generator.DataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.ParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.complex.EnumTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.complex.MapTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.complex.PojoTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.iterable.ArrayTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.iterable.ListTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.iterable.SetTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.BooleanTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.ByteTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.CharacterTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.DoubleTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.FloatTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.IntegerTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.LongTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.ShortTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.simple.StringTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.util.ClassUtil;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Implementation of <code>TestDataGeneratorStorage</code>.
 *
 * @see NonParameterizedTypeDataGenerator
 * @see TestDataGeneratorStorage
 */
@Log4j2
public final class TestDataGeneratorStorageImpl implements TestDataGeneratorStorage {

    private static final ReadWriteLock GENERATORS_LOCK = new ReentrantReadWriteLock();
    private List<DataGenerator> generators = new LinkedList<>();

    /**
     * Sets up list of active generators and unmodifiable list of default generators.
     *
     * @param generationContext <code>TestDataGenerationContext</code> to use.
     */
    public TestDataGeneratorStorageImpl(TestDataGenerationContext generationContext) {
        addDefaultGenerators(generationContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(NonParameterizedTypeDataGenerator<?> customGenerator) {
        GENERATORS_LOCK.writeLock().lock();
        add((DataGenerator) customGenerator);
        GENERATORS_LOCK.writeLock().unlock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ParameterizedTypeDataGenerator<?> customGenerator) {
        GENERATORS_LOCK.writeLock().lock();
        add((DataGenerator) customGenerator);
        GENERATORS_LOCK.writeLock().unlock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAfter(NonParameterizedTypeDataGenerator<?> customGenerator,
            Class<? extends DataGenerator> targetType) {
        addAfter((DataGenerator) customGenerator, targetType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAfter(ParameterizedTypeDataGenerator<?> customGenerator, Class<? extends DataGenerator> targetType) {
        addAfter((DataGenerator) customGenerator, targetType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBefore(NonParameterizedTypeDataGenerator<?> customGenerator,
            Class<? extends DataGenerator> targetType) {
        addBefore((DataGenerator) customGenerator, targetType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBefore(ParameterizedTypeDataGenerator<?> customGenerator,
            Class<? extends DataGenerator> targetType) {
        addBefore((DataGenerator) customGenerator, targetType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DataGenerator> getAll() {
        GENERATORS_LOCK.readLock().lock();
        List<DataGenerator> generatorsList = generators.isEmpty() ? Collections.emptyList()
                : new ArrayList<>(generators);
        GENERATORS_LOCK.readLock().unlock();
        return generatorsList;
    }

    private void add(DataGenerator customGenerator) {
        add(generators.size(), customGenerator);
    }

    private void add(int index, DataGenerator customGenerator) {
        if (customGenerator == null) {
            log.info("Received generator is null so it will not be added.");
            return;
        }
        if (ClassUtil.getIndexOfType(generators, customGenerator.getClass()) != -1) {
            log.info("Received generator is already present at list so it's copy will not be added.");
            return;
        }
        generators.add(index, customGenerator);
    }

    private void addAfter(DataGenerator customGenerator, Class<? extends DataGenerator> targetType) {
        GENERATORS_LOCK.writeLock().lock();
        int targetTypeIndex = ClassUtil.getIndexOfType(generators, targetType);
        if (targetTypeIndex == -1) {
            log.info("Target type is not present at list, generator will be added to list end.");
            add(customGenerator);
        } else {
            add(targetTypeIndex + 1, customGenerator);
        }
        GENERATORS_LOCK.writeLock().unlock();
    }

    private void addBefore(DataGenerator customGenerator, Class<? extends DataGenerator> targetType) {
        GENERATORS_LOCK.writeLock().lock();
        int targetTypeIndex = ClassUtil.getIndexOfType(generators, targetType);
        if (targetTypeIndex == -1) {
            log.info("Target type is not present at list, generator will be added to list end.");
            add(customGenerator);
        } else {
            add(targetTypeIndex, customGenerator);
        }
        GENERATORS_LOCK.writeLock().unlock();
    }

    private void addDefaultGenerators(TestDataGenerationContext generationContext) {
        generators.add(new StringTestDataGenerator());
        generators.add(new BooleanTestDataGenerator());
        addNumberGenerators();
        generators.add(new CharacterTestDataGenerator());
        generators.add(new EnumTestDataGenerator());
        addElementSequenceGenerators(generationContext);
        generators.add(new MapTestDataGenerator(generationContext));
        generators.add(new PojoTestDataGenerator(generationContext));
    }

    private void addElementSequenceGenerators(TestDataGenerationContext generationContext) {
        generators.add(new ArrayTestDataGenerator(generationContext));
        generators.add(new ListTestDataGenerator(generationContext));
        generators.add(new SetTestDataGenerator(generationContext));
    }

    private void addNumberGenerators() {
        generators.add(new ByteTestDataGenerator());
        generators.add(new DoubleTestDataGenerator());
        generators.add(new FloatTestDataGenerator());
        generators.add(new IntegerTestDataGenerator());
        generators.add(new LongTestDataGenerator());
        generators.add(new ShortTestDataGenerator());
    }
}
