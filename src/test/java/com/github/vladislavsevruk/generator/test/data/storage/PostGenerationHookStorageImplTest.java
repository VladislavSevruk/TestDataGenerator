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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Serializable;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PostGenerationHookStorageImplTest {

    private static final TypeMeta<Integer> DESCENDANT_TEST_TYPE_META = new TypeMeta<>(Integer.class);
    private static final TypeMeta<Number> TEST_TYPE_META = new TypeMeta<>(Number.class);
    private static final TypeProvider<Number> TEST_TYPE_PROVIDER = new TypeProvider<Number>() {};

    private PostGenerationHook<Serializable> interfacePostGenerationHook = model -> {};
    private PostGenerationHook<Number> postGenerationHook1 = model -> {};
    private PostGenerationHook<Number> postGenerationHook2 = model -> {};

    @Test
    void addExistentAtParentPostGenerationHookTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        postGenerationHookStorage.add(Integer.class, postGenerationHook1);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(DESCENDANT_TEST_TYPE_META).size();
        Assertions.assertEquals(1, numberOfHooksAfter);
    }

    @Test
    void addExistentPostGenerationHookAfterExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        postGenerationHookStorage.add(Number.class, postGenerationHook2);
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addAfter(Number.class, postGenerationHook1,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook2.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addExistentPostGenerationHookAfterNonExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addAfter(Number.class, postGenerationHook1,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook2.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addExistentPostGenerationHookAfterNullTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addAfter(Number.class, postGenerationHook1, null);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addExistentPostGenerationHookBeforeExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        postGenerationHookStorage.add(Number.class, postGenerationHook2);
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addBefore(Number.class, postGenerationHook1,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook2.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addExistentPostGenerationHookBeforeNonExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addBefore(Number.class, postGenerationHook1,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook2.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addExistentPostGenerationHookBeforeNullTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addBefore(Number.class, postGenerationHook1, null);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addExistentPostGenerationHookTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addNewPostGenerationHookAfterExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        List<PostGenerationHook<? super Number>> postGenerationHooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksBefore = postGenerationHooks.size();
        int indexBefore = getIndexOfHook(postGenerationHooks, postGenerationHook1.getClass());
        postGenerationHookStorage.addAfter(TEST_TYPE_META, postGenerationHook2,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook1.getClass());
        postGenerationHooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksAfter = postGenerationHooks.size();
        Assertions.assertEquals(numberOfHooksBefore + 1, numberOfHooksAfter);
        Assertions.assertEquals(indexBefore, getIndexOfHook(postGenerationHooks, postGenerationHook1.getClass()));
        Assertions.assertEquals(indexBefore + 1, getIndexOfHook(postGenerationHooks, postGenerationHook2.getClass()));
    }

    @Test
    void addNewPostGenerationHookAfterNonExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addAfter(TEST_TYPE_PROVIDER, postGenerationHook1,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook2.getClass());
        List<PostGenerationHook<? super Number>> hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksAfter = hooks.size();
        Assertions.assertEquals(numberOfHooksBefore + 1, numberOfHooksAfter);
        Assertions.assertEquals(numberOfHooksAfter - 1, getIndexOfHook(hooks, postGenerationHook1.getClass()));
    }

    @Test
    void addNewPostGenerationHookAfterNullTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addAfter(Number.class, postGenerationHook1, null);
        List<PostGenerationHook<? super Number>> hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksAfter = hooks.size();
        Assertions.assertEquals(numberOfHooksBefore + 1, numberOfHooksAfter);
        Assertions.assertEquals(numberOfHooksAfter - 1, getIndexOfHook(hooks, postGenerationHook1.getClass()));
    }

    @Test
    void addNewPostGenerationHookBeforeExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        List<PostGenerationHook<? super Number>> hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksBefore = hooks.size();
        int indexBefore = getIndexOfHook(hooks, postGenerationHook1.getClass());
        postGenerationHookStorage.addBefore(TEST_TYPE_META, postGenerationHook2,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook1.getClass());
        hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksAfter = hooks.size();
        Assertions.assertEquals(numberOfHooksBefore + 1, numberOfHooksAfter);
        Assertions.assertEquals(indexBefore + 1, getIndexOfHook(hooks, postGenerationHook1.getClass()));
        Assertions.assertEquals(indexBefore, getIndexOfHook(hooks, postGenerationHook2.getClass()));
    }

    @Test
    void addNewPostGenerationHookBeforeNonExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addBefore(TEST_TYPE_PROVIDER, postGenerationHook1,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook2.getClass());
        List<PostGenerationHook<? super Number>> hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksAfter = hooks.size();
        Assertions.assertEquals(numberOfHooksBefore + 1, numberOfHooksAfter);
        Assertions.assertEquals(numberOfHooksAfter - 1, getIndexOfHook(hooks, postGenerationHook1.getClass()));
    }

    @Test
    void addNewPostGenerationHookBeforeNullTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addBefore(Number.class, postGenerationHook1, null);
        List<PostGenerationHook<? super Number>> hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksAfter = hooks.size();
        Assertions.assertEquals(numberOfHooksBefore + 1, numberOfHooksAfter);
        Assertions.assertEquals(numberOfHooksAfter - 1, getIndexOfHook(hooks, postGenerationHook1.getClass()));
    }

    @Test
    void addNewPostGenerationHookTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.add(TEST_TYPE_META, postGenerationHook1);
        postGenerationHookStorage.add(TEST_TYPE_PROVIDER, postGenerationHook2);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore + 2, numberOfHooksAfter);
    }

    @Test
    void addNullPostGenerationHookAfterExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        List<PostGenerationHook<? super Number>> hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksBefore = hooks.size();
        postGenerationHookStorage.addAfter(Number.class, null,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook1.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addNullPostGenerationHookAfterNonExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addAfter(Number.class, null,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook1.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addNullPostGenerationHookAfterNullTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addAfter(Number.class, null, null);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addNullPostGenerationHookBeforeExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        List<PostGenerationHook<? super Number>> hooks = postGenerationHookStorage.getAll(TEST_TYPE_META);
        int numberOfHooksBefore = hooks.size();
        postGenerationHookStorage.addBefore(Number.class, null,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook1.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addNullPostGenerationHookBeforeNonExistentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addBefore(Number.class, null,
                (Class<? extends PostGenerationHook<Number>>) postGenerationHook1.getClass());
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addNullPostGenerationHookBeforeNullTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.addBefore(Number.class, null, null);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void addNullPostGenerationHookTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        int numberOfHooksBefore = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        postGenerationHookStorage.add(Number.class, null);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(numberOfHooksBefore, numberOfHooksAfter);
    }

    @Test
    void postGenerationHookAtDescendantNotReceivedForParentTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Integer.class, postGenerationHook1);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(TEST_TYPE_META).size();
        Assertions.assertEquals(0, numberOfHooksAfter);
    }

    @Test
    void postGenerationHookAtParentReceivedForDescendantTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        int numberOfHooksAfter = postGenerationHookStorage.getAll(DESCENDANT_TEST_TYPE_META).size();
        Assertions.assertEquals(1, numberOfHooksAfter);
    }

    @Test
    void postGenerationHooksSortedFromParentToDescendantTest() {
        PostGenerationHookStorage postGenerationHookStorage = new PostGenerationHookStorageImpl();
        postGenerationHookStorage.add(Number.class, postGenerationHook1);
        postGenerationHookStorage.add(Integer.class, postGenerationHook2);
        postGenerationHookStorage.add(Serializable.class, interfacePostGenerationHook);
        List<PostGenerationHook<? super Integer>> hooks = postGenerationHookStorage.getAll(DESCENDANT_TEST_TYPE_META);
        Assertions.assertEquals(3, hooks.size());
        Assertions.assertEquals(interfacePostGenerationHook, hooks.get(0));
        Assertions.assertEquals(postGenerationHook1, hooks.get(1));
        Assertions.assertEquals(postGenerationHook2, hooks.get(2));
    }

    private int getIndexOfHook(List<? extends PostGenerationHook<?>> hooks, Class<?> generatorClass) {
        for (int i = 0; i < hooks.size(); ++i) {
            if (generatorClass.equals(hooks.get(i).getClass())) {
                return i;
            }
        }
        throw new AssertionError("Failed to find post generation hook at list.");
    }
}
