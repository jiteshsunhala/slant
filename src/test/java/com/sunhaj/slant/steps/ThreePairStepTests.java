package com.sunhaj.slant.steps;

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
public class ThreePairStepTests {

    @Autowired
    private ThreePairStep threePairStep;

    @Nested
    public class ThreesOnRight {

        @Test
        public void withoutTwos() {
            /*
            +-+-+-+
            | | | |
            +-3-3-+
            | | | |
            +-+-+-+
            | | | |
            +-+-+-+
             */

            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 3),
                    new Corner(1, 2, 3)
            ));

            threePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 0));

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 2));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 2));
        }

        @Test
        public void withTwos() {
            /*
            +-+-+-+-+
            | | | | |
            +-3-2-3-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(3, 4, List.of(
                    new Corner(1, 1, 3),
                    new Corner(1, 2, 2),
                    new Corner(1, 3, 3)
            ));

            threePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 0));

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 3));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 3));
        }
    }

    @Nested
    public class ThreesOnBottom {

        @Test
        public void withoutTwos() {
            /*
            +-+-+-+
            | | | |
            +-3-+-+
            | | | |
            +-3-+-+
            | | | |
            +-+-+-+
             */

            Board board = new Board(3, 3, List.of(
                    new Corner(1, 1, 3),
                    new Corner(2, 1, 3)
            ));

            threePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 1));

            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 1));
        }

        @Test
        public void withTwos() {
            /*
            +-+-+-+-+
            | | | | |
            +-3-+-+-+
            | | | | |
            +-2-+-+-+
            | | | | |
            +-3-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(1, 1, 3),
                    new Corner(2, 1, 2),
                    new Corner(3, 1, 3)
            ));

            threePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 1));

            Assertions.assertEquals(CellValue.forward, board.getCellValue(3, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(3, 1));
        }
    }

    @Nested
    public class NegativeTests {

        @Test
        public void oneInBetween() {
            /*
            +-+-+-+-+
            | | | | |
            +-3-+-+-+
            | | | | |
            +-1-+-+-+
            | | | | |
            +-3-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(1, 1, 3),
                    new Corner(2, 1, 1),
                    new Corner(3, 1, 3)
            ));

            threePairStep.execute(board);

            Assertions.assertEquals(CellValue.none, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.none, board.getCellValue(0, 1));

            Assertions.assertEquals(CellValue.none, board.getCellValue(3, 0));
            Assertions.assertEquals(CellValue.none, board.getCellValue(3, 1));
        }
    }

    @Nested
    public class MixedThrees {

        @Test
        public void mixed() {
            /*
            +-+-+-+-+
            | | | | |
            +-3-3-+-+
            | | | | |
            +-3-+-+-+
            | | | | |
            +-+-+-+-+
            | | | | |
            +-+-+-+-+
             */

            Board board = new Board(4, 4, List.of(
                    new Corner(1, 1, 3),
                    new Corner(1, 2, 3),
                    new Corner(2, 1, 3)
            ));

            threePairStep.execute(board);

            Assertions.assertEquals(CellValue.backward, board.getCellValue(0, 0));
            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 1));

            Assertions.assertEquals(CellValue.forward, board.getCellValue(0, 2));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 2));

            Assertions.assertEquals(CellValue.forward, board.getCellValue(2, 0));
            Assertions.assertEquals(CellValue.backward, board.getCellValue(2, 1));
        }
    }
}
