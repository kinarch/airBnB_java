package com.richard.airbnb.tools;

import com.richard.airbnb.models.MyComparable;

public class MyComparator<T extends MyComparable> {

    private T a;
    private T b;

    public MyComparator(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public T getHigher() {
        return a.getValueToCompare() > b.getValueToCompare() ? a : b;
    }

    public T getLower() {
        return a.getValueToCompare() < b.getValueToCompare() ? a : b;
    }
}
