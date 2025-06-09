package com.sunhaj.slant.simulator;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.solver.SlantSolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SlantStarter implements CommandLineRunner {

    private final SlantSolver slantSolver;

    public SlantStarter(SlantSolver slantSolver) {
        this.slantSolver = slantSolver;
    }

    @Override
    public void run(String... args) throws Exception {
        if(args.length != 1) {
            throw new RuntimeException("Only one argument should be provided");
        }

        Board board = getBoard(args[0]);
        slantSolver.solve(board);

        board.print();
    }

    private Board getBoard(String inputFile) {
        Scanner scanner = new Scanner(System.in);

        int r = scanner.nextInt();
        int c = scanner.nextInt();


    }
}
