package com.sunhaj.slant.steps.pairs.oneTwoPair;

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

@SpringBootTest(classes = EdgedOneTwoPairStep.class)
public class EdgedOneTwoPairStepTest {

    @Autowired
    private EdgedOneTwoPairStep edgedOneTwoPairStep;

    private static Stream<Arguments> differentBoards() {
        return Stream.of(
                /*
                +-1-+-+-+
                | | | | |
                +-2-+-+-+
                | |\| | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */

                Arguments.of(getBoard(4, 4,
                        List.of(
                                new Corner(0, 1, 1),
                                new Corner(1, 1, 2)
                        ),
                        Map.of(
                                new Corner.Cell(1, 1),
                                CellValue.backward
                        )
                    ),
                    14,
                    Map.of(
                            new Corner.Cell(1, 0),
                            CellValue.backward,
                            new Corner.Cell(1, 1),
                            CellValue.backward
                    )
                ),
                /*
                +-1-+-+-+
                | | | | |
                +-2-+-+-+
                | | | | |
                +-2-+-+-+
                | |\| | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */

                Arguments.of(getBoard(4, 4,
                                List.of(
                                        new Corner(0, 1, 1),
                                        new Corner(1, 1, 2),
                                        new Corner(2, 1, 2)
                                ),
                                Map.of(
                                        new Corner.Cell(2, 1),
                                        CellValue.backward
                                )
                        ),
                        14,
                        Map.of(
                                new Corner.Cell(2, 0),
                                CellValue.backward,
                                new Corner.Cell(2, 1),
                                CellValue.backward
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("differentBoards")
    public void testAllBoards(Board board, int emptyCells, Map<Corner.Cell, CellValue> cellValueMap) {

        edgedOneTwoPairStep.execute(board);

        Assertions.assertEquals(emptyCells, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        cellValueMap.forEach((key, value) -> {
            Assertions.assertEquals(value, board.getCellValue(key));
        });
    }

    private static Board getBoard(int r, int c, List<Corner> corners, Map<Corner.Cell, CellValue> cellValueMap) {
        Board board = new Board(r, c, corners);
        cellValueMap.forEach((key, value) -> board.setCell(key.getX(), key.getY(), value));

        return board;
    }
}
