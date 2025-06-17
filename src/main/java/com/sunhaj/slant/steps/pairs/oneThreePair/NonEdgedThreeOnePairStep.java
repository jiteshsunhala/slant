package com.sunhaj.slant.steps.pairs.oneThreePair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NonEdgedThreeOnePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .pairDirection(Corner.Direction.right)
                        .skipValues(List.of(2))
                        .startCornerValue(3)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellFunctions(List.of(Corner::getTopLeft, Corner::getBottomLeft))
                        .startCellCondition((board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)))
                        .startCellValueFunction(Corner.Cell::getIncoming)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getTopRight, Corner::getBottomRight))
                        .endCellCondition((board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottom)
                        .skipValues(List.of(2))
                        .startCornerValue(3)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellFunctions(List.of(Corner::getTopLeft, Corner::getTopRight))
                        .startCellCondition((board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)))
                        .startCellValueFunction(Corner.Cell::getIncoming)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getBottomLeft, Corner::getBottomRight))
                        .endCellCondition((board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .build()
        );
    }
}
