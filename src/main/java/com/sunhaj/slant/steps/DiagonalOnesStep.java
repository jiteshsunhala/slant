package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.steps.pairs.PairCondition;
import com.sunhaj.slant.util.AlwaysTrue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

@Service
public class DiagonalOnesStep extends Step {

    @Override
    public boolean execute(Board board) {

        ArrayList<ArrayList<Corner>> corners = board.getCorners();

        PairCondition.PairConditionBuilder pairConditionBuilder = PairCondition.builder()
                .skipValues(List.of())
                .startCornerValue(1)
                .startCornerCondition((b, corner) -> !b.isEdgedCorner(corner))
                .endCornerValue(1)
                .endCornerCondition((b, corner) -> !b.isEdgedCorner(corner))
                .endCellConditions(List.of());

        for(ArrayList<Corner> cornerList: corners) {
            for (Corner corner : cornerList) {
                if (corner.getValue() == null || corner.getValue() != 1 || board.isEdgedCorner(corner)) {
                    continue;
                }

                trySolving(board, corner, pairConditionBuilder, Corner.Direction.bottomLeft, Corner::getBottomLeft);
                trySolving(board, corner, pairConditionBuilder, Corner.Direction.bottomRight, Corner::getBottomRight);
            }
        }

        return executeNext(board);
    }

    private void trySolving(Board board, Corner corner, PairCondition.PairConditionBuilder pairConditionBuilder, Corner.Direction direction, Function<Corner, Corner.Cell> cornerCellFunction) {
        PairCondition pairCondition = pairConditionBuilder
                .pairDirection(direction)
                .startCellConditions(List.of(
                        new PairCondition.CellCondition(
                                cornerCellFunction,
                                new AlwaysTrue<>(),
                                Corner.Cell::getAway
                        )
                ))
                .build();
        Optional<Corner> nextCorner = board.getNextCorner(corner, pairCondition);
        nextCorner.ifPresent(endCorner -> solve(board, corner, pairCondition));
    }

    private void solve(Board board, Corner corner, PairCondition pairCondition) {
        pairCondition.getStartCellConditions().forEach(cellCondition -> {
            Corner.Cell cell = cellCondition.getCornerToCellFunction().apply(corner);
            CellValue cellValue = cellCondition.getCellToValueFunction().apply(cell);

            board.setCell(cell.getX(), cell.getY(), cellValue);
        });
    }
}
