package com.sunhaj.slant.steps;

import com.sunhaj.slant.model.Board;
import lombok.Data;

import java.util.List;

@Data
public abstract class Step {
    private Step next;

    public static Step link(Step first, List<Step> nextSteps) {
        Step head = first;
        for(Step next: nextSteps) {
            head.setNext(next);
            head = next;
        }

        return first;
    }

    abstract public boolean execute(Board board);

    public boolean executeNext(Board board) {
        if(next == null) {
            return true;
        }
        return next.execute(board);
    }
}
