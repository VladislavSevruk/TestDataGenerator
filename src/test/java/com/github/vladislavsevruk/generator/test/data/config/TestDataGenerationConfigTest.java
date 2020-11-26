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
package com.github.vladislavsevruk.generator.test.data.config;

import com.github.vladislavsevruk.generator.test.data.TestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.exception.GenerationConfigurationException;
import com.github.vladislavsevruk.generator.test.data.test.SimpleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestDataGenerationConfigTest {

    private static final int DEFAULT_MAX_ITEMS_NUMBER = 5;
    private static final int DEFAULT_MIN_ITEMS_NUMBER = 1;
    private static final int MAX_ITEMS_NUMBER = 3;
    private static final int MIN_ITEMS_NUMBER = 4;
    private static final String TEST_VALUE = "test";

    @Test
    void defaultConfigTest() {
        SimpleModel model = new TestDataGenerator().generate(SimpleModel.class);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertFalse(model.getStringField().startsWith(TEST_VALUE));
        Assertions.assertFalse(model.getStringField().endsWith(TEST_VALUE));
        Assertions.assertNotNull(model.getStringListField());
        Assertions.assertTrue(model.getStringListField().size() >= DEFAULT_MIN_ITEMS_NUMBER);
        Assertions.assertTrue(model.getStringListField().size() <= DEFAULT_MAX_ITEMS_NUMBER);
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.startsWith(TEST_VALUE)));
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.endsWith(TEST_VALUE)));
    }

    @Test
    void maxItemsForCollectionsLessThanMinItemsForCollectionsTest() {
        TestDataGenerationConfigBuilder builder = TestDataGenerationConfig.builder()
                .maxItemsForCollections(DEFAULT_MIN_ITEMS_NUMBER - 1);
        Assertions.assertThrows(GenerationConfigurationException.class, builder::build);
    }

    @Test
    void maxItemsForCollectionsTest() {
        TestDataGenerationConfig config = TestDataGenerationConfig.builder().maxItemsForCollections(MAX_ITEMS_NUMBER)
                .build();
        SimpleModel model = new TestDataGenerator(config).generate(SimpleModel.class);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertFalse(model.getStringField().startsWith(TEST_VALUE));
        Assertions.assertFalse(model.getStringField().endsWith(TEST_VALUE));
        Assertions.assertNotNull(model.getStringListField());
        Assertions.assertTrue(model.getStringListField().size() >= DEFAULT_MIN_ITEMS_NUMBER);
        Assertions.assertTrue(model.getStringListField().size() <= MAX_ITEMS_NUMBER);
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.startsWith(TEST_VALUE)));
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.endsWith(TEST_VALUE)));
    }

    @Test
    void minItemsForCollectionsAndMaxItemsForCollectionsSameValueTest() {
        TestDataGenerationConfig config = TestDataGenerationConfig.builder().maxItemsForCollections(MAX_ITEMS_NUMBER)
                .minItemsForCollections(MAX_ITEMS_NUMBER).build();
        SimpleModel model = new TestDataGenerator(config).generate(SimpleModel.class);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertFalse(model.getStringField().startsWith(TEST_VALUE));
        Assertions.assertFalse(model.getStringField().endsWith(TEST_VALUE));
        Assertions.assertNotNull(model.getStringListField());
        Assertions.assertEquals(MAX_ITEMS_NUMBER, model.getStringListField().size());
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.startsWith(TEST_VALUE)));
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.endsWith(TEST_VALUE)));
    }

    @Test
    void minItemsForCollectionsGreaterThanMaxItemsForCollectionsTest() {
        TestDataGenerationConfigBuilder builder = TestDataGenerationConfig.builder()
                .minItemsForCollections(DEFAULT_MAX_ITEMS_NUMBER + 1);
        Assertions.assertThrows(GenerationConfigurationException.class, builder::build);
    }

    @Test
    void minItemsForCollectionsLessThanZeroTest() {
        TestDataGenerationConfigBuilder builder = TestDataGenerationConfig.builder().minItemsForCollections(-1);
        Assertions.assertThrows(GenerationConfigurationException.class, builder::build);
    }

    @Test
    void minItemsForCollectionsTest() {
        TestDataGenerationConfig config = TestDataGenerationConfig.builder().minItemsForCollections(MIN_ITEMS_NUMBER)
                .build();
        SimpleModel model = new TestDataGenerator(config).generate(SimpleModel.class);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertFalse(model.getStringField().startsWith(TEST_VALUE));
        Assertions.assertFalse(model.getStringField().endsWith(TEST_VALUE));
        Assertions.assertTrue(model.getStringListField().size() >= MIN_ITEMS_NUMBER);
        Assertions.assertTrue(model.getStringListField().size() <= DEFAULT_MAX_ITEMS_NUMBER);
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.startsWith(TEST_VALUE)));
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.endsWith(TEST_VALUE)));
    }

    @Test
    void nullTestDataPostfixTest() {
        TestDataGenerationConfigBuilder builder = TestDataGenerationConfig.builder().testDataPostfix(null);
        Assertions.assertThrows(GenerationConfigurationException.class, builder::build);
    }

    @Test
    void nullTestDataPrefixTest() {
        TestDataGenerationConfigBuilder builder = TestDataGenerationConfig.builder().testDataPrefix(null);
        Assertions.assertThrows(GenerationConfigurationException.class, builder::build);
    }

    @Test
    void testDataPostfixTest() {
        TestDataGenerationConfig config = TestDataGenerationConfig.builder().testDataPostfix(TEST_VALUE).build();
        SimpleModel model = new TestDataGenerator(config).generate(SimpleModel.class);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertFalse(model.getStringField().startsWith(TEST_VALUE));
        Assertions.assertTrue(model.getStringField().endsWith(TEST_VALUE));
        Assertions.assertNotNull(model.getStringListField());
        Assertions.assertTrue(model.getStringListField().size() >= DEFAULT_MIN_ITEMS_NUMBER);
        Assertions.assertTrue(model.getStringListField().size() <= DEFAULT_MAX_ITEMS_NUMBER);
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.startsWith(TEST_VALUE)));
        model.getStringListField().forEach(item -> Assertions.assertTrue(item.endsWith(TEST_VALUE)));
    }

    @Test
    void testDataPrefixTest() {
        TestDataGenerationConfig config = TestDataGenerationConfig.builder().testDataPrefix(TEST_VALUE).build();
        SimpleModel model = new TestDataGenerator(config).generate(SimpleModel.class);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertTrue(model.getStringField().startsWith(TEST_VALUE));
        Assertions.assertFalse(model.getStringField().endsWith(TEST_VALUE));
        Assertions.assertNotNull(model.getStringListField());
        Assertions.assertTrue(model.getStringListField().size() >= DEFAULT_MIN_ITEMS_NUMBER);
        Assertions.assertTrue(model.getStringListField().size() <= DEFAULT_MAX_ITEMS_NUMBER);
        model.getStringListField().forEach(item -> Assertions.assertTrue(item.startsWith(TEST_VALUE)));
        model.getStringListField().forEach(item -> Assertions.assertFalse(item.endsWith(TEST_VALUE)));
    }
}
