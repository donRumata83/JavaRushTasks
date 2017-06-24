package com.javarush.task.task28.task2810.model;


import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.Collections;
import java.util.List;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return Collections.emptyList();
    }
}
