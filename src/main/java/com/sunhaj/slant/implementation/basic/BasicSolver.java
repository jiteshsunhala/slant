package com.sunhaj.slant.implementation.basic;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.solver.SlantSolver;
import org.springframework.stereotype.Service;

@Service
public class BasicSolver implements SlantSolver {

    @Override
    public void solve(Board board) {
        board.solvePairOfOnes();
    }
}
