package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

@Data
@Builder
public class PairCondition {

    private int startCornerValue;
    private BiPredicate<Board, Corner> startCornerCondition;
    private List<Function<Corner, Corner.Cell>> startCellFunctions;
    private BiPredicate<Board, Corner.Cell> startCellCondition;

    private int endCornerValue;
    private BiPredicate<Board, Corner> endCornerCondition;
    private List<Function<Corner, Corner.Cell>> endCellFunctions;
    private BiPredicate<Board, Corner.Cell> endCellCondition;

    private List<Integer> skipValues;
    private Corner.Direction pairDirection;

    private Function<Corner.Cell, CellValue> startCellValueFunction;
    private Function<Corner.Cell, CellValue> endCellValueFunction;
}
