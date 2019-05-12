package ru.mycity.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.ComplaintDto;
import ru.mycity.core.service.ComplaintService;

import java.util.List;

@Component
@RestController
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    @RequestMapping(path = "/complaint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ComplaintDto> getComplaints(@RequestParam(value = "category" , required = false) String category) {
        return service.getComplaints(category);
    }
}
