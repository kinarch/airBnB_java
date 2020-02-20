package com.richard.airbnb.tools;

import com.richard.airbnb.models.MyComparable;

import java.util.List;

public class MyListComparator<T extends MyComparable> {

    private final List<T> list;

    public MyListComparator(List<T> list) {
        this.list = list;
    }

    public void add(T item) {
        list.add(item);
    }

    public void remove(int i) throws IndexOutOfBoundsException {
        list.remove(i);
    }

    public T getHigher() {
        if (!list.isEmpty()) {
            int temp = list.size() - 1;
            for (int i = temp; i > 0; i--) {
                final int prev = i - 1;
                if (list.get(i).getValueToCompare() > list.get(prev).getValueToCompare()) {
                    temp = i;
                }
            }
            return list.get(temp);
        }
        return null;
    }

    public T getLower() {
        if (!list.isEmpty()) {
            int temp = list.size() - 1;
            for (int i = temp; i > 0; i--) {
                final int prev = i - 1;
                if (list.get(i).getValueToCompare() < list.get(prev).getValueToCompare()) {
                    temp = i;
                }
            }
            return list.get(temp);
        }
        return null;
    }
}
