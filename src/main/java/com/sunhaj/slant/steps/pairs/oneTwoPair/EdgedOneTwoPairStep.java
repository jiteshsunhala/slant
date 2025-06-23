package com.sunhaj.slant.steps.pairs.oneTwoPair;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.EdgedCorner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

@Service
public class EdgedOneTwoPairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        BiPredicate<Board, Corner.Cell> incomingCellPredicate = (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell));
        BiPredicate<Board, Corner.Cell> awayCellPredicate = (board, cell) -> cell.getAway().equals(board.getCellValue(cell));

        return List.of(
                getPairCondition(Corner.Direction.right, Corner::getTopRight, Corner::getBottomRight, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.right, Corner::getBottomRight, Corner::getTopRight, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.right, Corner::getTopRight, Corner::getBottomRight, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming),
                getPairCondition(Corner.Direction.right, Corner::getBottomRight, Corner::getTopRight, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming),

                getPairCondition(Corner.Direction.bottom, Corner::getBottomLeft, Corner::getBottomRight, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.bottom, Corner::getBottomRight, Corner::getBottomLeft, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.bottom, Corner::getBottomLeft, Corner::getBottomRight, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming),
                getPairCondition(Corner.Direction.bottom, Corner::getBottomRight, Corner::getBottomLeft, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming),

                getPairCondition(Corner.Direction.left, Corner::getTopLeft, Corner::getBottomLeft, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.left, Corner::getBottomLeft, Corner::getTopLeft, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.left, Corner::getTopLeft, Corner::getBottomLeft, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming),
                getPairCondition(Corner.Direction.left, Corner::getBottomLeft, Corner::getTopLeft, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming),

                getPairCondition(Corner.Direction.top, Corner::getTopLeft, Corner::getTopRight, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.top, Corner::getTopRight, Corner::getTopLeft, incomingCellPredicate, Corner.Cell::getIncoming, Corner.Cell::getAway),
                getPairCondition(Corner.Direction.top, Corner::getTopLeft, Corner::getTopRight, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming),
                getPairCondition(Corner.Direction.top, Corner::getTopRight, Corner::getTopLeft, awayCellPredicate, Corner.Cell::getAway, Corner.Cell::getIncoming)
        );
    }

    private PairCondition getPairCondition(Corner.Direction direction,
                                           Function<Corner, Corner.Cell> cell1Function,
                                           Function<Corner, Corner.Cell> cell2Function,
                                           BiPredicate<Board, Corner.Cell> cellPredicate,
                                           Function<Corner.Cell, CellValue> cell1ValueFunction,
                                           Function<Corner.Cell, CellValue> cell2ValueFunction) {
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
                                cellPredicate,
                                cell1ValueFunction
                        ),
                        new PairCondition.CellCondition(
                                cell2Function,
                                cellPredicate.negate(),
                                cell2ValueFunction
                        )
                ))
                .build();
    }
}
