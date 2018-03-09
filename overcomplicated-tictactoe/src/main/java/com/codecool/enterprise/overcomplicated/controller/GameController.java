package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TicTacToeGame;
import com.codecool.enterprise.overcomplicated.service.FunFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"player", "game"})
public class GameController {

    @Autowired
    private TicTacToeGame game;

    @Autowired
    private FunFactService funFactService;

    @ModelAttribute("player")
    public Player getPlayer() {
        return game.getPlayers().get("player");
    }

    @ModelAttribute("game")
    public TicTacToeGame getGame() {
        return game;
    }

    @ModelAttribute("avatar_uri")
    public String getAvatarUri() {
        return "https://robohash.org/codecool";
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value="/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) {
        model.addAttribute("funfact", "&quot;Chuck Norris knows the last digit of pi.&quot;");
        model.addAttribute("comic_uri", "https://imgs.xkcd.com/comics/bad_code.png");
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move) {
        System.out.println("Player moved " + move);
        return "redirect:/game";
    }
}
