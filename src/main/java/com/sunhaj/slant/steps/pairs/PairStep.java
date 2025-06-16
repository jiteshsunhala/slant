package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.Step;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

public abstract class PairStep extends Step {

    protected abstract List<PairCondition> getPairConditions();
    protected abstract String getStepName();

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
            if(!pairCondition.getStartCornerCondition().test(board, corner) ||
                    corner.getValue() == null ||
                    corner.getValue() != pairCondition.getStartCornerValue()) {
                continue;
            }

            Optional<Corner> nextCorner = board.getNextCorner(corner,
                    pairCondition.getEndCornerValue(),
                    pairCondition.getPairDirection(),
                    pairCondition.getEndCornerCondition(),
                    pairCondition.getSkipValues()
            );

            nextCorner.ifPresent(endCorner -> solve(board, corner, endCorner, pairCondition));
        }
    }

    private void solve(Board board, Corner startCorner, Corner endCorner, PairCondition pairCondition) {
        List<Corner.Cell> startCells = pairCondition.getStartCellFunctions()
                .stream()
                .map(fn -> fn.apply(startCorner))
                .toList();

        if(!areValidCells(board, startCells, pairCondition.getStartCellCondition())) {
            return;
        }

        List<Corner.Cell> endCells = pairCondition.getEndCellFunctions()
                .stream()
                .map(fn -> fn.apply(endCorner))
                .toList();

        if(!areValidCells(board, endCells, pairCondition.getEndCellCondition())) {
            return;
        }

        setCells(board, startCells, pairCondition.getStartCellValueFunction());
        setCells(board, endCells, pairCondition.getEndCellValueFunction());
    }

    private void setCells(Board board, List<Corner.Cell> cells, Function<Corner.Cell, CellValue> cellValueFn) {
        cells.stream().forEach(cell -> {
            board.setCell(cell.getX(), cell.getY(), cellValueFn.apply(cell));
        });
    }

    private boolean areValidCells(Board board, List<Corner.Cell> cells, BiPredicate<Board, Corner.Cell> cellCondition) {
        return cells.stream().allMatch(cell -> cellCondition.test(board, cell));
    }
}
