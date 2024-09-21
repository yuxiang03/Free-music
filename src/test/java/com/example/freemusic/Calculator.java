package com.example.freemusic;

public class Calculator {
    private static int result = 0;

    public void add(int n) {
        result = result + n;
    }

    public void subtration(int n) {
        result = result - 1;    //bug.正确的应该为  //result=result-n
    }

    public void multiply(int n) {
        result = result * n;
    }

    public void divide(int n) {
        result = result / n;
    }

    public void square(int n) {
        result = n * n;
    }

    public void clear() {    //将结果清零
        result = 0;
    }

    public Object getResult() {
        return result;
    }
}
