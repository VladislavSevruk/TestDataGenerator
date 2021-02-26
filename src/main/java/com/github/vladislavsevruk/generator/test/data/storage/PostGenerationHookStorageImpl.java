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
import com.github.vladislavsevruk.generator.test.data.util.ClassUtil;
import com.github.vladislavsevruk.resolver.type.TypeMeta;
import com.github.vladislavsevruk.resolver.type.TypeProvider;
import com.github.vladislavsevruk.resolver.util.TypeMetaUtil;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Implementation of <code>PostGenerationHookStorage</code>.
 *
 * @see PostGenerationHook
 * @see PostGenerationHookStorage
 */
@Log4j2
public final class PostGenerationHookStorageImpl implements PostGenerationHookStorage {

    private List<PostGenerationHookNode<?>> hookNodes = new ArrayList<>();
    private final ReadWriteLock hooksLock = new ReentrantReadWriteLock();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void add(Class<T> target, PostGenerationHook<? super T> postGenerationHook) {
        add(new TypeMeta<>(target), postGenerationHook);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void add(TypeMeta<T> target, PostGenerationHook<? super T> postGenerationHook) {
        hooksLock.writeLock().lock();
        List<PostGenerationHook<? super T>> hooks = getOrCreateNode(target).hooks;
        add(target, hooks.size(), postGenerationHook);
        hooksLock.writeLock().unlock();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void add(TypeProvider<T> target, PostGenerationHook<? super T> postGenerationHook) {
        add((TypeMeta<T>) target.getTypeMeta(), postGenerationHook);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void addAfter(Class<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType) {
        addAfter(new TypeMeta<>(target), postGenerationHook, targetHookType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void addAfter(TypeMeta<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType) {
        hooksLock.writeLock().lock();
        List<PostGenerationHook<? super T>> hooks = getOrCreateNode(target).hooks;
        int targetTypeIndex = ClassUtil.getIndexOfType(hooks, targetHookType);
        if (targetTypeIndex == -1) {
            log.info(
                    "Target post generation hook type is not present at list, post generation hook will be added to list end.");
            add(target, hooks.size(), postGenerationHook);
        } else {
            add(target, targetTypeIndex + 1, postGenerationHook);
        }
        hooksLock.writeLock().unlock();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void addAfter(TypeProvider<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType) {
        addAfter((TypeMeta<T>) target.getTypeMeta(), postGenerationHook, targetHookType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void addBefore(Class<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType) {
        addBefore(new TypeMeta<>(target), postGenerationHook, targetHookType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void addBefore(TypeMeta<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType) {
        hooksLock.writeLock().lock();
        List<PostGenerationHook<? super T>> hooks = getOrCreateNode(target).hooks;
        int targetTypeIndex = ClassUtil.getIndexOfType(hooks, targetHookType);
        if (targetTypeIndex == -1) {
            log.info(
                    "Target post generation hook type is not present at list, post generation hook will be added to list end.");
            add(target, hooks.size(), postGenerationHook);
        } else {
            add(target, targetTypeIndex, postGenerationHook);
        }
        hooksLock.writeLock().unlock();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void addBefore(TypeProvider<T> target, PostGenerationHook<? super T> postGenerationHook,
            Class<? extends PostGenerationHook<? super T>> targetHookType) {
        addBefore((TypeMeta<T>) target.getTypeMeta(), postGenerationHook, targetHookType);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<PostGenerationHook<? super T>> getAll(TypeMeta<T> typeMeta) {
        List<PostGenerationHook<? super T>> hooks = new ArrayList<>();
        hooksLock.readLock().lock();
        for (PostGenerationHookNode<?> hookNode : hookNodes) {
            if (TypeMetaUtil.isTypesMatch(hookNode.typeMeta, typeMeta)) {
                hooks.addAll(((PostGenerationHookNode<? super T>) hookNode).hooks);
            }
        }
        hooksLock.readLock().unlock();
        return hooks;
    }

    private <T> void add(TypeMeta<T> typeMeta, int index, PostGenerationHook<? super T> hook) {
        if (hook == null) {
            log.info("Received post generation hook is null so it will not be added.");
            return;
        }
        if (isAlreadyPresent(typeMeta, hook)) {
            log.info("Received post generation hook is already present at list so it's copy will not be added.");
            return;
        }
        getOrCreateNode(typeMeta).hooks.add(index, hook);
    }

    private <T> PostGenerationHookNode<T> addNewNode(TypeMeta<T> typeMeta) {
        PostGenerationHookNode<T> newNode = new PostGenerationHookNode<>(typeMeta);
        int index = hookNodes.size();
        for (int i = hookNodes.size() - 1; i >= 0; --i) {
            PostGenerationHookNode<?> hookNode = hookNodes.get(i);
            if (TypeMetaUtil.isTypesMatch(hookNode.typeMeta, typeMeta)) {
                // find first assignable is enough as items sorted in the way that superclasses have lower indices than subclasses
                index = i + 1;
                break;
            }
            if (TypeMetaUtil.isTypesMatch(typeMeta, hookNode.typeMeta)) {
                // use lowest index of found subclasses as superclasses should have lower indices than subclasses
                index = i;
            }
        }
        hookNodes.add(index, newNode);
        return newNode;
    }

    @SuppressWarnings("unchecked")
    private <T> PostGenerationHookNode<T> getNode(TypeMeta<T> typeMeta) {
        for (PostGenerationHookNode<?> hookNode : hookNodes) {
            if (TypeMetaUtil.isSameTypes(hookNode.typeMeta, typeMeta)) {
                return (PostGenerationHookNode<T>) hookNode;
            }
        }
        return null;
    }

    private <T> PostGenerationHookNode<T> getOrCreateNode(TypeMeta<T> typeMeta) {
        return Optional.ofNullable(getNode(typeMeta)).orElseGet(() -> addNewNode(typeMeta));
    }

    @SuppressWarnings("unchecked")
    private <T> boolean isAlreadyPresent(TypeMeta<?> typeMeta, PostGenerationHook<? super T> hook) {
        Class<PostGenerationHook<? super T>> hookClass = (Class<PostGenerationHook<? super T>>) hook.getClass();
        for (PostGenerationHookNode<?> hookNode : hookNodes) {
            if (TypeMetaUtil.isTypesMatch(hookNode.typeMeta, typeMeta)) {
                PostGenerationHookNode<T> node = (PostGenerationHookNode<T>) hookNode;
                if (ClassUtil.getIndexOfType(node.hooks, hookClass) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class PostGenerationHookNode<T> {

        private final List<PostGenerationHook<? super T>> hooks = new ArrayList<>();
        private final TypeMeta<T> typeMeta;

        public PostGenerationHookNode(TypeMeta<T> typeMeta) {
            this.typeMeta = typeMeta;
        }
    }
}
