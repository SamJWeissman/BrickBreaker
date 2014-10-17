package com.games.math;

/**
 * Created by SJWeissman on 2/7/14.
 */
public class Point
{
    private int _x;
    private int _y;
    public Point(int x, int y){
        _x = x;
        _y = y;
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }
}
