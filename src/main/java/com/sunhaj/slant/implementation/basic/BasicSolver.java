package com.sunhaj.slant.implementation.basic;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.solver.SlantSolver;
import com.sunhaj.slant.steps.DiagonalOnesStep;
import com.sunhaj.slant.steps.NonEdgedOnePairStep;
import com.sunhaj.slant.steps.Step;
import com.sunhaj.slant.steps.ThreePairStep;
import com.sunhaj.slant.steps.ZeroCornerStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicSolver implements SlantSolver {

    private final NonEdgedOnePairStep nonEdgedOnePairStep;
    private final ZeroCornerStep zeroCornerStep;
    private final ThreePairStep threePairStep;
    private final DiagonalOnesStep diagonalOnesStep;

    @Autowired
    public BasicSolver(NonEdgedOnePairStep nonEdgedOnePairStep, ZeroCornerStep zeroCornerStep, ThreePairStep threePairStep, DiagonalOnesStep diagonalOnesStep) {
        this.nonEdgedOnePairStep = nonEdgedOnePairStep;
        this.zeroCornerStep = zeroCornerStep;
        this.threePairStep = threePairStep;
        this.diagonalOnesStep = diagonalOnesStep;
    }

    @Override
    public void solve(Board board) {
        Step nonIterativeSteps = Step.link(nonEdgedOnePairStep, List.of(zeroCornerStep, threePairStep, diagonalOnesStep));
        nonIterativeSteps.execute(board);

    }
}
