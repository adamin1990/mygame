package com.adamin90.adamlee.mygame.data;

import java.util.List;

/**
 * Created by adamlee on 2015/8/21.
 */
public final class Level {
    public final int height;
    public final Integer hue;
    public final List<List<List<Integer>>> tiles;
    public final int width;

    public Level(int i, int i2, int i3, List<List<List<Integer>>> list) {
        this.width = i;
        this.height = i2;
        this.hue = Integer.valueOf(i3);
        this.tiles = list;
    }


}
