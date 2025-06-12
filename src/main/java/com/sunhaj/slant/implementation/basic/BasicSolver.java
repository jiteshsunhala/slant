package com.sunhaj.slant.implementation.basic;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.solver.SlantSolver;
import com.sunhaj.slant.steps.OnePairStep;
import com.sunhaj.slant.steps.Step;
import com.sunhaj.slant.steps.ThreePairStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicSolver implements SlantSolver {

    private final OnePairStep onePairStep;
    private final ThreePairStep threePairStep;

    @Autowired
    public BasicSolver(OnePairStep onePairStep, ThreePairStep threePairStep) {
        this.onePairStep = onePairStep;
        this.threePairStep = threePairStep;
    }

    @Override
    public void solve(Board board) {
        Step nonIterativeSteps = Step.link(onePairStep, List.of(threePairStep));
        nonIterativeSteps.execute(board);
    }
}
