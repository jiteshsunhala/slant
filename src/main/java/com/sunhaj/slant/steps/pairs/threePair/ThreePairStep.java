package com.sunhaj.slant.steps.pairs.threePair;

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
                        .pairDirection(Corner.Direction.bottom)
                        .skipValues(List.of(2))
                        .startCornerValue(3)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                )
                        ))
                        .endCornerValue(3)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                )
                        ))
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.right)
                        .skipValues(List.of(2))
                        .startCornerValue(3)
                        .startCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                )
                        ))
                        .endCornerValue(3)
                        .endCornerCondition((board, corner) -> !board.isEdgedCorner(corner))
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getAway().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                )
                        ))
                        .build()
        );
    }
}
