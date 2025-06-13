package com.sunhaj.slant.steps;

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
public class ZeroCornerStepTests {

    @Autowired
    private ZeroCornerStep zeroCornerStep;
    
    @Test
    public void zeroOnTwoCellCorner() {
        /*
        +-0-+-+
        | | | |
        +-+-+-+
        | | | |
        +-+-+-+
        | | | |
        +-+-+-+
         */

        Board board = new Board(3, 3, List.of(
                new Corner(0, 1, 0)
        ));

        zeroCornerStep.execute(board);

        Assertions.assertEquals(CellValue.left, board.getCellValue(0, 0));
        Assertions.assertEquals(CellValue.right, board.getCellValue(0, 1));
    }

    @Test
    public void zeroOnOneCellCorner() {
        /*
        0-+-+-+
        | | | |
        +-+-+-+
        | | | |
        +-+-+-+
        | | | |
        +-+-+-+
         */

        Board board = new Board(3, 3, List.of(
                new Corner(0, 0, 0)
        ));

        zeroCornerStep.execute(board);
        Assertions.assertEquals(CellValue.right, board.getCellValue(0, 0));
    }
}
