package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.service.OrganisationService;

@Component
@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationService service;


    @RequestMapping(path = "/config/{guid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Получить конфигурацию организации по GUID")
    public String auth(@PathVariable("guid") String guid) {
        return service.getOrganisationConfig(guid);
    }
}
