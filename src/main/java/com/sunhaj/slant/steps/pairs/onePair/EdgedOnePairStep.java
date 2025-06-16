package com.sunhaj.slant.steps.pairs.onePair;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.AlwaysTrue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgedOnePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getTopRight, Corner::getBottomRight))
                        .endCellCondition((board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.right)
                        .build(),
                PairCondition.builder()
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getBottomLeft, Corner::getBottomRight))
                        .endCellCondition((board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.bottom)
                        .build(),
                PairCondition.builder()
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getTopLeft, Corner::getBottomLeft))
                        .endCellCondition((board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.left)
                        .build(),
                PairCondition.builder()
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellFunctions(List.of())
                        .startCellCondition(new AlwaysTrue<>())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getTopLeft, Corner::getTopRight))
                        .endCellCondition((board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.top)
                        .build()
        );
    }
}
