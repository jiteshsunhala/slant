package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.CellValue;
import com.sunhaj.slant.model.Corner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillRemainingStep extends Step {

    @Override
    public boolean execute(Board board) {

        board.getAllCorners().stream()
                .filter(corner -> corner.getValue() != null)
                .forEach(corner -> {
            List<Corner.Cell> cells = corner.getAllCells();
            List<CellValue> cellValues = cells.stream().map(board::getCellValue).toList();

            int totalCells = 0, incomingCells = 0, awayCells = 0;

            for(int i=0;i<4;i++) {
                if(CellValue.invalid.equals(cellValues.get(i))) {
                    continue;
                }

                totalCells++;
                if(cells.get(i).getIncoming().equals(cellValues.get(i))) {
                    incomingCells++;
                }
                if(cells.get(i).getAway().equals(cellValues.get(i))) {
                    awayCells++;
                }
            }

            if(incomingCells + awayCells == totalCells) {
                return;
            }

            if(incomingCells == corner.getValue()) {
                for(int i=0;i<4;i++) {
                    if(CellValue.none.equals(cellValues.get(i))) {
                        Corner.Cell cell = cells.get(i);
                        board.setCell(cell.getX(), cell.getY(), cell.getAway());
                    }
                }

                return;
            }

            if(totalCells - corner.getValue() == awayCells) {
                for(int i=0;i<4;i++) {
                    if(CellValue.none.equals(cellValues.get(i))) {
                        Corner.Cell cell = cells.get(i);
                        board.setCell(cell.getX(), cell.getY(), cell.getIncoming());
                    }
                }
            }
        });

        return executeNext(board);
    }
}
