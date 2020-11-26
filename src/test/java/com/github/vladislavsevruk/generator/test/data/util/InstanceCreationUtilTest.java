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
package com.github.vladislavsevruk.generator.test.data.util;

import com.github.vladislavsevruk.generator.test.data.TestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.exception.InstanceCreationException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InstanceCreationUtilTest {

    @Test
    void nonPublicModelTest() {
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        NonPublicModel model = testDataGenerator.generate(NonPublicModel.class);
        Assertions.assertNotNull(model);
        Assertions.assertNull(model.getStringField());
    }

    @Test
    void withoutNoArgsConstructorTest() {
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        Assertions.assertThrows(InstanceCreationException.class,
                () -> testDataGenerator.generate(WithoutNoArgsConstructorModel.class));
    }

    @Test
    void withoutPublicConstructorTest() {
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        Assertions.assertThrows(InstanceCreationException.class,
                () -> testDataGenerator.generate(WithoutPublicConstructorModel.class));
    }

    @Getter
    @Setter
    public static class WithoutNoArgsConstructorModel {

        private String stringField;

        public WithoutNoArgsConstructorModel(String stringField) {
            this.stringField = stringField;
        }
    }

    @Getter
    @Setter
    public static class WithoutPublicConstructorModel {

        private String stringField;

        protected WithoutPublicConstructorModel() {
        }
    }

    @Data
    static class NonPublicModel {

        private String stringField;
    }
}
