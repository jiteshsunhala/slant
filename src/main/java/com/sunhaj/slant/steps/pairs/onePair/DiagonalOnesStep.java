package com.sunhaj.slant.steps.pairs.onePair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.AlwaysTrue;
import com.sunhaj.slant.util.EdgedCorner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class DiagonalOnesStep extends PairStep {
    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                getPairCondition(Corner.Direction.bottomLeft, Corner::getBottomLeft),
                getPairCondition(Corner.Direction.bottomRight, Corner::getBottomRight)
        );
    }

    private PairCondition getPairCondition(Corner.Direction direction, Function<Corner, Corner.Cell> cellFunction) {
        return PairCondition.builder()
                .pairDirection(direction)
                .skipValues(List.of())
                .startCornerValue(1)
                .startCornerCondition(new EdgedCorner().negate())
                .startCellConditions(List.of(
                        new PairCondition.CellCondition(
                                cellFunction,
                                new AlwaysTrue<>(),
                                Corner.Cell::getAway
                        )
                ))
                .endCornerValue(1)
                .endCornerCondition(new EdgedCorner().negate())
                .endCellConditions(List.of())
                .build();
    }
}
