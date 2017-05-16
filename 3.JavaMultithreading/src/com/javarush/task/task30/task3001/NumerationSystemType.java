package com.javarush.task.task30.task3001;

public enum NumerationSystemType implements NumerationSystem {
    _16,
    _12,
    _10,
    _9,
    _8,
    _7,
    _6,
    _5,
    _4,
    _3,
    _2;

    @Override
    public int getNumerationSystemIntValue() {
        return Integer.parseInt(this.name().substring(1));
    }
}
