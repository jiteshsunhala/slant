package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import org.springframework.stereotype.Service;

@Service
public class OnePairStep extends Step {

    @Override
    public boolean execute(Board board) {
        return executeNext(board);
    }
}
