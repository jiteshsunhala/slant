package com.sunhaj.slant.steps.pairs.oneThreePair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.AlwaysTrue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NonEdgedThreeOnePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .pairDirection(Corner.Direction.right)
                        .skipValues(List.of(2))
                        .startCornerValue(3)
                        .startCornerCondition(new AlwaysTrue<>())
                        .startCellConditions(Map.of(
                                Corner::getTopLeft, (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getBottomLeft, (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .startCellValueFunction(Corner.Cell::getIncoming)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellConditions(Map.of(
                                Corner::getTopRight, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getBottomRight, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottom)
                        .skipValues(List.of(2))
                        .startCornerValue(3)
                        .startCornerCondition(new AlwaysTrue<>())
                        .startCellConditions(Map.of(
                                Corner::getTopLeft, (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getTopRight, (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .startCellValueFunction(Corner.Cell::getIncoming)
                        .endCornerValue(1)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellConditions(Map.of(
                                Corner::getBottomLeft, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                Corner::getBottomRight, (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getAway)
                        .build()
        );
    }
}
