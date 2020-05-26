package com.example.mz;

import android.content.Context;

import java.io.IOException;

public class Counter {

    private short count;
    public Counter() {
        count = 0;
    }

    public void addOne() {
        count++;
    }

    public void subOne() {
        count--;
    }

    public short getCount() {
        return count;
    }

    public void setCount(short newCount) {
        count = newCount;
    }
}
