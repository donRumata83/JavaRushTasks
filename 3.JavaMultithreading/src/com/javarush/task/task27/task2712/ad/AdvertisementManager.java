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

    public List<Advertisement> getVideos(ArrayList<Advertisement> inList, int start) {
        List<Advertisement> storageList = storage.list();
        List<Advertisement> result;
        int inListSumm = 0;
        for (Advertisement ad : inList) {  // сумма времени рекламы в Ин листе
            inListSumm += ad.getDuration();
        }
        Advertisement ad;

        for (int i = start; i < storageList.size(); i++) { // проходим по сторейдж массиву
            ad = storageList.get(i);
            if (!inList.contains(ad) && ad.getHits() > 0 && (inListSumm + ad.getDuration()) < timeSeconds) {
                inList.add(ad);
                getVideos(inList, i);
            }

        }
    }
}
