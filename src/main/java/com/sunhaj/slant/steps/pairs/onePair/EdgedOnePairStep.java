package com.sunhaj.slant.steps.pairs.onePair;

import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.steps.pairs.PairStep;
import com.sunhaj.slant.util.EdgedCorner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgedOnePairStep extends PairStep {

    @Override
    protected List<PairCondition> getPairConditions() {
        return List.of(
                PairCondition.builder()
                        .pairDirection(Corner.Direction.right)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(new EdgedCorner())
                        .startCellConditions(List.of())
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )))
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.bottom)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(new EdgedCorner())
                        .startCellConditions(List.of())
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway),
                                new PairCondition.CellCondition(
                                        Corner::getBottomRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )))
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.left)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(new EdgedCorner())
                        .startCellConditions(List.of())
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway),
                                new PairCondition.CellCondition(
                                        Corner::getBottomLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )))
                        .build(),
                PairCondition.builder()
                        .pairDirection(Corner.Direction.top)
                        .skipValues(List.of(2))
                        .startCornerValue(1)
                        .startCornerCondition(new EdgedCorner())
                        .startCellConditions(List.of())
                        .endCornerValue(1)
                        .endCornerCondition(new EdgedCorner().negate())
                        .endCellConditions(List.of(
                                new PairCondition.CellCondition(
                                        Corner::getTopLeft,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway),
                                new PairCondition.CellCondition(
                                        Corner::getTopRight,
                                        (board, cell) -> !cell.getIncoming().equals(board.getCellValue(cell)),
                                        Corner.Cell::getAway
                                )))
                        .build()
        );
    }
}
