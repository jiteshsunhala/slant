package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OnePairStep extends Step {

    @Override
    public boolean execute(Board board) {
        solveRight(board);
        solveBottom(board);

        return executeNext(board);
    }

    private void solveBottom(Board board) {
        ArrayList<ArrayList<Corner>> corners = board.getCorners();

        int cols = corners.get(0).size();
        int rows = corners.size();

        for(int j=0;j<cols;j++) {
            for(int i=0;i<rows;i++) {
                Corner corner = corners.get(i).get(j);
                if (corner.getValue() == null || corner.getValue() != 1) {
                    continue;
                }

                Optional<Corner> nextCorner = board.getNextCorner(corner, 1, Corner.Direction.bottom, List.of(2));
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

        for (ArrayList<Corner> cornerArrayList : corners) {
            for (int j = 0; j < cornerArrayList.size(); j++) {
                Corner corner = cornerArrayList.get(j);
                if (corner.getValue() == null || corner.getValue() != 1) {
                    continue;
                }

                Optional<Corner> nextCorner = board.getNextCorner(corner, 1, Corner.Direction.right, List.of(2));
                if(nextCorner.isEmpty()) {
                    continue;
                }

                solve(board, corner.getTopLeft(), corner.getBottomLeft(), nextCorner.get().getTopRight(), nextCorner.get().getBottomRight());
                j = nextCorner.get().getY();
            }
        }
    }

    private void solve(Board board, Corner.Cell start1, Corner.Cell start2, Corner.Cell end1, Corner.Cell end2) {
        if(start1.getIncoming().equals(board.getCellValue(start1.getX(), start1.getY())) ||
        start2.getIncoming().equals(board.getCellValue(start2.getX(), start2.getY())) ||
        end1.getIncoming().equals(board.getCellValue(end1.getX(), end2.getY())) ||
        end2.getIncoming().equals(board.getCellValue(end2.getX(), end2.getY()))) {
            return;
        }

        board.setCell(start1.getX(), start1.getY(), start1.getAway());
        board.setCell(start2.getX(), start2.getY(), start2.getAway());
        board.setCell(end1.getX(), end1.getY(), end1.getAway());
        board.setCell(end2.getX(), end2.getY(), end2.getAway());
    }
}
