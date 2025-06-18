package com.sunhaj.slant.steps.pairs.onePair;

import com.sunhaj.slant.config.SlantTestConfiguration;
import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SlantTestConfiguration.class)
public class DiagonalOnesStepTest {

    @Autowired
    private DiagonalOnesStep diagonalOnesStep;

    @Test
    public void bottomRightOne() {
        /*
        1-+-+-+
        | | | |
        +-1-+-+
        | | | |
        +-+-1-+
        | | | |
        +-+-+-+
         */

        Board board = new Board(3, 3, List.of(
                new Corner(0, 0, 1),
                new Corner(1, 1, 1),
                new Corner(2, 2, 1)
        ));

        diagonalOnesStep.execute(board);
        Assertions.assertEquals(CellValue.forward, board.getCellValue(1, 1));
    }

    @Test
    public void bottomLeftOne() {
        /*
        1-+-+-+
        | | | |
        +-+-1-+
        | | | |
        +-1-+-+
        | | | |
        +-+-+-+
         */

        Board board = new Board(3, 3, List.of(
                new Corner(0, 0, 1),
                new Corner(1, 2, 1),
                new Corner(2, 1, 1)
        ));

        diagonalOnesStep.execute(board);
        Assertions.assertEquals(CellValue.backward, board.getCellValue(1, 1));
    }

    @Test
    public void edgedOnes() {
        /*
        1-+-+-+
        | | | |
        +-1-+-+
        | | | |
        1-+-+-+
        | | | |
        +-+-+-+
         */

        Board board = new Board(3, 3, List.of(
                new Corner(0, 0, 1),
                new Corner(1, 1, 1),
                new Corner(2, 0, 1)
        ));

        diagonalOnesStep.execute(board);
        Assertions.assertTrue(board.getAllCellValues().stream().allMatch(CellValue.none::equals));
    }
}
