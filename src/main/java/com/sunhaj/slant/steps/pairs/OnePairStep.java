package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class OnePairStep extends PairStep {

    @Override
    protected String getStepName() {
        return "one pair step";
    }

    @Override
    protected int getPairValue() {
        return 1;
    }

    @Override
    protected Function<Corner.Cell, CellValue> setFunction() {
        return Corner.Cell::getAway;
    }

    @Override
    protected List<Integer> getSkipList() {
        return List.of(2);
    }
}
