package com.sunhaj.slant.steps.pairs;

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
public class OnePairStepTests {

    @Autowired
    private OnePairStep onePairStep;

    @Nested
    public class OneOnRightEdge {

        @Test
        public void twoOnesOnEdge() {
            /*
            +-+-+-+
            | | | |
            +-+-+-1
            | | | |
            +-+-+-1
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 3, 1),
                    new Corner(2, 3, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void oneWithoutTwos() {
            /*
            +-+-+-+
            | | | |
            +-+-1-1
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 2, 1),
                    new Corner(1, 3, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 1));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 1));
        }

        @Test
        public void oneWithTwos() {
            /*
            +-+-+-+
            | | | |
            +-1-2-1
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 1),
                    new Corner(1, 2, 2),
                    new Corner(1, 3, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 0));
        }
    }

    @Nested
    public class OneOnTopEdge {

        @Test
        public void twoOnesOnEdge() {
            /*
            +-1-1-+
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(0, 1, 1),
                    new Corner(0, 2, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void oneWithoutTwos() {
            /*
            +-1-+-+
            | | | |
            +-1-+-+
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(0, 1, 1),
                    new Corner(1, 1, 1)
            ));

            onePairStep.execute(board);
            board.print();

            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 1));
        }

        @Test
        public void oneWithTwos() {
            /*
            +-1-+-+
            | | | |
            +-2-+-+
            | | | |
            +-1-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(0, 1, 1),
                    new Corner(1, 1, 2),
                    new Corner(2, 1, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 1));
        }
    }

    @Nested
    public class OneOnLeftEdge {

        @Test
        public void twoOnesOnEdge() {
            /*
            +-+-+-+
            | | | |
            1-+-+-+
            | | | |
            1-+-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 0, 1),
                    new Corner(2, 0, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void oneWithoutTwos() {
            /*
            +-+-+-+
            | | | |
            1-1-+-+
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 0, 1),
                    new Corner(1, 1, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 1));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 1));
        }

        @Test
        public void oneWithTwos() {
            /*
            +-+-+-+
            | | | |
            1-2-1-+
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 0, 1),
                    new Corner(1, 1, 2),
                    new Corner(1, 2, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 2));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 2));
        }
    }

    @Nested
    public class OneOnBottomEdge {

        @Test
        public void twoOnesOnEdge() {
            /*
            +-+-+-+
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
            | | | |
            +-1-1-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(3, 1, 1),
                    new Corner(3, 2, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void oneWithoutTwos() {
            /*
            +-+-+-+
            | | | |
            +-+-+-+
            | | | |
            +-1-+-+
            | | | |
            +-1-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(2, 1, 1),
                    new Corner(3, 1, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 1));
        }

        @Test
        public void oneWithTwos() {
            /*
            +-+-+-+
            | | | |
            +-1-+-+
            | | | |
            +-2-+-+
            | | | |
            +-1-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 1),
                    new Corner(2, 1, 2),
                    new Corner(3, 1, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 1));
        }
    }

    @Nested
    public class OnePairBetweenBoard {

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

            onePairStep.execute(board);

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

            onePairStep.execute(board);

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

            onePairStep.execute(board);

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

            onePairStep.execute(board);

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

            onePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 1));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1,0));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(3, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(3, 1));

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0,3));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1,3));
        }
    }

    @Nested
    public class NegativeTests {

        @Test
        public void threeBetweenVerticalOnes() {
            /*
            +-+-+-+
            | | | |
            +-1-+-+
            | | | |
            +-3-+-+
            | | | |
            +-1-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 1),
                    new Corner(2, 1, 3),
                    new Corner(3, 1, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void emptyBetweenVerticalOnes() {
            /*
            +-+-+-+
            | | | |
            +-1-+-+
            | | | |
            +-+-+-+
            | | | |
            +-1-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 1),
                    new Corner(3, 1, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void threeBetweenHorizontalOnes() {
            /*
            +-+-+-+-+
            | | | | |
            +-1-3-1-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 1),
                    new Corner(1, 2, 3),
                    new Corner(1, 3, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }

        @Test
        public void emptyBetweenHorizontalOnes() {
            /*
            +-+-+-+-+
            | | | | |
            +-1-+-1-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */
            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 1),
                    new Corner(1, 3, 1)
            ));

            onePairStep.execute(board);

            Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
        }
    }
}
