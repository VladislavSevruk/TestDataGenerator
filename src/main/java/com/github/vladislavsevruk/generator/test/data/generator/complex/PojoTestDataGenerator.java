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
package com.github.vladislavsevruk.generator.test.data.generator.complex;

import com.github.vladislavsevruk.generator.test.data.config.TestDataGenerationConfig;
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.generator.AbstractParameterizedTestDataGenerator;
import com.github.vladislavsevruk.generator.test.data.generator.NonParameterizedTypeDataGenerator;
import com.github.vladislavsevruk.generator.test.data.util.InstanceCreationUtil;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Implements <code>TypeTestDataGenerator</code> for POJOs.
 *
 * @see NonParameterizedTypeDataGenerator
 */
@Log4j2
public class PojoTestDataGenerator extends AbstractParameterizedTestDataGenerator<Object> {

    private final TestDataGenerationContext context;

    public PojoTestDataGenerator(TestDataGenerationContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canGenerate(Class<?> type) {
        return isCustomType(type);
    }

    @Override
    protected Object doGenerate(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<?> typeMeta) {
        Object object = InstanceCreationUtil.createItem(typeMeta.getType());
        setValues(testDataGenerationConfig, typeMeta, typeMeta.getType(), object);
        return object;
    }

    @Override
    protected Class<Object> getTargetType() {
        return Object.class;
    }

    private NonParameterizedTypeDataGenerator<?> getMatchingGenerator(TypeMeta<?> typeMeta, Field field) {
        if (context.getCustomFieldMappingStorage().hasMapping(field)) {
            return context.getCustomFieldMappingStorage().getMapping(field);
        }
        TypeMeta<?> fieldMeta = context.getFieldTypeResolver().resolveField(typeMeta, field);
        return context.getTestDataGeneratorPicker().pickGenerator(fieldMeta);
    }

    private boolean hasCustomSuperclass(Class<?> clazz) {
        return !Object.class.equals(clazz.getSuperclass());
    }

    private boolean isCustomType(Class<?> type) {
        return !type.isPrimitive() && !type.isArray() && !type.isEnum() && !Iterable.class.isAssignableFrom(type)
                && !Map.class.isAssignableFrom(type) && !Number.class.isAssignableFrom(type) && !CharSequence.class
                .isAssignableFrom(type) && !Boolean.class.isAssignableFrom(type) && !Character.class
                .isAssignableFrom(type) && !TemporalAccessor.class.isAssignableFrom(type) && !Date.class
                .isAssignableFrom(type) && !Calendar.class.isAssignableFrom(type) && !TimeZone.class
                .isAssignableFrom(type) && !Dictionary.class.isAssignableFrom(type);
    }

    private boolean isNotFinal(Field field) {
        return !Modifier.isFinal(field.getModifiers());
    }

    private boolean isNotStatic(Field field) {
        return !Modifier.isStatic(field.getModifiers());
    }

    private void setValue(Method matchingSetter, Object object, Object value) {
        try {
            matchingSetter.invoke(object, value);
        } catch (ReflectiveOperationException roEx) {
            log.error("Failed to set value via '{}' method.", matchingSetter.getName());
        }
    }

    private void setValue(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<?> typeMeta, Object object,
            Field field) {
        Method matchingSetter = context.getSetterMapper().findMatchingSetter(typeMeta, field);
        if (Objects.nonNull(matchingSetter)) {
            NonParameterizedTypeDataGenerator<?> matchingGenerator = getMatchingGenerator(typeMeta, field);
            if (Objects.nonNull(matchingGenerator)) {
                Object value = matchingGenerator.generate(testDataGenerationConfig);
                setValue(matchingSetter, object, value);
            }
        }
    }

    private void setValues(TestDataGenerationConfig testDataGenerationConfig, TypeMeta<?> typeMeta, Class<?> clazz,
            Object object) {
        for (Field field : clazz.getDeclaredFields()) {
            if (isNotStatic(field) && isNotFinal(field)) {
                setValue(testDataGenerationConfig, typeMeta, object, field);
            }
        }
        if (hasCustomSuperclass(clazz)) {
            setValues(testDataGenerationConfig, typeMeta, clazz.getSuperclass(), object);
        }
    }
}
