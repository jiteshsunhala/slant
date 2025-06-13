package com.sunhaj.slant.simulator;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;
import com.sunhaj.slant.solver.SlantSolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlantStarter implements CommandLineRunner {

    private final SlantSolver slantSolver;

    public SlantStarter(SlantSolver slantSolver) {
        this.slantSolver = slantSolver;
    }

    @Override
    public void run(String... args) {
        Board board = getBoard();
        board.print();
        slantSolver.solve(board);
        board.print();
    }

    private Board getBoard() {
        return new Board(10, 10, List.of(
                new Corner(0, 8, 1),
                new Corner(1, 1, 3),
                new Corner(1, 2, 1),
                new Corner(1, 5, 3),
                new Corner(1, 6, 2),
                new Corner(1, 9, 1),
                new Corner(2, 1, 2),
                new Corner(2, 2, 2),
                new Corner(2, 5, 1),
                new Corner(2, 7, 2),
                new Corner(2, 8, 2),
                new Corner(2, 9, 1),
                new Corner(3, 0, 1),
                new Corner(3, 3, 2),
                new Corner(3, 4, 3),
                new Corner(3, 5, 2),
                new Corner(3, 6, 3),
                new Corner(3, 7, 1),
                new Corner(3, 8, 1),
                new Corner(4, 1, 2),
                new Corner(4, 2, 2),
                new Corner(4, 7, 2),
                new Corner(4, 9, 2),
                new Corner(4, 10, 1),
                new Corner(5, 0, 1),
                new Corner(5, 1, 2),
                new Corner(5, 2, 1),
                new Corner(5, 3, 3),
                new Corner(5, 4, 1),
                new Corner(5, 6, 1),
                new Corner(5, 7, 2),
                new Corner(5, 8, 2),
                new Corner(5, 10, 1),
                new Corner(6, 1, 2),
                new Corner(6, 2, 3),
                new Corner(6, 3, 1),
                new Corner(6, 5, 1),
                new Corner(6, 6, 3),
                new Corner(6, 10, 2),
                new Corner(7, 3, 2),
                new Corner(7, 4, 3),
                new Corner(7, 5, 1),
                new Corner(7, 7, 2),
                new Corner(7, 8, 2),
                new Corner(8, 1, 3),
                new Corner(8, 4, 1),
                new Corner(8, 6, 2),
                new Corner(8, 7, 2),
                new Corner(8, 9, 1),
                new Corner(8, 10, 1),
                new Corner(9, 0, 1),
                new Corner(9, 2, 2),
                new Corner(9, 4, 1),
                new Corner(9, 5, 1),
                new Corner(9, 7, 1),
                new Corner(9, 9, 1),
                new Corner(10, 1, 1),
                new Corner(10, 2, 1),
                new Corner(10, 3, 1)
        ));
    }
}
