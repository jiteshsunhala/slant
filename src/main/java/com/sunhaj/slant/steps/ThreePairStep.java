package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .startCornerValue(3)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellFunctions(List.of(Corner::getTopLeft, Corner::getTopRight))
                        .startCellCondition((board, cell) -> !cell.getAway().equals(board.getCellValue(cell)))
                        .startCellValueFunction(Corner.Cell::getIncoming)
                        .endCornerValue(3)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getBottomLeft, Corner::getBottomRight))
                        .endCellCondition((board, cell) -> !cell.getAway().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.bottom)
                        .build(),
                PairCondition.builder()
                        .startCornerValue(3)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellFunctions(List.of(Corner::getTopLeft, Corner::getBottomLeft))
                        .startCellCondition((board, cell) -> !cell.getAway().equals(board.getCellValue(cell)))
                        .startCellValueFunction(Corner.Cell::getIncoming)
                        .endCornerValue(3)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellFunctions(List.of(Corner::getTopRight, Corner::getBottomRight))
                        .endCellCondition((board, cell) -> !cell.getAway().equals(board.getCellValue(cell)))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .skipValues(List.of(2))
                        .pairDirection(Corner.Direction.right)
                        .build()
        );
    }
}
