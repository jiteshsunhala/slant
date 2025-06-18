package com.sunhaj.slant.steps.pairs.oneThreePair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.AlwaysTrue;
import com.sunhaj.slant.util.EdgedCorner;
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
                        .startCornerCondition(new AlwaysTrue<>())
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                )
                        ))
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottom)
                        .skipValues(List.of(2))
                        .startCornerValue(3)
                        .startCornerCondition(new AlwaysTrue<>())
                        .startCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getIncoming
                                )
                        ))
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                ),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )
                        ))
                        .build()
        );
    }
}
