package com.codecool.AI.controller;

import com.codecool.AI.model.Computer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AIController {

    private Computer computer;

    private AIController() {
        this.computer = new Computer("O", "Computer");
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public @ResponseBody ArrayList<String> move(@RequestBody ArrayList<String> board) {
        board = computer.move(board);
        return board;
    }
}
