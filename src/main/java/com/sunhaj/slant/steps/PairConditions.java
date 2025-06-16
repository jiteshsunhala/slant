package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;

import java.util.List;
import java.util.function.BiPredicate;

public class PairConditions {

    private int startCornerValue, endCornerValue;
    private List<Integer> skipValues;
    private BiPredicate<Board, Corner> startCornerConditions, endCornerConditions;
    private Corner.Direction pairDirection;
}
