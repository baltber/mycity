package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.order.OrderDto;
import ru.mycity.core.controller.dto.order.OrderList;
import ru.mycity.core.controller.dto.order.OrderRequestDto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StringOrderService {

    @Autowired
    private OrderService orderService;

    public String add(String request){
        String decode = null;
        try {
            decode = URLDecoder.decode(request, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OrderRequestDto requestDto = mapToOrder(requestToMap(decode));
        return orderService.add(requestDto);
    }


    public OrderRequestDto mapToOrder(Map<String, String> map){

        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setSummary("Заказ №: " + map.get("payment[orderid]"));
        requestDto.setName(map.get("name"));
        requestDto.setPhone(map.get("phone"));
        requestDto.setEmail(map.get("email"));
        requestDto.setAddress(map.get("address"));
        requestDto.setComment(map.get("comment"));

        OrderList orderList = new OrderList(createListOrder(map));
        orderList.setDelivery(map.get("payment[delivery]"));
        orderList.setTotalPrice(Integer.valueOf(map.get("payment[subtotal]")));
        orderList.setDeliveryPrice(Integer.valueOf(map.get("delivery_price")));
        requestDto.setOrderList(orderList);

        return requestDto;

    }

    private List<OrderDto> createListOrder(Map<String, String> map){
        List<OrderDto> result = new ArrayList<>();
        for(int i = 0; i < map.size(); i++){
            String orderName = "payment[products]["+i+"][name]";
            if(map.containsKey(orderName)){
                OrderDto orderDto = new OrderDto();
                orderDto.setName(map.get(orderName));
                orderDto.setAmount(map.get("payment[products]["+i+"][amount]"));
                orderDto.setQuantity(map.get("payment[products]["+i+"][quantity]"));
                orderDto.setPrice(map.get("payment[products]["+i+"][price]"));
                result.add(orderDto);
            }else {
                break;
            }
        }

        return result;
    }


    public Map<String, String> requestToMap(String s){

        String [] split = s.split("&");
        String deliveryPriceString = Arrays.stream(split)
                .map(e -> e.split("="))
                .filter(e -> e[0].equals("delivery"))
                .findFirst()
                .get()[2];

        Map<String, String> map = Arrays.stream(split)
                .map(e -> e.split("="))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));

        map.put("delivery_price", deliveryPriceString.trim());
        return new LinkedHashMap<>(map);

    }
}

/*
{payment[products][1][price]=390,
        formid=form175983689,
        payment[products][0][price]=580,
        payment[products][1][name]=Каротосалата,
        formname=Cart,
        payment[products][0][name]=Валерияна,
        payment[products][1][quantity]=1,
        payment[products][2][name]=Падзаросалата,
        payment[products][0][amount]=580,
        payment[products][2][price]=410,
        email=s.tailakov@gmail.com,
        delivery=В пределах МКАД ,
        address=Гражданский проспект 121,
        payment[products][0][quantity]=1,
        payment[products][2][amount]=820,
        payment[sys]=none, payment[amount]=1790,
        payment[subtotal]=1790,
        payment[orderid]=2064015095,
        phone=+7 (981) 859 20 47,
        payment[systranid]=0,
        payment[products][1][amount]=390,
        name=Сергей,
        comment=Привет,
        payment[products][2][quantity]=2,
        payment[delivery]=В пределах МКАД}

*/