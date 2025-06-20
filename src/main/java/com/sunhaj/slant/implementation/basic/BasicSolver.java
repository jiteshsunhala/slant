package com.sunhaj.slant.implementation.basic;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.solver.SlantSolver;
import com.sunhaj.slant.steps.*;
import com.sunhaj.slant.steps.pairs.onePair.DiagonalOnesStep;
import com.sunhaj.slant.steps.pairs.onePair.EdgedOnePairStep;
import com.sunhaj.slant.steps.pairs.onePair.NonEdgedOnePairStep;
import com.sunhaj.slant.steps.pairs.oneThreePair.EdgedOneThreePairStep;
import com.sunhaj.slant.steps.pairs.oneThreePair.NonEdgedOneThreePairStep;
import com.sunhaj.slant.steps.pairs.oneThreePair.NonEdgedThreeOnePairStep;
import com.sunhaj.slant.steps.pairs.oneTwoPair.EdgedOneTwoPairStep;
import com.sunhaj.slant.steps.pairs.threePair.ThreePairStep;
import com.sunhaj.slant.steps.pairs.twoPair.SameRowTwoPairStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicSolver implements SlantSolver {

    private final NonEdgedOnePairStep nonEdgedOnePairStep;
    private final EdgedOnePairStep edgedOnePairStep;
    private final ZeroCornerStep zeroCornerStep;
    private final ThreePairStep threePairStep;
    private final DiagonalOnesStep diagonalOnesStep;

    private final EdgedOneThreePairStep edgedOneThreePairStep;
    private final NonEdgedOneThreePairStep nonEdgedOneThreePairStep;
    private final NonEdgedThreeOnePairStep nonEdgedThreeOnePairStep;
    private final FillRemainingStep fillRemainingStep;
    private final EdgedOneTwoPairStep edgedOneTwoPairStep;
    private final SameRowTwoPairStep sameRowTwoPairStep;

    @Autowired
    public BasicSolver(NonEdgedOnePairStep nonEdgedOnePairStep,
                       ZeroCornerStep zeroCornerStep,
                       ThreePairStep threePairStep,
                       DiagonalOnesStep diagonalOnesStep,
                       EdgedOnePairStep edgedOnePairStep,
                       EdgedOneThreePairStep edgedOneThreePairStep,
                       NonEdgedOneThreePairStep nonEdgedOneThreePairStep,
                       NonEdgedThreeOnePairStep nonEdgedThreeOnePairStep,
                       FillRemainingStep fillRemainingStep,
                       EdgedOneTwoPairStep edgedOneTwoPairStep,
                       SameRowTwoPairStep sameRowTwoPairStep) {

        this.nonEdgedOnePairStep = nonEdgedOnePairStep;
        this.zeroCornerStep = zeroCornerStep;
        this.threePairStep = threePairStep;
        this.diagonalOnesStep = diagonalOnesStep;
        this.edgedOnePairStep = edgedOnePairStep;

        this.edgedOneThreePairStep = edgedOneThreePairStep;
        this.nonEdgedOneThreePairStep = nonEdgedOneThreePairStep;
        this.nonEdgedThreeOnePairStep = nonEdgedThreeOnePairStep;
        this.fillRemainingStep = fillRemainingStep;
        this.edgedOneTwoPairStep = edgedOneTwoPairStep;
        this.sameRowTwoPairStep = sameRowTwoPairStep;
    }

    @Override
    public void solve(Board board) {
        Step nonIterativeSteps = Step.link(nonEdgedOnePairStep, List.of(
                edgedOnePairStep,
                zeroCornerStep,
                threePairStep,
                diagonalOnesStep
        ));

        Step iterativeSteps = Step.link(edgedOneThreePairStep, List.of(
                nonEdgedOneThreePairStep,
                nonEdgedThreeOnePairStep,
                fillRemainingStep,
                edgedOneTwoPairStep,
                sameRowTwoPairStep
        ));

        Map<Board, Integer> counts = new HashMap<>();

        nonIterativeSteps.execute(board);
        board.print();
        System.out.println("Starting iterative steps.");
        while(!board.isSolved()) {
            iterativeSteps.execute(board);
            board.print();
            int value = counts.computeIfAbsent(board, b -> 0);
            if(value == 3) {
                break;
            }
            counts.put(board, value + 1);
        }
    }
}
