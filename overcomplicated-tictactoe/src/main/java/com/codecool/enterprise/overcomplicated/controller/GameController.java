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
        model.addAttribute("comic_uri", "https://imgs.xkcd.com/comics/bad_code.png");
        model.addAttribute("gameState", game.getGameState());
        model.addAttribute("funFact", funFactService.getFunFact());
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move, Model model) {
        player.move(move);
        game.getComputer().move();
        model.addAttribute("gameState", game.getGameState());
        return "redirect:/game";
    }

    @GetMapping(value = "/new_fun_fact")
    public @ResponseBody String getNewFunFact() {
        return funFactService.getFunFact();
    }
}
