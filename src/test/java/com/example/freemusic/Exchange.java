package com.example.freemusic;

public class Exchange {
    public char function(int x,int y){
        char t;
        if((x>=90)&&(y>=90)){
            t='A';
        }else{
            if((x+y)>=165){
                t='B';
            }else{
                t='C';
            }
        }
        return t;
    }
    public static void main(String[]args) {
        Exchange ex = new Exchange();
        ex.function(60,90);
    }
}
