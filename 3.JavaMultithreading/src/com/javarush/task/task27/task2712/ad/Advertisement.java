package com.javarush.task.task27.task2712.ad;

/**
 * Created by Rumata on 04.05.2017.
 */
public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content; //видео
        this.name = name; //название
        this.initialAmount = initialAmount; //начальная сумма, стоимость рекламы в копейках
        this.hits = hits; //количество оплаченных показов
        this.duration = duration; // продолжительность в секундах
        this.amountPerOneDisplaying = initialAmount/hits; //стоимость одного показа рекламного объявления в копейках
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate() {
        if (hits <= 0) throw new UnsupportedOperationException();
        else hits--;
    }

    @Override
    public String toString() {
        return name + " is displaying... " + getAmountPerOneDisplaying() + ", " + getAmountPerOneDisplaying()/duration*1000;
    }
}
