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
public class EdgedOnePairStepTest {

    @Autowired
    private EdgedOnePairStep edgedOnePairStep;

    @Nested
    public class OneWithTwoTests {

        @Test
        public void oneOnTopEdge() {
            /*
            +-1-+-+-+
            | | | | |
            +-2-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(0, 1, 1),
                    new Corner(1, 1, 2),
                    new Corner(2, 1, 1)
            ));

            edgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 1));

            Assertions.assertEquals(14, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        }

        @Test
        public void oneOnRightEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-1-2-1
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(2, 2, 1),
                    new Corner(2, 3, 2),
                    new Corner(2, 4, 1)
            ));

            edgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 1));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 1));

            Assertions.assertEquals(14, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        }

        @Test
        public void oneOnBottomEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-2-+-+-+
            | | | | |
            +-1-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(2, 1, 1),
                    new Corner(3, 1, 2),
                    new Corner(4, 1, 1)
            ));

            edgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 1));

            Assertions.assertEquals(14, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        }

        @Test
        public void oneOnLeftEdge() {
            /*
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            1-2-1-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(2, 0, 1),
                    new Corner(2, 1, 2),
                    new Corner(2, 2, 1)
            ));

            edgedOnePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 2));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 2));

            Assertions.assertEquals(14, board.getAllCellValues().stream().filter(CellValue.none::equals).count());
        }
    }

    @Nested
    public class AllOnesOnEdge {

        @Test
        public void allOnes() {
            /*
            +-+-1-+-+
            | | | | |
            +-+-1-+-+
            | | | | |
            1-1-+-1-1
            | | | | |
            +-+-1-+-+
            | | | | |
            +-+-1-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(0, 2, 1),
                    new Corner(1, 2, 1),
                    new Corner(2, 0, 1),
                    new Corner(2, 1, 1),
                    new Corner(2, 3, 1),
                    new Corner(2, 4, 1),
                    new Corner(3, 2, 1),
                    new Corner(4, 2, 1)
            ));

            edgedOnePairStep.execute(board);
            Assertions.assertEquals(12, board.getAllCellValues().stream().filter(CellValue.none::equals).count());

            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 1));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 2));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 1));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 2));
        }
    }
}
