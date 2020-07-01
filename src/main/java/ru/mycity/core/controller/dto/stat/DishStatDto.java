package ru.mycity.core.controller.dto.stat;

public class DishStatDto {

    private String dishName;
    private int count;

    public DishStatDto(String dishName, int count) {
        this.dishName = dishName;
        this.count = count;
    }

    public DishStatDto() {
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
