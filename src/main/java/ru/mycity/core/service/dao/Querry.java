package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.DateTimeModel;

import java.util.StringJoiner;

public class Querry {

    private StringJoiner where;

    public Querry init(){
        where = (new StringJoiner("", " WHERE ", "")).setEmptyValue("");
        where.add(" 1 = 1");
        return this;
    }

    public Querry withPageable(Integer limit, Integer start){

        addDefaultValues(limit, start);
        if(limit != null){
            where.add(" limit :limit");
        }
        if(start != null){
            where.add(" offset :offset");
        }
        return this;

    }

    private void addDefaultValues(Integer limit, Integer start){
        if(limit==null){
            limit=10;
        }

        if(start==null){
            start=0;
        }
    }

    public Querry withDates(DateTimeModel dateTime){
        if (dateTime.getStartDate() != null && dateTime.getEndDate() != null) {
            where.add(" AND order_date BETWEEN :start_date AND :end_date");
        } else if(dateTime.getStartDate() != null && dateTime.getEndDate() == null){
            where.add(" AND order_date >= :start_date");
        }  else if(dateTime.getStartDate() == null && dateTime.getEndDate() != null){
            where.add(" AND order_date <= :end_date");
        }
        return this;
    }

    public StringJoiner build(){
        return where;
    }
}
