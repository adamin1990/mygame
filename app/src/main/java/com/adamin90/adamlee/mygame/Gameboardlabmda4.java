package com.adamin90.adamlee.mygame;

import com.adamin90.adamlee.mygame.util.Action1;

/**
 * Created by Administrator on 2015/8/21.
 */
public class Gameboardlabmda4 implements Action1 {
    private final GameBoard a;

    private Gameboardlabmda4(GameBoard gameBoard) {
        this.a = gameBoard;
    }

    public static Action1 a(GameBoard gameBoard) {
        return new Gameboardlabmda4(gameBoard);
    }

    public void call(Object obj) {
        this.a.a((GameBoardCell) obj);
    }

}
