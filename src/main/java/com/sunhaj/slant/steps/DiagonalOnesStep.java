package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.util.AlwaysTrue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

@Service
public class DiagonalOnesStep extends Step {

    @Override
    public boolean execute(Board board) {

        ArrayList<ArrayList<Corner>> corners = board.getCorners();

        for(ArrayList<Corner> cornerList: corners) {
            for (Corner corner : cornerList) {
                if (corner.getValue() == null || corner.getValue() != 1 || board.isEdgedCorner(corner)) {
                    continue;
                }

                Optional<Corner> bottomLeftCorner = board.getNextCorner(corner, 1, Corner.Direction.bottomLeft, new AlwaysTrue<>(), List.of());
                bottomLeftCorner.ifPresent(nextCorner -> solve(board, nextCorner, corner.getBottomLeft(), CellValue.backward));
                Optional<Corner> bottomRightCorner = board.getNextCorner(corner, 1, Corner.Direction.bottomRight, new AlwaysTrue<>(), List.of());
                bottomRightCorner.ifPresent(nextCorner -> solve(board, nextCorner, corner.getBottomRight(), CellValue.forward));
            }
        }

        return executeNext(board);
    }

    private void solve(Board board, Corner corner, Corner.Cell cell, CellValue cellValue) {
        if(board.isEdgedCorner(corner)) {
            return;
        }
        board.setCell(cell.getX(), cell.getY(), cellValue);
    }
}
