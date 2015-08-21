package com.adamin90.adamlee.mygame;

import com.adamin90.adamlee.mygame.util.Action1;

/**
 * Created by Administrator on 2015/8/21.
 */
public class Gameboardlabmda1 implements Action1 {

    private final GameBoard a;
    private final boolean b;
    private final float c;

    private Gameboardlabmda1(GameBoard gameBoard, boolean z, float f) {
        this.a = gameBoard;
        this.b = z;
        this.c = f;
    }

    public static Action1 a(GameBoard gameBoard, boolean z, float f) {
        return new Gameboardlabmda1(gameBoard, z, f);
    }

    public void call(Object obj) {
        this.a.a(this.b, this.c, (GameBoardCell) obj);
    }


}
