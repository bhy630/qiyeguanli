package com.donkee.house.gen;

public class Point<T> {
    private T x ;
    private T y ;
    public void setX(T x){//作为参数
        this.x = x ;
    }
    public void setY(T y){
        this.y = y ;
    }
    public T getX(){//作为返回值
        return this.x ;
    }
    public T getY(){
        return this.y ;
    }
}
