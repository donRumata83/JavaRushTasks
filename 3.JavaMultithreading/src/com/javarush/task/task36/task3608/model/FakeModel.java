package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.Arrays;

/**
 * Created by Rumata on 28.02.2017.
 */
public class FakeModel implements Model{
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(Arrays.asList(new User("A", 1, 1),
                new User("B", 2, 1)));
    }
}
