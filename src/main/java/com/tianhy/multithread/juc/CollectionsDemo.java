package com.tianhy.multithread.juc;

import java.util.*;
import java.util.concurrent.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/12
 **/
public class CollectionsDemo {

    public static void main(String[] args) {

        Collection<Object> collection = Collections.synchronizedCollection(new ArrayList<>());
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        SortedSet<Object> sortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        SortedMap<Object, Object> sortedMap = Collections.synchronizedSortedMap(new TreeMap<>());


    }
}
