package com.javarush.task.task27.task2712.ad;


import java.util.ArrayList;
import java.util.List;


public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> availableVideos = storage.list();
        if (availableVideos.isEmpty()) throw new NoVideoAvailableException();
        List<Advertisement> result = getVideos(availableVideos, 0);

    }

    public List<Advertisement> getVideos(List<Advertisement> inList, int start) {
        List<Advertisement> storageList = storage.list();
        List<Advertisement> tmp;
        List<Advertisement> result = new ArrayList<>();
        int inListSummOfTime = summOfTime(inList);
        int inListSummOfMoney = summOfListMoney(inList);

        Advertisement ad;

        for (int i = start; i < storageList.size(); i++) { // проходим по сторейдж массиву
            ad = storageList.get(i);
            if (!inList.contains(ad) && ad.getHits() > 0 && (inListSummOfTime + ad.getDuration()) < timeSeconds) {
                inList.add(ad);
                tmp = getVideos(inList, i);
                result = checkWhoBetter(result, tmp);
            }

        }
        return checkWhoBetter(inList, result);
    }

    // выводит сумму прибыли за показ листа
    public int summOfListMoney(List<Advertisement> list) {
        int summ = 0;
        for (Advertisement ad : list) {
            summ += ad.getAmountPerOneDisplaying();
        }
        return summ;
    }

    public int summOfTime(List<Advertisement> list) {
        int summ = 0;
        for (Advertisement ad : list) {
            summ += ad.getDuration();
        }
        return summ;
    }

    //сравнивает два листа по требованиям
    public List<Advertisement> checkWhoBetter(List<Advertisement> check, List<Advertisement> tmp) {
        List<Advertisement> result = new ArrayList<>();
        if (summOfListMoney(check) > summOfListMoney(tmp)) result = check;
        if (summOfListMoney(check) < summOfListMoney(tmp)) result = tmp;
        if (summOfListMoney(check) == summOfListMoney(tmp)) {
            if (summOfTime(check) > summOfTime(tmp)) result = check;
            if (summOfTime(check) < summOfTime(tmp)) result = tmp;
            if (summOfTime(check) == summOfTime(tmp)) {
                if (check.size() > tmp.size()) result = check;
                else result = tmp;
            }
        }
        return result;
    }
}
