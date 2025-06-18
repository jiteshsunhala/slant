package com.sunhaj.slant.steps.pairs.oneTwoPair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.EdgedCorner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class EdgedOneTwoPairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                getPairCondition(Corner.Direction.right, Corner::getTopRight, Corner::getBottomRight),
                getPairCondition(Corner.Direction.right, Corner::getBottomRight, Corner::getTopRight),

                getPairCondition(Corner.Direction.bottom, Corner::getBottomLeft, Corner::getBottomRight),
                getPairCondition(Corner.Direction.bottom, Corner::getBottomRight, Corner::getBottomLeft),

                getPairCondition(Corner.Direction.left, Corner::getTopLeft, Corner::getBottomLeft),
                getPairCondition(Corner.Direction.left, Corner::getBottomLeft, Corner::getTopLeft),

                getPairCondition(Corner.Direction.top, Corner::getTopLeft, Corner::getTopRight),
                getPairCondition(Corner.Direction.top, Corner::getTopRight, Corner::getTopLeft)
        );
    }

    private PairCondition getPairCondition(Corner.Direction direction,
                                           Function<Corner, Corner.Cell> cell1Function,
                                           Function<Corner, Corner.Cell> cell2Function) {
        return PairCondition.builder()
                .pairDirection(direction)
                .skipValues(List.of(2))
                .startCornerValue(1)
                .startCornerCondition(new EdgedCorner())
                .startCellConditions(List.of())
                .endCornerValue(2)
                .endCornerCondition(new EdgedCorner().negate())
                .endCellConditions(List.of(
                        new PairCondition.CellCondition(
                                cell1Function,
                                (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner.Cell::getIncoming
                        ),
                        new PairCondition.CellCondition(
                                cell2Function,
                                (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner.Cell::getAway
                        )
                ))
                .build();
    }
}
