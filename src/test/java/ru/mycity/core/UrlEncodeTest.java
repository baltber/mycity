package ru.mycity.core;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.util.UriUtils;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.service.StringOrderService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;

@Ignore
public class UrlEncodeTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        String enc = "name=%D0%A1%D0%B5%D1%80%D0%B3%D0%B5%D0%B9&phone=%2B7+%28981%29+859+20+47&email=s.tailakov%40gmail.com&address=%D0%93%D1%80%D0%B0%D0%B6%D0%B4%D0%B0%D0%BD%D1%81%D0%BA%D0%B8%D0%B9+%D0%BF%D1%80%D0%BE%D1%81%D0%BF%D0%B5%D0%BA%D1%82+121&delivery=%D0%92+%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B0%D1%85+%D0%9C%D0%9A%D0%90%D0%94+%3D+0&comment=%D0%9F%D1%80%D0%B8%D0%B2%D0%B5%D1%82&payment%5Bsys%5D=none&payment%5Bsystranid%5D=0&payment%5Borderid%5D=2064015095&payment%5Bproducts%5D%5B0%5D%5Bname%5D=%D0%92%D0%B0%D0%BB%D0%B5%D1%80%D0%B8%D1%8F%D0%BD%D0%B0&payment%5Bproducts%5D%5B0%5D%5Bquantity%5D=1&payment%5Bproducts%5D%5B0%5D%5Bamount%5D=580&payment%5Bproducts%5D%5B0%5D%5Bprice%5D=580&payment%5Bproducts%5D%5B1%5D%5Bname%5D=%D0%9A%D0%B0%D1%80%D0%BE%D1%82%D0%BE%D1%81%D0%B0%D0%BB%D0%B0%D1%82%D0%B0&payment%5Bproducts%5D%5B1%5D%5Bquantity%5D=1&payment%5Bproducts%5D%5B1%5D%5Bamount%5D=390&payment%5Bproducts%5D%5B1%5D%5Bprice%5D=390&payment%5Bproducts%5D%5B2%5D%5Bname%5D=%D0%9F%D0%B0%D0%B4%D0%B7%D0%B0%D1%80%D0%BE%D1%81%D0%B0%D0%BB%D0%B0%D1%82%D0%B0&payment%5Bproducts%5D%5B2%5D%5Bquantity%5D=2&payment%5Bproducts%5D%5B2%5D%5Bamount%5D=820&payment%5Bproducts%5D%5B2%5D%5Bprice%5D=410&payment%5Bamount%5D=1790&payment%5Bsubtotal%5D=1790&payment%5Bdelivery%5D=%D0%92+%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B0%D1%85+%D0%9C%D0%9A%D0%90%D0%94&formid=form175983689&formname=Cart";
        String s = URLDecoder.decode(enc, "UTF-8");

        String s2 = UriUtils.decode(enc, Charset.forName("UTF-8"));
        System.out.println(s);
        System.out.println(s2);

        StringOrderService stringOrderService = new StringOrderService();
        Map<String, String> map = stringOrderService.requestToMap(s);
        OrderRequestDto dto = stringOrderService.mapToOrder(map);
        System.out.println(map);
    }
}
