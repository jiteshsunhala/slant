package com.sunhaj.slant.util;

import java.util.function.BiPredicate;

public class AlwaysTrue<X, Y> implements BiPredicate<X, Y> {

    @Override
    public boolean test(X x, Y y) {
        return true;
    }
}
