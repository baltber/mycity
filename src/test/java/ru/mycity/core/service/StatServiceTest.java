package ru.mycity.core.service;

import org.junit.Assert;
import org.junit.Test;
import ru.mycity.core.service.dao.model.DishStat;

import java.util.ArrayList;
import java.util.List;


public class StatServiceTest {



    @Test
    public void groupByDish() {
        List<DishStat> sourceList = new ArrayList<>();
        sourceList.add(new DishStat("aaa", 100));
        sourceList.add(new DishStat("aaa", 100));
        sourceList.add(new DishStat("aaa", 100));
        sourceList.add(new DishStat("bbb", 200));
        sourceList.add(new DishStat("bbb", 200));
        sourceList.add(new DishStat("ccc", 300));

        StatService statService = new StatService(null);
        List<DishStat> res =  statService.groupByDish(sourceList);

        List<DishStat> expected = new ArrayList<>();
        expected.add(new DishStat("aaa", 300));
        expected.add(new DishStat("bbb", 400));
        expected.add(new DishStat("ccc", 300));


        Assert.assertTrue(expected.containsAll(res));

    }
}