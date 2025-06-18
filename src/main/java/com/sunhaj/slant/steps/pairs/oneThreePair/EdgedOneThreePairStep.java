package com.sunhaj.slant.steps.pairs.oneThreePair;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.AlwaysTrue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EdgedOneThreePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .pairDirection(Corner.Direction.right)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellConditions(Map.of())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellConditions(Map.of(
                                Corner::getTopRight, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                Corner::getBottomRight, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottom)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellConditions(Map.of())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellConditions(Map.of(
                                Corner::getBottomLeft, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                Corner::getBottomRight, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.left)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellConditions(Map.of())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellConditions(Map.of(
                                Corner::getTopLeft, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                Corner::getBottomLeft, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.top)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(Board::isEdgedCorner)
                        .startCellConditions(Map.of())
                        .startCellValueFunction(Corner.Cell::getAway)
                        .endCornerValue(3)
                        .endCornerCondition(new AlwaysTrue<>())
                        .endCellConditions(Map.of(
                                Corner::getTopLeft, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                Corner::getTopRight, (board, cell) -> !cell.getAway().equals(board.getCellValue(cell))
                        ))
                        .endCellValueFunction(Corner.Cell::getIncoming)
                        .build()
        );
    }
}
