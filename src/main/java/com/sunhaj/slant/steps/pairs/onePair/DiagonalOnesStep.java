package com.sunhaj.slant.steps.pairs.onePair;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.Step;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.AlwaysTrue;
import com.sunhaj.slant.util.EdgedCorner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class DiagonalOnesStep extends PairStep {
    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottomLeft)
                        .skipValues(List.of())
                        .startCornerValue(1)
                        .startCornerCondition(new EdgedCorner().negate())
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        new AlwaysTrue<>(),
                                        Corner.Cell::getAway
                                )
                        ))
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of())
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottomRight)
                        .skipValues(List.of())
                        .startCornerValue(1)
                        .startCornerCondition(new EdgedCorner().negate())
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        new AlwaysTrue<>(),
                                        Corner.Cell::getAway
                                )
                        ))
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of())
                        .build()
        );
    }
}
