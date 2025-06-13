package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class ThreePairStep extends PairStep {
    @Override
    protected int getPairValue() {
        return 3;
    }

    @Override
    protected Function<Corner.Cell, CellValue> setFunction() {
        return Corner.Cell::getIncoming;
    }

    @Override
    protected List<Integer> getSkipList() {
        return List.of(2);
    }
}
