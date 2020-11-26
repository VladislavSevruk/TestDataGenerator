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

import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Contains utility methods for getting superclasses and implemented interfaces.
 */
@Log4j2
public final class ClassUtil {

    private ClassUtil() {
    }

    /**
     * Gets index of element that has received type from received list.
     *
     * @param elements   <code>List</code> with target type elements.
     * @param targetType <code>Class</code> with type to get index of.
     * @param <T>        type of list elements.
     * @return index of element that has received <code>Class</code> from received <code>List</code> or <code>-1</code>
     * if there is no element with such type.
     */
    public static <T> int getIndexOfType(List<T> elements, Class<? extends T> targetType) {
        int targetTypeIndex = -1;
        if (targetType == null) {
            log.info("Target type is null.");
            return -1;
        }
        for (int i = 0; i < elements.size(); ++i) {
            if (targetType.equals(elements.get(i).getClass())) {
                targetTypeIndex = i;
                break;
            }
        }
        return targetTypeIndex;
    }
}
