package com.adamin90.adamlee.mygame;

//import com.google.android.gms.common.api.CommonStatusCodes;
//import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/8/21.
 */
class GameBoardCell {
    final int a;
    final int b;
    GameBoard.Edge c;
    final Set<GameBoard.Edge> d;
    final Set<GameBoard.Edge> e;
    private final GameBoard f;

    /* renamed from: com.balysv.loop.GameBoardCell.1 */
    /* synthetic */ static class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[GameBoard.Edge.values().length];
            try {
                a[GameBoard.Edge.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[GameBoard.Edge.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[GameBoard.Edge.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[GameBoard.Edge.LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    GameBoardCell(int i, int i2, GameBoard gameBoard) {
        this.d = new HashSet();
        this.e = new HashSet();
        this.a = i;
        this.b = i2;
        this.f = gameBoard;
        if (i2 == 0) {
            this.e.add(GameBoard.Edge.TOP);
        }
        if (i == gameBoard.a - 1) {
            this.e.add(GameBoard.Edge.RIGHT);
        }
        if (i2 == gameBoard.b - 1) {
            this.e.add(GameBoard.Edge.BOTTOM);
        }
        if (i == 0) {
            this.e.add(GameBoard.Edge.LEFT);
        }
        this.c = GameBoard.Edge.a((int) (PMRandom.getInstance().a() % 4));
    }

    GameBoardCell(int i, int i2, GameBoard gameBoard, Set<GameBoard.Edge> set, GameBoard.Edge edge) {
        this.d = new HashSet();
        this.e = new HashSet();
        this.a = i;
        this.b = i2;
        this.f = gameBoard;
        this.d.addAll(set);
        this.e.addAll(Arrays.asList(GameBoard.Edge.values()));
        this.c = edge;
    }

    void a() {
        this.c = this.c.a();
    }

    int b() {
        return this.d.size();
    }

    void a(GameBoard.Edge edge, boolean z) {
        GameBoardCell b = b(edge);
        GameBoard.Edge b2 = edge.b();
        if (!this.e.contains(edge) && !b.e.contains(b2)) {
            b(edge, z);
            this.e.add(edge);
            b.b(b2, z);
            b.e.add(b2);
        }
    }

    boolean a(GameBoard.Edge edge) {
        return this.d.contains(edge.a(this.c));
    }

    GameBoardCell b(GameBoard.Edge edge) {
        int i = this.a;
        int i2 = this.b;
        switch (AnonymousClass1.a[edge.ordinal()]) {
            case 1 /*1*/:
                i2--;
                break;
            case 2 /*2*/:
                i++;
                break;
            case 3 /*3*/:
                i2++;
                break;
            case 4 /*4*/:
                i--;
                break;
        }
        if (i < 0 || i >= this.f.a || i2 < 0 || i2 >= this.f.b) {
            return null;
        }
        return this.f.c[i][i2];
    }

    boolean c() {
        if (this.b > 0) {
            if (a(GameBoard.Edge.TOP) != b(GameBoard.Edge.TOP).a(GameBoard.Edge.BOTTOM)) {
                return false;
            }
        } else if (a(GameBoard.Edge.TOP)) {
            return false;
        }
        if (this.a < this.f.a - 1) {
            if (a(GameBoard.Edge.RIGHT) != b(GameBoard.Edge.RIGHT).a(GameBoard.Edge.LEFT)) {
                return false;
            }
        } else if (a(GameBoard.Edge.RIGHT)) {
            return false;
        }
        if (this.b < this.f.b - 1) {
            if (a(GameBoard.Edge.BOTTOM) != b(GameBoard.Edge.BOTTOM).a(GameBoard.Edge.TOP)) {
                return false;
            }
        } else if (a(GameBoard.Edge.BOTTOM)) {
            return false;
        }
        if (this.a > 0) {
            if (a(GameBoard.Edge.LEFT) != b(GameBoard.Edge.LEFT).a(GameBoard.Edge.RIGHT)) {
                return false;
            }
        } else if (a(GameBoard.Edge.LEFT)) {
            return false;
        }
        return true;
    }

    private void b(GameBoard.Edge edge, boolean z) {
        if (z) {
            this.d.add(edge);
        } else {
            this.d.remove(edge);
        }
    }

}