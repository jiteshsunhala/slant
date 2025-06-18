package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.Step;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

public abstract class PairStep extends Step {

    protected abstract List<PairCondition> getPairConditions();

    @Override
    public boolean execute(Board board) {
        List<PairCondition> pairConditions = getPairConditions();

        if(pairConditions == null || pairConditions.isEmpty()) {
            throw new IllegalArgumentException("Pair condition can't be null or empty");
        }

        board.getAllCorners()
                .forEach(corner -> trySolving(board, corner, pairConditions));

        return executeNext(board);
    }

    private void trySolving(Board board, Corner corner, List<PairCondition> pairConditions) {
        for(PairCondition pairCondition: pairConditions) {

            if(!pairCondition.isValidCorner(board, corner, pairCondition.getStartCornerCondition(), pairCondition.getStartCornerValue())) {
                continue;
            }

            if(!pairCondition.areValidCells(board, corner, pairCondition.getStartCellConditions())) {
                continue;
            }

            Optional<Corner> nextCorner = board.getNextCorner(corner, pairCondition);

            nextCorner.ifPresent(endCorner -> solve(board, corner, endCorner, pairCondition));
        }
    }

    private void solve(Board board, Corner startCorner, Corner endCorner, PairCondition pairCondition) {
        setCells(board, startCorner, pairCondition.getStartCellConditions());
        setCells(board, endCorner, pairCondition.getEndCellConditions());
    }

    private void setCells(Board board, Corner corner, List<PairCondition.CellCondition> cellConditions) {
        cellConditions.forEach(cellCondition -> {
            Corner.Cell cell = cellCondition.getCornerToCellFunction().apply(corner);
            CellValue cellValue = cellCondition.getCellToValueFunction().apply(cell);

            board.setCell(cell.getX(), cell.getY(), cellValue);
        });
    }
}
