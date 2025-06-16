package com.sunhaj.slant.implementation.basic;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.solver.SlantSolver;
import com.sunhaj.slant.steps.*;
import com.sunhaj.slant.steps.pairs.onePair.EdgedOnePairStep;
import com.sunhaj.slant.steps.pairs.onePair.NonEdgedOnePairStep;
import com.sunhaj.slant.steps.pairs.oneThreePair.EdgedOneThreePairStep;
import com.sunhaj.slant.steps.pairs.threePair.ThreePairStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicSolver implements SlantSolver {

    private final NonEdgedOnePairStep nonEdgedOnePairStep;
    private final EdgedOnePairStep edgedOnePairStep;
    private final ZeroCornerStep zeroCornerStep;
    private final ThreePairStep threePairStep;
    private final DiagonalOnesStep diagonalOnesStep;

    private final EdgedOneThreePairStep edgedOneThreePairStep;

    @Autowired
    public BasicSolver(NonEdgedOnePairStep nonEdgedOnePairStep,
                       ZeroCornerStep zeroCornerStep,
                       ThreePairStep threePairStep,
                       DiagonalOnesStep diagonalOnesStep,
                       EdgedOnePairStep edgedOnePairStep,
                       EdgedOneThreePairStep edgedOneThreePairStep) {
        this.nonEdgedOnePairStep = nonEdgedOnePairStep;
        this.zeroCornerStep = zeroCornerStep;
        this.threePairStep = threePairStep;
        this.diagonalOnesStep = diagonalOnesStep;
        this.edgedOnePairStep = edgedOnePairStep;

        this.edgedOneThreePairStep = edgedOneThreePairStep;
    }

    @Override
    public void solve(Board board) {
        Step nonIterativeSteps = Step.link(nonEdgedOnePairStep, List.of(edgedOnePairStep, zeroCornerStep, threePairStep, diagonalOnesStep));

        Step iterativeSteps = Step.link(edgedOneThreePairStep, List.of());
        nonIterativeSteps.execute(board);
    }
}
