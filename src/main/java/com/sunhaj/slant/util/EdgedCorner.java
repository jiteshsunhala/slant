package com.sunhaj.slant.util;

import com.sunhaj.slant.model.Board;
import com.sunhaj.slant.model.Corner;

import java.util.function.BiPredicate;

public class EdgedCorner implements BiPredicate<Board, Corner> {

    @Override
    public boolean test(Board board, Corner corner) {
        return board.isEdgedCorner(corner);
    }
}
