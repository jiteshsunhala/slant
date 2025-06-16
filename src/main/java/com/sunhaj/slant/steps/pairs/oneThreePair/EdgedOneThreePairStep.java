package com.sunhaj.slant.steps.pairs.oneThreePair;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.AlwaysTrue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgedOneThreePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.right)
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellFunctions(List.of(Corner::getTopRight, Corner::getBottomRight))
                        .endCellCondition(((board, cell) -> !cell.getAway().equals(board.getCellValue(cell))))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build(),
                PairCondition.builder()
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.bottom)
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellFunctions(List.of(Corner::getBottomLeft, Corner::getBottomRight))
                        .endCellCondition(((board, cell) -> !cell.getAway().equals(board.getCellValue(cell))))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build(),
                PairCondition.builder()
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.left)
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellFunctions(List.of(Corner::getTopLeft, Corner::getBottomLeft))
                        .endCellCondition(((board, cell) -> !cell.getAway().equals(board.getCellValue(cell))))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build(),
                PairCondition.builder()
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.top)
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellFunctions(List.of(Corner::getTopLeft, Corner::getTopRight))
                        .endCellCondition(((board, cell) -> !cell.getAway().equals(board.getCellValue(cell))))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build()
        );
    }
}
