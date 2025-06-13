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

            Assertions.assertEquals(CellValue.right, board.getCellValue(0, 1));
            Assertions.assertEquals(CellValue.left, board.getCellValue(1, 1));
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

            Assertions.assertEquals(CellValue.right, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.left, board.getCellValue(1, 0));
        }
    }

    @Nested
    public class OneOnTopEdge {

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

            Assertions.assertEquals(CellValue.left, board.getCellValue(1, 0));
            Assertions.assertEquals(CellValue.right, board.getCellValue(1, 1));
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

            Assertions.assertEquals(CellValue.left, board.getCellValue(2, 0));
            Assertions.assertEquals(CellValue.right, board.getCellValue(2, 1));
        }
    }
}
