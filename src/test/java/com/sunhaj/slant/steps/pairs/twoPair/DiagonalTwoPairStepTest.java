package com.sunhaj.slant.steps.pairs.twoPair;

import com.sunhaj.slant.config.SlantTestConfiguration;
import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest(classes = SlantTestConfiguration.class)
public class DiagonalTwoPairStepTest {

    @Autowired
    private DiagonalTwoPairStep diagonalTwoPairStep;

    @ParameterizedTest
    @MethodSource("differentBoards")
    public void testAllCases(String id, Board board, int emptyCells, Map<Corner.Cell, CellValue> cellValueMap) {

        diagonalTwoPairStep.execute(board);
        Assertions.assertEquals(emptyCells, board.getAllCellValues().stream().filter(CellValue.none::equals).count());

        cellValueMap.forEach((cell, cellValue) -> Assertions.assertEquals(cellValue, board.getCellValue(cell.getX(), cell.getY())));
    }

    private static Stream<Arguments> differentBoards() {
        return Stream.of(
                /*
                +-+-+-+-+
                | | |/| |
                +-2-2-+-+
                |/| | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        "direction-right, bottom-left -> top-right",
                        getBoard(4, 4, List.of(
                                new Corner(1, 1, 2),
                                new Corner(1, 2, 2)
                        ), Map.of(
                                new Corner.Cell(0, 2),
                                CellValue.forward,
                                new Corner.Cell(1, 0),
                                CellValue.forward
                        )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 0),
                                CellValue.forward,
                                new Corner.Cell(0, 2),
                                CellValue.forward,
                                new Corner.Cell(1, 0),
                                CellValue.forward,
                                new Corner.Cell(1, 2),
                                CellValue.forward
                        )
                ),
                /*
                +-+-+-+-+
                |\| | | |
                +-2-2-+-+
                | | |\| |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        "direction-right, top-left -> bottom-right",
                        getBoard(4, 4, List.of(
                                new Corner(1, 1, 2),
                                new Corner(1, 2, 2)
                        ), Map.of(
                                new Corner.Cell(0, 0),
                                CellValue.backward,
                                new Corner.Cell(1, 2),
                                CellValue.backward
                        )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 0),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.backward,
                                new Corner.Cell(1, 0),
                                CellValue.backward,
                                new Corner.Cell(1, 2),
                                CellValue.backward
                        )
                )
        );
    }

    private static Board getBoard(int r, int c, List<Corner> corners, Map<Corner.Cell, CellValue> cellValueMap) {
        Board board = new Board(r, c, corners);
        cellValueMap.forEach((cell, cellValue) -> board.setCell(cell.getX(), cell.getY(), cellValue));

        return board;
    }
}
