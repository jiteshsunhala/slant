package com.sunhaj.slant.steps;

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
public class FillRemainingStepTest {

    @Autowired
    private FillRemainingStep fillRemainingStep;

    private static Stream<Arguments> differentBoards() {
        return Stream.of(
                /*
                0-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(0, 0, 0)), Map.of()),
                        15,
                        Map.of(
                                new Corner.Cell(0, 0),
                                CellValue.forward
                        )
                ),
                /*
                +-+-0-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(0, 2, 0)), Map.of()),
                        14,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.forward
                        )
                ),
                /*
                +-+-1-+-+
                | | |\| |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(0, 2, 1)), Map.of(new Corner.Cell(0, 2), CellValue.backward)),
                        14,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.backward
                        )
                ),
                /*
                +-+-1-+-+
                | |\| | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(0, 2, 1)), Map.of(new Corner.Cell(0, 1), CellValue.backward)),
                        14,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.backward
                        )
                ),
                /*
                1-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(0, 0, 1)), Map.of()),
                        15,
                        Map.of(
                                new Corner.Cell(0, 0),
                                CellValue.backward
                        )
                ),
                /*
                +-+-+-+-+
                | | |/| |
                +-+-1-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 1)),
                                Map.of(
                                        new Corner.Cell(0, 2),
                                        CellValue.forward
                                )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.forward,
                                new Corner.Cell(0, 2),
                                CellValue.forward,
                                new Corner.Cell(1, 1),
                                CellValue.backward,
                                new Corner.Cell(1, 2),
                                CellValue.forward
                        )
                ),
                /*
                +-+-+-+-+
                | |/| | |
                +-+-1-+-+
                | |\|/| |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 1)),
                                Map.of(
                                        new Corner.Cell(0, 1),
                                        CellValue.forward,
                                        new Corner.Cell(1, 1),
                                        CellValue.backward,
                                        new Corner.Cell(1, 2),
                                        CellValue.forward
                                )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.forward,
                                new Corner.Cell(0, 2),
                                CellValue.forward,
                                new Corner.Cell(1, 1),
                                CellValue.backward,
                                new Corner.Cell(1, 2),
                                CellValue.forward
                        )
                ),
                /*
                +-+-2-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(0, 2, 2)), Map.of()),
                        14,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.forward,
                                new Corner.Cell(0, 2),
                                CellValue.backward
                        )
                ),
                /*
                +-+-+-+-+
                | |\|/| |
                +-+-2-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 2)),
                                Map.of(
                                        new Corner.Cell(0, 1),
                                        CellValue.backward,
                                        new Corner.Cell(0, 2),
                                        CellValue.forward
                                )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.forward,
                                new Corner.Cell(1, 1),
                                CellValue.backward,
                                new Corner.Cell(1, 2),
                                CellValue.forward
                        )
                ),
                /*
                +-+-+-+-+
                | |\| | |
                +-+-2-+-+
                | | |\| |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 2)),
                                Map.of(
                                        new Corner.Cell(0, 1),
                                        CellValue.backward,
                                        new Corner.Cell(1, 2),
                                        CellValue.backward
                                )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.backward,
                                new Corner.Cell(1, 1),
                                CellValue.backward,
                                new Corner.Cell(1, 2),
                                CellValue.backward
                        )
                ),
                /*
                +-+-+-+-+
                | |/|\| |
                +-+-2-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 2)),
                                Map.of(
                                        new Corner.Cell(0, 1),
                                        CellValue.forward,
                                        new Corner.Cell(0, 2),
                                        CellValue.backward
                                )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.forward,
                                new Corner.Cell(0, 2),
                                CellValue.backward,
                                new Corner.Cell(1, 1),
                                CellValue.forward,
                                new Corner.Cell(1, 2),
                                CellValue.backward
                        )
                )
                ,
                /*
                +-+-+-+-+
                | | |\| |
                +-+-3-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 3)),
                                Map.of(
                                        new Corner.Cell(0, 2),
                                        CellValue.backward
                                )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.backward,
                                new Corner.Cell(1, 1),
                                CellValue.forward,
                                new Corner.Cell(1, 2),
                                CellValue.backward
                        )
                ),
                /*
                +-+-+-+-+
                | |\| | |
                +-+-3-+-+
                | |/|\| |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 3)),
                                Map.of(
                                        new Corner.Cell(0, 1),
                                        CellValue.backward,
                                        new Corner.Cell(1, 1),
                                        CellValue.forward,
                                        new Corner.Cell(1, 2),
                                        CellValue.backward
                                )),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.backward,
                                new Corner.Cell(1, 1),
                                CellValue.forward,
                                new Corner.Cell(1, 2),
                                CellValue.backward
                        )
                ),
                /*
                +-+-+-+-+
                | | | | |
                +-+-4-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                | | | | |
                +-+-+-+-+
                 */
                Arguments.of(
                        getBoard(4, 4, List.of(new Corner(1, 2, 4)), Map.of()),
                        12,
                        Map.of(
                                new Corner.Cell(0, 1),
                                CellValue.backward,
                                new Corner.Cell(0, 2),
                                CellValue.forward,
                                new Corner.Cell(1, 1),
                                CellValue.forward,
                                new Corner.Cell(1, 2),
                                CellValue.backward
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("differentBoards")
    public void testAllBoards(Board board, int emptyCells, Map<Corner.Cell, CellValue> cellToValueMap) {

        fillRemainingStep.execute(board);
        Assertions.assertEquals(emptyCells, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        cellToValueMap.forEach((key, value) -> {
            Assertions.assertEquals(value, board.getCellValue(key));
        });
    }

    private static Board getBoard(int r, int c, List<Corner> corners, Map<Corner.Cell, CellValue> cellToValueMap) {
        Board board = new Board(r, c, corners);
        cellToValueMap.forEach((key, value) -> {
            board.setCell(key.getX(), key.getY(), value);
        });

        return board;
    }
}
