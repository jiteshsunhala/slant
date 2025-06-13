package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OnePairStep extends PairStep {
    @Override
    protected int getPairValue() {
        return 1;
    }

    @Override
    protected Function<Corner.Cell, CellValue> setFunction() {
        return Corner.Cell::getAway;
    }
}
