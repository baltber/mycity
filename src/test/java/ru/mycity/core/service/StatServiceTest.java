package ru.mycity.core.service;

import org.junit.Assert;
import org.junit.Test;
import ru.mycity.core.controller.dto.stat.DailyOrderStatDto;
import ru.mycity.core.service.dao.model.DishStat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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

    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate1 = dateFormat.parse("2020-05-12 02:53:48.884000");
        Date parsedDate2 = dateFormat.parse("2020-05-12 02:54:48.884000");
        Date parsedDate3 = dateFormat.parse("2020-05-13 04:53:48.884000");
        Date parsedDate4 = dateFormat.parse("2020-05-14 04:53:48.884000");
        Date parsedDate5 = dateFormat.parse("2020-05-15 04:53:48.884000");

        Timestamp timestamp1 = new java.sql.Timestamp(parsedDate1.getTime());
        Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
        Timestamp timestamp3 = new java.sql.Timestamp(parsedDate3.getTime());
        Timestamp timestamp4 = new java.sql.Timestamp(parsedDate4.getTime());
        Timestamp timestamp5 = new java.sql.Timestamp(parsedDate5.getTime());

        List<DailyOrderStatDto> source = new ArrayList<>();
        source.add(new DailyOrderStatDto(100, 200, 300, timestamp1));
        source.add(new DailyOrderStatDto(200, 500, 700, timestamp2));
        source.add(new DailyOrderStatDto(200, 500, 700, timestamp3));
        source.add(new DailyOrderStatDto(200, 500, 700, timestamp4));
        source.add(new DailyOrderStatDto(200, 500, 700, timestamp5));
        StatService statService = new StatService(null);
        Map<Instant, List<DailyOrderStatDto>> res = statService.groupByDays(source, ChronoUnit.DAYS);
        System.out.println(res);


    }


    @Test
    public void test2() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate1 = dateFormat.parse("2020-05-12 02:53:48.884000");
        Date parsedDate2 = dateFormat.parse("2020-05-12 04:53:48.884000");
        Date parsedDate3 = dateFormat.parse("2020-05-13 04:53:48.884000");
        Date parsedDate4 = dateFormat.parse("2020-05-14 04:53:48.884000");
        Date parsedDate5 = dateFormat.parse("2020-05-15 04:53:48.884000");

        Timestamp timestamp1 = new java.sql.Timestamp(parsedDate1.getTime());
        Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
        Timestamp timestamp3 = new java.sql.Timestamp(parsedDate3.getTime());
        Timestamp timestamp4 = new java.sql.Timestamp(parsedDate4.getTime());
        Timestamp timestamp5 = new java.sql.Timestamp(parsedDate5.getTime());

        System.out.println(timestamp1.toLocalDateTime().truncatedTo(ChronoUnit.HOURS));


    }
}