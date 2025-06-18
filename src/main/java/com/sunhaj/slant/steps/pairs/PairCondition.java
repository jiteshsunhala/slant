package com.sunhaj.slant.steps.pairs;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
    private Map<Function<Corner, Corner.Cell>, BiPredicate<Board, Corner.Cell>> startCellConditions;

    private int endCornerValue;
    private BiPredicate<Board, Corner> endCornerCondition;
    private Map<Function<Corner, Corner.Cell>, BiPredicate<Board, Corner.Cell>> endCellConditions;

    private Function<Corner.Cell, CellValue> startCellValueFunction;
    private Function<Corner.Cell, CellValue> endCellValueFunction;
}
