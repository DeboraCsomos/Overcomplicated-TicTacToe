package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TicTacToeGame;
import com.codecool.enterprise.overcomplicated.service.AIService;
import com.codecool.enterprise.overcomplicated.service.AvatarService;
import com.codecool.enterprise.overcomplicated.service.ComicService;
import com.codecool.enterprise.overcomplicated.service.FunFactService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("game")
public class GameController {

    @Autowired
    private TicTacToeGame game;
    @Autowired
    private FunFactService funFactService;
    @Autowired
    private AIService aiService;
    @Autowired
    private ComicService comicService;
    @Autowired
    private AvatarService avatarService;

    @ModelAttribute("player")
    public Player getPlayer() {
        return game.getPlayer();
    }

    @ModelAttribute("game")
    public TicTacToeGame getGame() {
        return game;
    }

    @ModelAttribute("avatar")
    public String getAvatarUri() {
        Player player = game.getPlayer();
        return avatarService.getAvatarURI(player.getUserName());
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value = "/change_player_username")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) {
        model.addAttribute("board", game.getBoard());
        model.addAttribute("funFact", funFactService.getFunFact());
        model.addAttribute("comic", comicService.getComic());
        return "game";
    }

    @GetMapping(value = "/game-move", produces = "application/json")
    @ResponseBody
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move) throws URISyntaxException {
        player.move(move);
        String winner = game.checkWinner();
        Boolean isGameFinished = game.checkIsGameFinished();
        if (winner == null && !isGameFinished) {
            game.setBoard(aiService.moveAI(game.getBoard()));
            winner = game.checkWinner();
            isGameFinished = game.checkIsGameFinished();
        }
        Map<String, String> gameMap = new HashMap<>();
        Gson gson = new Gson();
        gameMap.put("board", gson.toJson(game.getBoard()));
        if (winner != null || isGameFinished) {
            game.emptyBoard();
            if (isGameFinished) {
                gameMap.put("finished", "true");
            }
            if (winner != null) {
                if (winner.equals("O")) {
                    gameMap.put("winner", "computer");
                } else if (winner.equals("X")) {
                    gameMap.put("winner", player.getUserName());
                }
            }
        }
        return gson.toJson(gameMap);
    }

    @GetMapping(value = "/new_fun_fact", produces = "application/json")
    @ResponseBody
    public String getNewFunFact() {
        return new Gson().toJson(funFactService.getFunFact());
    }

    @GetMapping(value = "/new_comic")
    @ResponseBody
    public String getNewComic() {
        return new Gson().toJson(comicService.getComic());
    }
}
