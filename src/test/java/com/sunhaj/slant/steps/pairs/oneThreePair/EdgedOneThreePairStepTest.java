package com.sunhaj.slant.steps.pairs.oneThreePair;

import com.sunhaj.slant.config.SlantTestConfiguration;
import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest(classes = SlantTestConfiguration.class)
public class EdgedOneThreePairStepTest {

    @Autowired
    private EdgedOneThreePairStep edgedOneThreePairStep;

    @Nested
    public class WithoutTwosTests {

        private static Stream<Arguments> differentBoard() {

            return Stream.of(
                    /*
                    +-+-1-+-+
                    | | | | |
                    +-+-3-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(0, 2, 1),
                                    new Corner(1, 2, 3)
                            )),
                            14,
                            Map.of(new Corner.Cell(1, 1), CellValue.forward, new Corner.Cell(1, 2), CellValue.backward)
                    ),
                    /*
                    +-+-+-+-+
                    | | | | |
                    +-+-+-3-1
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(1, 3, 3),
                                    new Corner(1, 4, 1)
                            )),
                            14,
                            Map.of(new Corner.Cell(0, 2), CellValue.backward, new Corner.Cell(1, 2), CellValue.forward)
                    ),
                    /*
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-3-+-+-+
                    | | | | |
                    +-1-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(3, 1, 3),
                                    new Corner(4, 1, 1)
                            )),
                            14,
                            Map.of(new Corner.Cell(2, 0), CellValue.backward, new Corner.Cell(2, 1), CellValue.forward)
                    ),
                    /*
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    1-3-+-+-+
                    | | | | |
                    +-+-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(3, 0, 1),
                                    new Corner(3, 1, 3)
                            )),
                            14,
                            Map.of(new Corner.Cell(2, 1), CellValue.forward, new Corner.Cell(3, 1), CellValue.backward)
                    )
            );
        }

        @ParameterizedTest
        @MethodSource(value = "differentBoard")
        public void testAllBoards(Board board, int emptyCount, Map<Corner.Cell, CellValue> cellValueMap) {
            edgedOneThreePairStep.execute(board);

            cellValueMap.forEach((key, value) -> Assertions.assertEquals(value, board.getCellValue(key.getX(), key.getY())));
            Assertions.assertEquals(emptyCount, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        }
    }

    @Nested
    public class WithTwosTests {

        private static Stream<Arguments> differentBoard() {

            return Stream.of(
                    /*
                    +-+-1-+-+
                    | | | | |
                    +-+-2-+-+
                    | | | | |
                    +-+-3-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(0, 2, 1),
                                    new Corner(1, 2, 2),
                                    new Corner(2, 2, 3)
                            )),
                            14,
                            Map.of(new Corner.Cell(2, 1), CellValue.forward, new Corner.Cell(2, 2), CellValue.backward)
                    ),
                    /*
                    +-+-+-+-+
                    | | | | |
                    +-+-3-2-1
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(1, 2, 3),
                                    new Corner(1, 3, 2),
                                    new Corner(1, 4, 1)
                            )),
                            14,
                            Map.of(new Corner.Cell(0, 1), CellValue.backward, new Corner.Cell(1, 1), CellValue.forward)
                    ),
                    /*
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-3-+-+-+
                    | | | | |
                    +-2-+-+-+
                    | | | | |
                    +-1-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(2, 1, 3),
                                    new Corner(3, 1, 2),
                                    new Corner(4, 1, 1)
                            )),
                            14,
                            Map.of(new Corner.Cell(1, 0), CellValue.backward, new Corner.Cell(1, 1), CellValue.forward)
                    ),
                    /*
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    +-+-+-+-+
                    | | | | |
                    1-2-3-+-+
                    | | | | |
                    +-+-+-+-+
                     */
                    Arguments.of(
                            new Board(4, 4, List.of(
                                    new Corner(3, 0, 1),
                                    new Corner(3, 1, 2),
                                    new Corner(3, 2, 3)
                            )),
                            14,
                            Map.of(new Corner.Cell(2, 2), CellValue.forward, new Corner.Cell(3, 2), CellValue.backward)
                    )
            );
        }

        @ParameterizedTest
        @MethodSource(value = "differentBoard")
        public void testAllBoards(Board board, int emptyCount, Map<Corner.Cell, CellValue> cellValueMap) {
            edgedOneThreePairStep.execute(board);

            cellValueMap.forEach((key, value) -> Assertions.assertEquals(value, board.getCellValue(key.getX(), key.getY())));
            Assertions.assertEquals(emptyCount, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        }
    }
}
