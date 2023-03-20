package com.example.lztest.controller;

import com.example.lztest.service.impl.PeopleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Person Controller", tags = { "Person" })
public class PersonController {
    @SneakyThrows
    @GetMapping(value = "/get-json-of-names", produces = "application/json")
    @ApiOperation(value = "Parse people from file")
    public ResponseEntity<List<Object>> highlightNames() {
        return ResponseEntity.ok(PeopleServiceImpl.convertFileDataToJSONArray("src/main/resources/test-data/name.txt").toList());
    }
}
