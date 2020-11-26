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
package com.github.vladislavsevruk.generator.test.data;

import com.github.vladislavsevruk.generator.test.data.test.DescendantModel;
import com.github.vladislavsevruk.generator.test.data.test.ParameterizedModel;
import com.github.vladislavsevruk.generator.test.data.test.SimpleModel;
import com.github.vladislavsevruk.generator.test.data.test.SpecificCollectionsModel;
import com.github.vladislavsevruk.generator.test.data.test.SupportedTypesModel;
import com.github.vladislavsevruk.resolver.type.TypeProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class TestDataGeneratorTest {

    @Test
    void generateDescendantModelTest() {
        DescendantModel model = new TestDataGenerator().generate(DescendantModel.class);
        Assertions.assertNotNull(model);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertNotNull(model.getParameterizedField());
        Assertions.assertNotEquals(0, model.getParameterizedField().size());
        Assertions.assertTrue(model.getParameterizedField().stream().allMatch(Objects::nonNull));
    }

    @Test
    void generateListItemsTest() {
        List<SimpleModel> list = new TestDataGenerator().generate(new TypeProvider<List<SimpleModel>>() {});
        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());
        Assertions.assertTrue(list.stream().allMatch(Objects::nonNull));
        list.forEach(model -> {
            Assertions.assertNotNull(model.getStringField());
            Assertions.assertNotNull(model.getStringListField());
            Assertions.assertNotEquals(0, model.getStringListField().size());
            Assertions.assertTrue(model.getStringListField().stream().allMatch(Objects::nonNull));
        });
    }

    @Test
    void generateParameterizedModelTest() {
        ParameterizedModel<List<String>> model = new TestDataGenerator()
                .generate(new TypeProvider<ParameterizedModel<List<String>>>() {});
        Assertions.assertNotNull(model);
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertNotNull(model.getParameterizedField());
        Assertions.assertNotEquals(0, model.getParameterizedField().size());
        Assertions.assertTrue(model.getParameterizedField().stream().allMatch(Objects::nonNull));
    }

    @Test
    void generatePojoModelTest() {
        SupportedTypesModel model = new TestDataGenerator().generate(SupportedTypesModel.class);
        Assertions.assertNotNull(model);
        Assertions.assertNotNull(model.getEnumField());
        Assertions.assertNotNull(model.getMapField());
        Assertions.assertNotEquals(0, model.getMapField().size());
        Assertions.assertTrue(model.getMapField().keySet().stream().allMatch(Objects::nonNull));
        Assertions.assertTrue(model.getMapField().values().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getSimpleModelField());
        Assertions.assertNotNull(model.getSimpleModelField().getStringField());
        Assertions.assertNotNull(model.getSimpleModelField().getStringListField());
        Assertions.assertNotEquals(0, model.getSimpleModelField().getStringListField().size());
        Assertions.assertTrue(model.getSimpleModelField().getStringListField().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getArrayField());
        Assertions.assertNotEquals(0, model.getArrayField().length);
        Assertions.assertTrue(Arrays.stream(model.getArrayField()).allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getListField());
        Assertions.assertNotEquals(0, model.getListField().size());
        Assertions.assertTrue(model.getListField().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getSetField());
        Assertions.assertNotEquals(0, model.getSetField().size());
        Assertions.assertTrue(model.getSetField().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getBooleanField());
        Assertions.assertNotNull(model.getByteField());
        Assertions.assertNotNull(model.getCharacterField());
        Assertions.assertNotNull(model.getDoubleField());
        Assertions.assertNotNull(model.getFloatField());
        Assertions.assertNotNull(model.getIntegerField());
        Assertions.assertNotNull(model.getLongField());
        Assertions.assertNotNull(model.getShortField());
        Assertions.assertNotNull(model.getStringField());
    }

    @Test
    void generateSpecificCollectionPojoModelTest() {
        SpecificCollectionsModel model = new TestDataGenerator().generate(SpecificCollectionsModel.class);
        Assertions.assertNotNull(model);
        Assertions.assertNotNull(model.getAbstractMapField());
        Assertions.assertNotEquals(0, model.getAbstractMapField().size());
        Assertions.assertTrue(model.getAbstractMapField().keySet().stream().allMatch(Objects::nonNull));
        Assertions.assertTrue(model.getAbstractMapField().values().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getLinkedHashMapField());
        Assertions.assertNotEquals(0, model.getLinkedHashMapField().size());
        Assertions.assertTrue(model.getLinkedHashMapField().keySet().stream().allMatch(Objects::nonNull));
        Assertions.assertTrue(model.getLinkedHashMapField().values().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getAbstractListField());
        Assertions.assertNotEquals(0, model.getAbstractListField().size());
        Assertions.assertTrue(model.getAbstractListField().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getLinkedListField());
        Assertions.assertNotEquals(0, model.getLinkedListField().size());
        Assertions.assertTrue(model.getLinkedListField().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getAbstractSetField());
        Assertions.assertNotEquals(0, model.getAbstractSetField().size());
        Assertions.assertTrue(model.getAbstractSetField().stream().allMatch(Objects::nonNull));
        Assertions.assertNotNull(model.getLinkedHashSetField());
        Assertions.assertNotEquals(0, model.getLinkedHashSetField().size());
        Assertions.assertTrue(model.getLinkedHashSetField().stream().allMatch(Objects::nonNull));
    }
}
