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
package com.github.vladislavsevruk.generator.test.data.mapping;

import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContext;
import com.github.vladislavsevruk.generator.test.data.context.TestDataGenerationContextManager;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import com.github.vladislavsevruk.resolver.util.TypeMetaUtil;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of <code>SetterMapper</code>.
 *
 * @see SetterMapper
 */
@Log4j2
public class SetterMapperImpl implements SetterMapper {

    private static final String SETTER_PREFIX = "set";

    private TestDataGenerationContext context;

    public SetterMapperImpl() {
        this(TestDataGenerationContextManager.getContext());
    }

    public SetterMapperImpl(TestDataGenerationContext context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Method findMatchingSetter(TypeMeta<?> classMeta, Field field) {
        String fieldName = field.getName();
        // delayed resolution to avoid unnecessary action if there is no non-static candidates with matching name
        TypeMeta<?> fieldMeta = null;
        for (Method candidate : classMeta.getType().getMethods()) {
            if (hasMatchingName(candidate, fieldName) && isNotStatic(candidate)) {
                if (Objects.isNull(fieldMeta)) {
                    fieldMeta = context.getFieldTypeResolver().resolveField(classMeta, field);
                }
                if (hasSingleMatchingParameter(classMeta, candidate, fieldMeta)) {
                    return candidate;
                }
            }
        }
        return null;
    }

    private boolean hasMatchingName(Method method, String fieldName) {
        String methodName = method.getName();
        return methodName.equals(fieldName) || removeSetterPrefix(methodName).equals(fieldName);
    }

    private boolean hasSingleMatchingParameter(TypeMeta<?> classMeta, Method candidate, TypeMeta<?> fieldMeta) {
        if (candidate.getParameterTypes().length != 1) {
            return false;
        }
        List<TypeMeta<?>> methodMetas = context.getExecutableTypeResolver().getParameterTypes(classMeta, candidate);
        return TypeMetaUtil.isTypesMatch(fieldMeta, methodMetas.get(0));
    }

    private boolean isNotStatic(Method method) {
        return !Modifier.isStatic(method.getModifiers());
    }

    private String lowercaseFirstLetter(String value) {
        char firstLetter = value.charAt(0);
        if (firstLetter >= 65 && firstLetter <= 97) {
            return (char) (firstLetter + 32) + value.substring(1);
        }
        return value;
    }

    private String removeSetterPrefix(String value) {
        if (!value.startsWith(SETTER_PREFIX)) {
            return value;
        }
        return lowercaseFirstLetter(value.substring(SETTER_PREFIX.length()));
    }
}
