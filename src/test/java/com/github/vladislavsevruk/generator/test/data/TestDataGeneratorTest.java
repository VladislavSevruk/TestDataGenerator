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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class TestDataGeneratorTest {

    @Test
    void generateDescendantModelTest() {
        DescendantModel model = new TestDataGenerator().generate(DescendantModel.class);
        Assertions.assertNotNull(model);
        Assertions.assertNotNull(model.getStringField());
        assertCollection(model.getParameterizedField());
    }

    @Test
    void generateListItemsTest() {
        List<SimpleModel> list = new TestDataGenerator().generate(new TypeProvider<List<SimpleModel>>() {});
        assertCollection(list);
        list.forEach(model -> {
            Assertions.assertNotNull(model.getStringField());
            assertCollection(model.getStringListField(), "stringListField");
        });
    }

    @Test
    void generateParameterizedModelTest() {
        ParameterizedModel<List<String>> model = new TestDataGenerator()
                .generate(new TypeProvider<ParameterizedModel<List<String>>>() {});
        Assertions.assertNotNull(model);
        Assertions.assertNotNull(model.getStringField());
        assertCollection(model.getParameterizedField(), "parameterizedField");
    }

    @Test
    void generatePojoModelTest() {
        SupportedTypesModel model = new TestDataGenerator().generate(SupportedTypesModel.class);
        Assertions.assertNotNull(model);
        Assertions.assertNotNull(model.getEnumField());
        assertMap(model.getMapField(), "mapField");
        Assertions.assertNotNull(model.getSimpleModelField());
        Assertions.assertNotNull(model.getSimpleModelField().getStringField());
        assertCollection(model.getSimpleModelField().getStringListField(), "stringListField");
        assertArray(model.getArrayField(), "arrayField");
        assertCollection(model.getListField(), "listField");
        assertCollection(model.getSetField(), "setField");
        Assertions.assertNotNull(model.getBooleanField());
        Assertions.assertNotNull(model.getByteField());
        Assertions.assertNotNull(model.getCharacterField());
        Assertions.assertNotNull(model.getDoubleField());
        Assertions.assertNotNull(model.getFloatField());
        Assertions.assertNotNull(model.getIntegerField());
        Assertions.assertNotNull(model.getLongField());
        Assertions.assertNotNull(model.getShortField());
        Assertions.assertNotNull(model.getStringField());
        Assertions.assertTrue(model.getStringField().startsWith("stringField"));
    }

    @Test
    void generateSpecificCollectionPojoModelTest() {
        SpecificCollectionsModel model = new TestDataGenerator().generate(SpecificCollectionsModel.class);
        Assertions.assertNotNull(model);
        assertMap(model.getAbstractMapField(), "abstractMapField");
        assertMap(model.getLinkedHashMapField(), "linkedHashMapField");
        assertCollection(model.getAbstractListField(), "abstractListField");
        assertCollection(model.getLinkedListField(), "linkedListField");
        assertCollection(model.getAbstractSetField(), "abstractSetField");
        assertCollection(model.getLinkedHashSetField(), "linkedHashSetField");
    }

    private void assertArray(String[] array, String prefix) {
        Assertions.assertNotNull(array);
        Assertions.assertNotEquals(0, array.length);
        Assertions.assertTrue(Arrays.stream(array).allMatch(Objects::nonNull));
        Assertions.assertTrue(Arrays.stream(array).allMatch(value -> value.startsWith(prefix)));
    }

    private void assertCollection(Collection<String> collection, String prefix) {
        assertCollection(collection);
        Assertions.assertTrue(collection.stream().allMatch(value -> value.startsWith(prefix)));
    }

    private void assertCollection(Collection<?> collection) {
        Assertions.assertNotNull(collection);
        Assertions.assertNotEquals(0, collection.size());
        Assertions.assertTrue(collection.stream().allMatch(Objects::nonNull));
    }

    private void assertMap(Map<String, String> map, String prefix) {
        Assertions.assertNotNull(map);
        Assertions.assertNotEquals(0, map.size());
        Assertions.assertTrue(map.keySet().stream().allMatch(Objects::nonNull));
        Assertions.assertTrue(map.keySet().stream().allMatch(key -> key.startsWith(prefix + "-Key")));
        Assertions.assertTrue(map.values().stream().allMatch(Objects::nonNull));
        Assertions.assertTrue(map.values().stream().allMatch(value -> value.startsWith(prefix + "-Value")));
    }
}
