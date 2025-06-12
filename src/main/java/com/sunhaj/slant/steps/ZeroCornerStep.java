package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class ZeroCornerStep extends Step {

    @Override
    public boolean execute(Board board) {

        board.getCorners()
                .stream()
                .flatMap(Collection::stream)
                .filter(corner -> corner.getValue() != null && corner.getValue() == 0)
                .forEach(corner -> {
                    List<Corner.Cell> cells = corner.getAllCells();
                    cells.forEach(cell -> board.setCell(cell.getX(), cell.getY(), cell.getAway()));
                });

        return executeNext(board);
    }
}
