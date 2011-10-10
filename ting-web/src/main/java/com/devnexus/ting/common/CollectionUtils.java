package com.devnexus.ting.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Contains utility methods, e.g. factory methods for creating list.
 *
 * @author Gunnar Hillert
 * @version $Id: CollectionUtils.java 378 2009-05-14 01:42:49Z ghillert $
 */
public final class CollectionUtils {

    /** Private Constructor.
     *  Supress default constructor for non-instantiability */
    private CollectionUtils() {
        throw new AssertionError();
    }

    /** Return a basic ArrayList */
    public static < T > ArrayList < T > getArrayList() {
        return new ArrayList < T >(0);
    }

    /** Return a basic HashSet */
    public static < T > java.util.HashSet<T> getHashSet() {
        return new HashSet < T >(0);
    }

    /** Return a basic HashMap */
    public static < T, S > java.util.HashMap < T, S > getHashMap() {
        return new HashMap < T,S > (0);
    }
}
