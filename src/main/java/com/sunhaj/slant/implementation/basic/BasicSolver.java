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

    @Autowired
    public BasicSolver(OnePairStep onePairStep) {
        this.onePairStep = onePairStep;
    }

    @Override
    public void solve(Board board) {
        Step nonIterativeSteps = Step.link(onePairStep, List.of());
        nonIterativeSteps.execute(board);
    }
}
