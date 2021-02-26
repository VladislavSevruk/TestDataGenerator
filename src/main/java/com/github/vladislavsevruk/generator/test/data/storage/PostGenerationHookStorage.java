/*
 * MIT License
 *
 * Copyright (c) 2021 Uladzislau Seuruk
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

import com.github.vladislavsevruk.generator.test.data.hook.PostGenerationHook;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import com.github.vladislavsevruk.resolver.type.TypeProvider;

import java.util.List;

/**
 * Contains post generation hooks for updating values after all model values are set.
 *
 * @see PostGenerationHook
 */
public interface PostGenerationHookStorage {

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type. Post generation hook
     * will not be added provided it is <code>null</code> or storage already contains post generation hook of such type
     * associated with received type or its superclass.
     *
     * @param target             <code>Class</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param <T>                type of model to add hooks for.
     */
    <T> void add(Class<T> target, PostGenerationHook<? super T> postGenerationHook);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type. Post generation hook
     * will not be added provided it is <code>null</code> or storage already contains post generation hook of such type
     * associated with received type or its superclass.
     *
     * @param target             <code>TypeMeta</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     */
    <T> void add(TypeMeta<T> target, PostGenerationHook<? super T> postGenerationHook);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type. Post generation hook
     * will not be added provided it is <code>null</code> or storage already contains post generation hook of such type
     * associated with received type or its superclass.
     *
     * @param target             <code>TypeProvider</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param <T>                type of model to add hooks for.
     */
    <T> void add(TypeProvider<T> target, PostGenerationHook<? super T> postGenerationHook);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type after specified
     * <code>PostGenerationHook</code> or at list end provided target <code>PostGenerationHook</code> is missed. New
     * post generation hook will not be added provided it is <code>null</code> or storage already contains post
     * generation hook of such type associated with received type or its superclass.
     *
     * @param target             <code>Class</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param targetHookType     <code>Class</code> of hook after which new post generation hook should be added.
     * @param <T>                type of model to add hooks for.
     */
    <T> void addAfter(Class<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type after specified
     * <code>PostGenerationHook</code> or at list end provided target <code>PostGenerationHook</code> is missed. New
     * post generation hook will not be added provided it is <code>null</code> or storage already contains post
     * generation hook of such type associated with received type or its superclass.
     *
     * @param target             <code>TypeMeta</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param targetHookType     <code>Class</code> of hook after which new post generation hook should be added.
     * @param <T>                type of model to add hooks for.
     */
    <T> void addAfter(TypeMeta<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type after specified
     * <code>PostGenerationHook</code> or at list end provided target <code>PostGenerationHook</code> is missed. New
     * post generation hook will not be added provided it is <code>null</code> or storage already contains post
     * generation hook of such type associated with received type or its superclass.
     *
     * @param target             <code>TypeProvider</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param targetHookType     <code>Class</code> of hook after which new post generation hook should be added.
     * @param <T>                type of model to add hooks for.
     */
    <T> void addAfter(TypeProvider<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type before specified
     * <code>PostGenerationHook</code> or at list end provided target <code>PostGenerationHook</code> is missed. New
     * post generation hook will not be added provided it is <code>null</code> or storage already contains post
     * generation hook of such type associated with received type or its superclass.
     *
     * @param target             <code>Class</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param targetHookType     <code>Class</code> of hook before which new post generation hook should be added.
     * @param <T>                type of model to add hooks for.
     */
    <T> void addBefore(Class<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type before specified
     * <code>PostGenerationHook</code> or at list end provided target <code>PostGenerationHook</code> is missed. New
     * post generation hook will not be added provided it is <code>null</code> or storage already contains post
     * generation hook of such type associated with received type or its superclass.
     *
     * @param target             <code>Class</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param targetHookType     <code>Class</code> of hook before which new post generation hook should be added.
     * @param <T>                type of model to add hooks for.
     */
    <T> void addBefore(TypeMeta<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType);

    /**
     * Adds new <code>PostGenerationHook</code> to storage and associates it with received type before specified
     * <code>PostGenerationHook</code> or at list end provided target <code>PostGenerationHook</code> is missed. New
     * post generation hook will not be added provided it is <code>null</code> or storage already contains post
     * generation hook of such type associated with received type or its superclass.
     *
     * @param target             <code>TypeProvider</code> with type to add hook for.
     * @param postGenerationHook <code>PostGenerationHook</code> to add.
     * @param targetHookType     <code>Class</code> of hook before which new post generation hook should be added.
     * @param <T>                type of model to add hooks for.
     */
    <T> void addBefore(TypeProvider<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType);

    /**
     * Returns list of all hooks for received type that are present at storage. Returns hooks for received type and all
     * its superclasses as well.
     *
     * @param target <code>TypeMeta</code> with type to get hooks for.
     * @param <T>    type of class to get hooks for.
     * @return <code>List</code> of <code>PostGenerationHook</code> for received type.
     */
    <T> List<PostGenerationHook<? super T>> getAll(TypeMeta<T> target);
}
