package com.codecool.AI.controller;

import com.codecool.AI.model.Computer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AIController {

    @Autowired
    private Computer computer;


    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public ResponseEntity<List<String>> move(@RequestBody ArrayList<String> board) {
        board = computer.move(board);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
}
