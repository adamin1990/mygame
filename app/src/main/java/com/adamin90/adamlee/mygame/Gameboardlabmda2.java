package com.adamin90.adamlee.mygame;

import com.adamin90.adamlee.mygame.util.Action1;

/**
 * Created by Administrator on 2015/8/21.
 */
public class Gameboardlabmda2 implements Action1 {
    private final GameBoard a;
    private final float b;

    private Gameboardlabmda2(GameBoard gameBoard, float f) {
        this.a = gameBoard;
        this.b = f;
    }

    public static Action1 a(GameBoard gameBoard, float f) {
        return new Gameboardlabmda2(gameBoard, f);
    }

    public void call(Object obj) {
        this.a.a(this.b, (GameBoardCell) obj);
    }

}
