package com.sunhaj.slant.steps.pairs.twoPair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.EdgedCorner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SameRowTwoPairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                getPairConditionBuilder()
                        .pairDirection(Corner.Direction.right)
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .build(),
                getPairConditionBuilder()
                        .pairDirection(Corner.Direction.right)
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .build(),
                getPairConditionBuilder()
                        .pairDirection(Corner.Direction.bottom)
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .build(),
                getPairConditionBuilder()
                        .pairDirection(Corner.Direction.bottom)
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .build()
        );
    }

    private PairCondition.PairConditionBuilder getPairConditionBuilder() {
        return PairCondition.builder()
                .skipValues(List.of(2))
                .startCornerValue(2)
                .startCornerCondition(new EdgedCorner().negate())
                .endCornerValue(2)
                .endCornerCondition(new EdgedCorner().negate());
    }
}
