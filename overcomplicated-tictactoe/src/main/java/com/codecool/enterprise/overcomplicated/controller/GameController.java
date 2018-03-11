package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TicTacToeGame;
import com.codecool.enterprise.overcomplicated.service.AIService;
import com.codecool.enterprise.overcomplicated.service.ComicService;
import com.codecool.enterprise.overcomplicated.service.FunFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.net.URISyntaxException;
import java.util.Map;

@Controller
@SessionAttributes({"player", "game"})
public class GameController {

    @Autowired
    private TicTacToeGame game;

    @Autowired
    private FunFactService funFactService;

    @Autowired
    private AIService aiService;

    @Autowired
    private ComicService comicService;

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

    @PostMapping(value = "/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) {
        model.addAttribute("board", game.getBoard());
        model.addAttribute("funFact", getNewFunFact());
        Map<String, String> comic = getNewComic();
        model.addAttribute("comic", comic);
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move, Model model) throws URISyntaxException {
        player.move(move);
        game.setBoard(aiService.moveAI(game.getBoard()));
        String winner = game.checkWinner();
        Boolean isGameFinished = game.checkIsGameFinished();
        if (winner != null) {
            if (winner.equals("O")) {
                model.addAttribute("winner", "computer");
            } else if (winner.equals("X")) {
                model.addAttribute("winner", player.getUserName());
            }
        } else if (isGameFinished) {
            model.addAttribute("finished", true);
        }
        model.addAttribute("board", game.getBoard());
        return "game";
    }

    @GetMapping(value = "/new_fun_fact")
    public String getNewFunFact() {
        return funFactService.getFunFact();
    }

    @GetMapping(value = "/new_comic")
    public Map<String, String> getNewComic() {
        return comicService.getComic();
    }
}
