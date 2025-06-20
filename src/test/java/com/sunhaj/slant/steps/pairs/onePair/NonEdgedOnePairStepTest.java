package com.sunhaj.slant.steps.pairs.onePair;

import com.sunhaj.slant.config.SlantTestConfiguration;
import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SlantTestConfiguration.class)
public class NonEdgedOnePairStepTest {

    @Autowired
    private NonEdgedOnePairStep nonEdgedOnePairStep;

    @Nested
    class PositiveTests {

        @Test
        public void oneWithoutTwosOnRight() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-1-1-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(4, 4, List.of(
                    new Corner(2, 1, 1),
                    new Corner(2, 2, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 0));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 2));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 2));
        }

        @Test
        public void oneWithoutTwosOnBottom() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(4, 4, List.of(
                    new Corner(2, 1, 1),
                    new Corner(3, 1, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 1));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(3, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(3, 1));
        }

        @Test
        public void oneWithTwosOnRight() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-1-2-1-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(4, 4, List.of(
                    new Corner(2, 1, 1),
                    new Corner(2, 2, 2),
                    new Corner(2, 3, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 0));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 3));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 3));
        }

        @Test
        public void oneWithTwosOnBottom() {
            /*
            +-+-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-2-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(4, 4, List.of(
                    new Corner(1, 1, 1),
                    new Corner(2, 1, 2),
                    new Corner(3, 1, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 1));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(3, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(3, 1));
        }

        @Test
        public void mixedOnes() {
            /*
            +-+-+-+-+
            | | | | |
            +-1-2-1-+
            | | | | |
            +-2-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(4, 4, List.of(
                    new Corner(1, 1, 1),
                    new Corner(1, 2, 2),
                    new Corner(1, 3, 1),
                    new Corner(2, 1, 2),
                    new Corner(3, 1, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 1));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 0));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(3, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(3, 1));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 3));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 3));
        }

        @Test
        public void startOneIncomingEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-1-+-+-+
            | |\| | |
            +-1-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(4, 4, List.of(
                    new Corner(1, 1, 1),
                    new Corner(2, 1, 1)
            ));
            board.setCell(1, 1, CellValue.backward);

            nonEdgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 1));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 1));
        }

        @Test
        public void endOneIncomingEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-1-+-+-+
            |\| | | |
            +-1-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(4, 4, List.of(
                    new Corner(1, 1, 1),
                    new Corner(2, 1, 1)
            ));
            board.setCell(1, 0, CellValue.backward);

            nonEdgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 1));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 1));
        }
    }

    @Nested
    class EdgedOnesTest {

        @Test
        public void oneOnTopEdge() {
            /*
            +-1-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(0, 1, 1),
                    new Corner(1, 1, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void oneOnRightEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-1-1
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(1, 3, 1),
                    new Corner(1, 4, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void oneOnBottomEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-1-+-+
            | | | | |
            +-+-1-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(4, 2, 1),
                    new Corner(3, 2, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void oneOnLeftEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            1-1-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(3, 0, 1),
                    new Corner(3, 1, 1)
            ));

            nonEdgedOnePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }
    }
}
