package ru.mycity.core.service.dao.model;

import ru.mycity.core.controller.dto.stat.DishStatDto;
import ru.mycity.core.controller.dto.stat.OrderStatDto;

import java.util.Objects;

public class DishStat {

    public String dishName;
    public int count;

    public DishStatDto toDto(){
        return new DishStatDto(dishName,count);
    }

    public DishStat(String dishName, int count) {
        this.dishName = dishName;
        this.count = count;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishStat dishStat = (DishStat) o;
        return count == dishStat.count &&
                Objects.equals(dishName, dishStat.dishName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishName, count);
    }
}
