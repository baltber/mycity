package ru.mycity.core.service.dao.model;

public class DishStat {

    private String dishName;
    private String count;

    public DishStat(String dishName, String count) {
        this.dishName = dishName;
        this.count = count;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
