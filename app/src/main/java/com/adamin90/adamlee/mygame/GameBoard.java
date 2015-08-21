package com.adamin90.adamlee.mygame;

import com.adamin90.adamlee.mygame.data.Level;
import com.adamin90.adamlee.mygame.util.Action1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Administrator on 2015/8/21.
 */
public class GameBoard {
    final int a;
    final int b;
    GameBoardCell[][] c;
    private final PMRandom d;

    enum Edge {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT;

        static final Edge[] e;

        static {
            e = values();
        }

        Edge a() {
            return a(ordinal() + 1);
        }

        Edge b() {
            return a(ordinal() + 2);
        }

        Edge a(Edge edge) {
            return a((ordinal() - edge.ordinal()) + e.length);
        }

        static Edge a(int i) {
            return e[i % 4];
        }
    }

    GameBoard(Level level) {
        this.d = PMRandom.getInstance();
        this.a = level.width;
        this.b = level.height;
        this.c = (GameBoardCell[][]) Array.newInstance(GameBoardCell.class, new int[]{this.a, this.b});
        for (int i = 0; i < this.a; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                Set hashSet = new HashSet();
                List list = (List) ((List) level.tiles.get(i2)).get(i);
                if (((Integer) list.get(0)).intValue() > 0) {
                    hashSet.add(Edge.TOP);
                }
                if (((Integer) list.get(1)).intValue() > 0) {
                    hashSet.add(Edge.RIGHT);
                }
                if (((Integer) list.get(2)).intValue() > 0) {
                    hashSet.add(Edge.BOTTOM);
                }
                if (((Integer) list.get(3)).intValue() > 0) {
                    hashSet.add(Edge.LEFT);
                }
                this.c[i][i2] = new GameBoardCell(i, i2, this, hashSet, Edge.a(((Integer) list.get(4)).intValue()));
            }
        }
    }

    GameBoard(int i, int i2, float f) {
        this.d = PMRandom.getInstance();
        this.a = i;
        this.b = i2;
        this.c = (GameBoardCell[][]) Array.newInstance(GameBoardCell.class, new int[]{i, i2});
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                this.c[i3][i4] = new GameBoardCell(i3, i4, this);
            }
        }
        if (((double) this.d.b()) > 0.9d || i == i2) {
            a(f);
        } else {
            b(f);
        }
    }

    boolean a() {
        for (int i = 0; i < this.a; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                if (!this.c[i][i2].c()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void a(final float f) {
//        a(GameBoard$$Lambda$1.a(this, ((double) this.d.b()) > 0.7d, f));
        a(new Action1<GameBoardCell>() {
            @Override
            public void call(GameBoardCell gameBoardCell) {
                a(((double) d.b())>0.7d,f,gameBoardCell);
            }
        });
    }

    private /* synthetic */ void a(boolean z, float f, GameBoardCell gameBoardCell) {
        boolean z2 = true;
        if (((double) gameBoardCell.a) <= Math.ceil((double) (((float) this.a) - 1.0f)) / 2.0d) {
            if (((double) gameBoardCell.b) <= Math.ceil((double) (((float) this.b) - 1.0f)) / 2.0d || !z) {
                GameBoardCell gameBoardCell2 = this.c[(this.a - 1) - gameBoardCell.a][gameBoardCell.b];
                GameBoardCell gameBoardCell3 = this.c[gameBoardCell.a][(this.b - 1) - gameBoardCell.b];
                GameBoardCell gameBoardCell4 = this.c[(this.a - 1) - gameBoardCell.a][(this.b - 1) - gameBoardCell.b];
                if (!gameBoardCell.e.contains(Edge.TOP)) {
                    boolean z3 = this.d.b() > f;
                    gameBoardCell.a(Edge.TOP, z3);
                    gameBoardCell2.a(Edge.TOP, z3);
                    if (z) {
                        gameBoardCell3.a(Edge.BOTTOM, z3);
                        gameBoardCell4.a(Edge.BOTTOM, z3);
                    }
                }
                if (!gameBoardCell.e.contains(Edge.RIGHT)) {
                    if (this.d.b() <= f) {
                        z2 = false;
                    }
                    gameBoardCell.a(Edge.RIGHT, z2);
                    gameBoardCell2.a(Edge.LEFT, z2);
                    if (z) {
                        gameBoardCell3.a(Edge.RIGHT, z2);
                        gameBoardCell4.a(Edge.LEFT, z2);
                    }
                }
            }
        }
    }

    private void b(final float f) {
//        a(GameBoard$$Lambda$2.a(this, f));
        a(new Action1<GameBoardCell>() {
            @Override
            public void call(GameBoardCell gameBoardCell) {
                a(f,gameBoardCell);
            }
        });
        b();
        c();
    }

    private /* synthetic */ void a(float f, GameBoardCell gameBoardCell) {
        boolean z = true;
        if (!gameBoardCell.e.contains(Edge.TOP)) {
            gameBoardCell.a(Edge.TOP, this.d.b() > f);
        }
        if (!gameBoardCell.e.contains(Edge.RIGHT)) {
            Edge edge = Edge.RIGHT;
            if (this.d.b() <= f) {
                z = false;
            }
            gameBoardCell.a(edge, z);
        }
    }

    private void b() {
//        a(GameBoard$$Lambda$3.a(this));
        a(new Action1<GameBoardCell>() {
            @Override
            public void call(GameBoardCell gameBoardCell) {
                b(gameBoardCell);
            }
        });
    }

    private /* synthetic */ void b(GameBoardCell gameBoardCell) {
        if (gameBoardCell.b() <= 1) {
            for (Edge edge : Edge.values()) {
                GameBoardCell b = gameBoardCell.b(edge);
                if (!gameBoardCell.d.contains(edge) && b != null && b.b() == 1 && this.d.b() > 0.85f) {
                    b.d.add(edge.b());
                    gameBoardCell.d.add(edge);
                }
            }
        }
    }

    private void c() {
//        a(GameBoard$$Lambda$4.a(this));
        a(new Action1<GameBoardCell>() {
            @Override
            public void call(GameBoardCell gameBoardCell) {
              a(gameBoardCell);
            }
        });
    }

    private /* synthetic */ void a(GameBoardCell gameBoardCell) {
        if (gameBoardCell.b() >= 4) {
            for (Edge edge : Edge.values()) {
                if (gameBoardCell.b(edge) != null && this.d.b() > 0.95f) {
                    gameBoardCell.a(edge, false);
                }
            }
        }
    }

    private void a(Action1<GameBoardCell> action1) {
        for (int i = 0; i < this.a; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                action1.call(this.c[i][i2]);
            }
        }
    }
}