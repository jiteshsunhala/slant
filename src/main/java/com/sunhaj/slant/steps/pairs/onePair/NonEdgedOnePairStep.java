package com.sunhaj.slant.steps.pairs.onePair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NonEdgedOnePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottom)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellConditions(Map.of(
                                Corner::getTopLeft, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getTopRight, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellConditions(Map.of(
                                Corner::getBottomLeft, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getBottomRight, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.right)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellConditions(Map.of(
                                Corner::getTopLeft, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getBottomLeft, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellConditions(Map.of(
                                Corner::getTopRight, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getBottomRight, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .build()
        );
    }
}
