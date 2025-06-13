package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class PairStep extends Step {

    protected abstract int getPairValue();
    protected abstract Function<Corner.Cell, CellValue> setFunction();

    @Override
    public boolean execute(Board board) {
        solveRight(board);
        solveBottom(board);

        return executeNext(board);
    }

    private void solveBottom(Board board) {
        ArrayList<ArrayList<Corner>> corners = board.getCorners();

        int cols = corners.getFirst().size();
        int rows = corners.size();

        for(int j=1;j<cols;j++) {
            for(int i=0;i<rows;i++) {
                Corner corner = corners.get(i).get(j);

                if (corner.getValue() == null || corner.getValue() != getPairValue()) {
                    continue;
                }

                Optional<Corner> nextCorner = board.getNextCorner(corner, getPairValue(), Corner.Direction.bottom, List.of(2));
                if(nextCorner.isEmpty()) {
                    continue;
                }

                solve(board, corner.getTopLeft(), corner.getTopRight(), nextCorner.get().getBottomLeft(), nextCorner.get().getBottomRight());
                i = nextCorner.get().getX();
            }
        }
    }

    private void solveRight(Board board) {
        ArrayList<ArrayList<Corner>> corners = board.getCorners();

        for (int i=1;i<corners.size();i++) {
            for (int j = 0; j < corners.get(i).size(); j++) {
                Corner corner = corners.get(i).get(j);
                if(board.isEdgedCorner(corner)) {
                    continue;
                }
                if (corner.getValue() == null || corner.getValue() != getPairValue()) {
                    continue;
                }

                Optional<Corner> nextCorner = board.getNextCorner(corner, getPairValue(), Corner.Direction.right, List.of(2));
                if(nextCorner.isEmpty()) {
                    continue;
                }

                solve(board, corner.getTopLeft(), corner.getBottomLeft(), nextCorner.get().getTopRight(), nextCorner.get().getBottomRight());
                j = nextCorner.get().getY();
            }
        }
    }

    private void solve(Board board, Corner.Cell start1, Corner.Cell start2, Corner.Cell end1, Corner.Cell end2) {

        board.setCell(start1.getX(), start1.getY(), setFunction().apply(start1));
        board.setCell(start2.getX(), start2.getY(), setFunction().apply(start2));
        board.setCell(end1.getX(), end1.getY(), setFunction().apply(end1));
        board.setCell(end2.getX(), end2.getY(), setFunction().apply(end2));
    }
}
