package com.markzhai.library.utils;

import java.util.Collection;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class CollectionUtils {
    public static <E> boolean isEmpty(Collection<E> c) {
        return c == null || c.size() == 0;

    }

    public static <E> boolean notEmpty(Collection<E> c) {
        return c != null && c.size() != 0;

    }

    public static <E> int size(Collection<E> c) {
        return c == null ? 0 : c.size();
    }
}
