package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;

@Data
@Builder
public class PairCondition {

    private List<Integer> skipValues;
    private Corner.Direction pairDirection;

    private int startCornerValue;
    private BiPredicate<Board, Corner> startCornerCondition;
    private List<CellCondition> startCellConditions;

    private int endCornerValue;
    private BiPredicate<Board, Corner> endCornerCondition;
    private List<CellCondition> endCellConditions;

    @AllArgsConstructor
    @Getter
    public static class CellCondition {
        private Function<Corner, Corner.Cell> cornerToCellFunction;
        private BiPredicate<Board, Corner.Cell> cellCondition;
        private Function<Corner.Cell, CellValue> cellToValueFunction;
    }

    public boolean isValidCorner(Board board, Corner corner, BiPredicate<Board, Corner> cornerCondition, int cornerValue) {
        if(corner.getValue() == null) {
            return false;
        }
        if(corner.getValue() != cornerValue) {
            return false;
        }
        if(!cornerCondition.test(board, corner)) {
            return false;
        }

        return true;
    }

    public boolean areValidCells(Board board, Corner corner, List<CellCondition> cellConditions) {
        return cellConditions.stream()
                .allMatch(cellCondition -> {
                    Corner.Cell cell = cellCondition.getCornerToCellFunction().apply(corner);
                    return cellCondition.getCellCondition().test(board, cell);
                });
    }
}
