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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TestDataGeneratorStorageImplTest {

    @Mock
    private NonParameterizedTypeDataGenerator nonParameterizedTypeDataGenerator;
    @Mock
    private ParameterizedTypeDataGenerator parameterizedTypeDataGenerator;
    @Mock
    private TestDataGenerationContext testDataGenerationContext;

    @Test
    void addExistentNonParameterizedTypeDataGeneratorAfterExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage.addAfter(nonParameterizedDataGenerator, nonParameterizedDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentNonParameterizedTypeDataGeneratorAfterNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage.addAfter(nonParameterizedDataGenerator, nonParameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentNonParameterizedTypeDataGeneratorAfterNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage.addAfter(nonParameterizedDataGenerator, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentNonParameterizedTypeDataGeneratorBeforeExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage.addBefore(nonParameterizedDataGenerator, nonParameterizedDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentNonParameterizedTypeDataGeneratorBeforeNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage.addBefore(nonParameterizedDataGenerator, nonParameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentNonParameterizedTypeDataGeneratorBeforeNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage.addBefore(nonParameterizedDataGenerator, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentNonParameterizedTypeDataGeneratorTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        dataGeneratorStorage.add(getNonParameterizedTypeDataGenerator(dataGenerators));
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentParameterizedTypeDataGeneratorAfterExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addAfter(parameterizedGenerator, parameterizedGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentParameterizedTypeDataGeneratorAfterNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addAfter(parameterizedGenerator, parameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentParameterizedTypeDataGeneratorAfterNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addAfter(parameterizedGenerator, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentParameterizedTypeDataGeneratorBeforeExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addBefore(parameterizedGenerator, parameterizedGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentParameterizedTypeDataGeneratorBeforeNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addBefore(parameterizedGenerator, parameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentParameterizedTypeDataGeneratorBeforeNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addBefore(parameterizedGenerator, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addExistentParameterizedTypeDataGeneratorTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        dataGeneratorStorage.add(getParameterizedTypeDataGenerator(dataGenerators));
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNewNonParameterizedTypeDataGeneratorAfterExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        int indexBefore = getIndexOfGenerator(dataGenerators, nonParameterizedDataGenerator.getClass());
        dataGeneratorStorage.addAfter(nonParameterizedTypeDataGenerator, nonParameterizedDataGenerator.getClass());
        dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(indexBefore + 1,
                getIndexOfGenerator(dataGenerators, nonParameterizedTypeDataGenerator.getClass()));
        Assertions.assertEquals(indexBefore,
                getIndexOfGenerator(dataGenerators, nonParameterizedDataGenerator.getClass()));
    }

    @Test
    void addNewNonParameterizedTypeDataGeneratorAfterNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addAfter(nonParameterizedTypeDataGenerator, nonParameterizedTypeDataGenerator.getClass());
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, nonParameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewNonParameterizedTypeDataGeneratorAfterNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addAfter(nonParameterizedTypeDataGenerator, null);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, nonParameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewNonParameterizedTypeDataGeneratorBeforeExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        int indexBefore = getIndexOfGenerator(dataGenerators, nonParameterizedDataGenerator.getClass());
        dataGeneratorStorage.addBefore(nonParameterizedTypeDataGenerator, nonParameterizedDataGenerator.getClass());
        dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(indexBefore,
                getIndexOfGenerator(dataGenerators, nonParameterizedTypeDataGenerator.getClass()));
        Assertions.assertEquals(indexBefore + 1,
                getIndexOfGenerator(dataGenerators, nonParameterizedDataGenerator.getClass()));
    }

    @Test
    void addNewNonParameterizedTypeDataGeneratorBeforeNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addBefore(nonParameterizedTypeDataGenerator, nonParameterizedTypeDataGenerator.getClass());
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, nonParameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewNonParameterizedTypeDataGeneratorBeforeNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addBefore(nonParameterizedTypeDataGenerator, null);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, nonParameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewNonParameterizedTypeDataGeneratorTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.add(nonParameterizedTypeDataGenerator);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
    }

    @Test
    void addNewParameterizedTypeDataGeneratorAfterExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        int indexBefore = getIndexOfGenerator(dataGenerators, parameterizedGenerator.getClass());
        dataGeneratorStorage.addAfter(parameterizedTypeDataGenerator, parameterizedGenerator.getClass());
        dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(indexBefore + 1,
                getIndexOfGenerator(dataGenerators, parameterizedTypeDataGenerator.getClass()));
        Assertions.assertEquals(indexBefore, getIndexOfGenerator(dataGenerators, parameterizedGenerator.getClass()));
    }

    @Test
    void addNewParameterizedTypeDataGeneratorAfterNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addAfter(parameterizedTypeDataGenerator, parameterizedTypeDataGenerator.getClass());
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, parameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewParameterizedTypeDataGeneratorAfterNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addAfter(parameterizedTypeDataGenerator, null);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, parameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewParameterizedTypeDataGeneratorBeforeExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        int indexBefore = getIndexOfGenerator(dataGenerators, parameterizedGenerator.getClass());
        dataGeneratorStorage.addBefore(parameterizedTypeDataGenerator, parameterizedGenerator.getClass());
        dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(indexBefore,
                getIndexOfGenerator(dataGenerators, parameterizedTypeDataGenerator.getClass()));
        Assertions
                .assertEquals(indexBefore + 1, getIndexOfGenerator(dataGenerators, parameterizedGenerator.getClass()));
    }

    @Test
    void addNewParameterizedTypeDataGeneratorBeforeNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addBefore(parameterizedTypeDataGenerator, parameterizedTypeDataGenerator.getClass());
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, parameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewParameterizedTypeDataGeneratorBeforeNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addBefore(parameterizedTypeDataGenerator, null);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsAfter = dataGenerators.size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
        Assertions.assertEquals(numberOfGeneratorsAfter - 1,
                getIndexOfGenerator(dataGenerators, parameterizedTypeDataGenerator.getClass()));
    }

    @Test
    void addNewParameterizedTypeDataGeneratorTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.add(parameterizedTypeDataGenerator);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore + 1, numberOfGeneratorsAfter);
    }

    @Test
    void addNullNonParameterizedTypeDataGeneratorAfterExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage
                .addAfter((NonParameterizedTypeDataGenerator) null, nonParameterizedDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullNonParameterizedTypeDataGeneratorAfterNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage
                .addAfter((NonParameterizedTypeDataGenerator) null, nonParameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullNonParameterizedTypeDataGeneratorAfterNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addAfter((NonParameterizedTypeDataGenerator) null, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullNonParameterizedTypeDataGeneratorBeforeExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        NonParameterizedTypeDataGenerator nonParameterizedDataGenerator = getNonParameterizedTypeDataGenerator(
                dataGenerators);
        dataGeneratorStorage
                .addBefore((NonParameterizedTypeDataGenerator) null, nonParameterizedDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullNonParameterizedTypeDataGeneratorBeforeNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage
                .addBefore((NonParameterizedTypeDataGenerator) null, nonParameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullNonParameterizedTypeDataGeneratorBeforeNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addBefore((NonParameterizedTypeDataGenerator) null, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullNonParameterizedTypeDataGeneratorTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.add((NonParameterizedTypeDataGenerator) null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullParameterizedTypeDataGeneratorAfterExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addAfter((ParameterizedTypeDataGenerator) null, parameterizedGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullParameterizedTypeDataGeneratorAfterNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addAfter((ParameterizedTypeDataGenerator) null, parameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullParameterizedTypeDataGeneratorAfterNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addAfter((ParameterizedTypeDataGenerator) null, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullParameterizedTypeDataGeneratorBeforeExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        List<DataGenerator> dataGenerators = dataGeneratorStorage.getAll();
        int numberOfGeneratorsBefore = dataGenerators.size();
        ParameterizedTypeDataGenerator parameterizedGenerator = getParameterizedTypeDataGenerator(dataGenerators);
        dataGeneratorStorage.addBefore((ParameterizedTypeDataGenerator) null, parameterizedGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullParameterizedTypeDataGeneratorBeforeNonExistentTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage
                .addBefore((ParameterizedTypeDataGenerator) null, parameterizedTypeDataGenerator.getClass());
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullParameterizedTypeDataGeneratorBeforeNullTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.addBefore((ParameterizedTypeDataGenerator) null, null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    @Test
    void addNullParameterizedTypeDataGeneratorTest() {
        TestDataGeneratorStorage dataGeneratorStorage = new TestDataGeneratorStorageImpl(testDataGenerationContext);
        int numberOfGeneratorsBefore = dataGeneratorStorage.getAll().size();
        dataGeneratorStorage.add((ParameterizedTypeDataGenerator) null);
        int numberOfGeneratorsAfter = dataGeneratorStorage.getAll().size();
        Assertions.assertEquals(numberOfGeneratorsBefore, numberOfGeneratorsAfter);
    }

    private int getIndexOfGenerator(List<DataGenerator> generators, Class<?> generatorClass) {
        for (int i = 0; i < generators.size(); ++i) {
            if (generatorClass.equals(generators.get(i).getClass())) {
                return i;
            }
        }
        throw new AssertionError("Failed to find generator at list.");
    }

    private NonParameterizedTypeDataGenerator getNonParameterizedTypeDataGenerator(List<DataGenerator> dataGenerators) {
        for (DataGenerator dataGenerator : dataGenerators) {
            if (NonParameterizedTypeDataGenerator.class.isAssignableFrom(dataGenerator.getClass())) {
                return (NonParameterizedTypeDataGenerator) dataGenerator;
            }
        }
        throw new AssertionError("Failed to find any NonParameterizedTypeDataGenerator.");
    }

    private ParameterizedTypeDataGenerator getParameterizedTypeDataGenerator(List<DataGenerator> dataGenerators) {
        for (DataGenerator dataGenerator : dataGenerators) {
            if (ParameterizedTypeDataGenerator.class.isAssignableFrom(dataGenerator.getClass())) {
                return (ParameterizedTypeDataGenerator) dataGenerator;
            }
        }
        throw new AssertionError("Failed to find any ParameterizedTypeDataGenerator.");
    }
}
