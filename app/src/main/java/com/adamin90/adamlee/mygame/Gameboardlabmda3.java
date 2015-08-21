package com.adamin90.adamlee.mygame;

import com.adamin90.adamlee.mygame.util.Action1;

/**
 * Created by Administrator on 2015/8/21.
 */
public class Gameboardlabmda3 implements Action1 {
    private final GameBoard a;

    private Gameboardlabmda3(GameBoard gameBoard) {
        this.a = gameBoard;
    }

    public static Action1 a(GameBoard gameBoard) {
        return new Gameboardlabmda3(gameBoard);
    }

    public void call(Object obj) {
        this.a.b((GameBoardCell) obj);
    }

}
