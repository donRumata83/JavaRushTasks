package com.javarush.task.task27.task2712.ad;

/**
 * Created by Rumata on 04.05.2017.
 */
public class Advertisement {
    private Object content; //видео
    private String name; //название
    private long initialAmount; //начальная сумма, стоимость рекламы в копейках
    private int hits; //количество оплаченных показов
    private int duration; // продолжительность в секундах
    private long amountPerOneDisplaying; //стоимость одного показа рекламного объявления в копейках

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount/hits;
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

    public int getHits() {
        return hits;
    }
}
