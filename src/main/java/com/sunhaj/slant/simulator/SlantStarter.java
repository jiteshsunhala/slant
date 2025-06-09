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
        slantSolver.solve(board);

        board.print();
    }

    private Board getBoard() {
        return new Board(5, 5, List.of(
                new Corner(1, 1, 1),
                new Corner(1, 2, 1),
                new Corner(1, 3, 2),
                new Corner(1, 5, 1),
                new Corner(2, 0, 1),
                new Corner(2, 1, 2),
                new Corner(2, 4, 1),
                new Corner(2, 5, 1),
                new Corner(3, 2, 1),
                new Corner(3, 5, 0),
                new Corner(4, 1, 2),
                new Corner(4, 2, 1),
                new Corner(4, 3, 2),
                new Corner(4, 4, 1),
                new Corner(5, 4, 1)));
    }
}
