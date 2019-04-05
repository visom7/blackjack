package com.example.testPoker.controller;

import com.example.testPoker.model.game.PlayerStatus;
import com.example.testPoker.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/blackjack")
public class BlackjackController {

    @Autowired
    private GameService gameService;

    @GetMapping("/initgame")
    public ModelAndView initGame() {
        ModelAndView modelAndView = new ModelAndView();
        int cardNumber = gameService.initGame();
        modelAndView.setViewName("initgame");
        modelAndView.addObject("cardNumber", cardNumber);
        return modelAndView;
    }

    @GetMapping("/gethand")
    public ModelAndView getStartingHand() {
        ModelAndView modelAndView = new ModelAndView("handandscore");
        gameService.getStartingHand();
        modelAndView.addObject("hand", gameService.getPlayer().getHand());
        modelAndView.addObject("score", gameService.getPlayer().getScore());
        if (PlayerStatus.BLACKJACK.equals(gameService.getPlayer().getPlayerStatus())) {
            modelAndView.setViewName("handandscorewhenblackjack");
        }
        return modelAndView;
    }

    @GetMapping("/getcard")
    public ModelAndView getCard() {
        ModelAndView modelAndView = new ModelAndView();
        gameService.getCard();
        modelAndView.addObject("hand", gameService.getPlayer().getHand());
        modelAndView.addObject("score", gameService.getPlayer().getScore());

        switch (gameService.getPlayer().getPlayerStatus()) {
            case JUST_21:
                modelAndView.setViewName("muststand");
                break;
            case OVER_21:
                modelAndView.setViewName("handandscorewhenyoulose");
                break;
            case UNDER_21:
                modelAndView.setViewName("handandscore");
                break;
        }
        return modelAndView;
    }

    @GetMapping("/stand")
    public ModelAndView stand() {
        ModelAndView modelAndView = new ModelAndView();
        if (gameService.stand()) {
            modelAndView.setViewName("croupierturnwin");
        } else {
            modelAndView.setViewName("croupierturnlosses");
        }
        modelAndView.addObject("hand", gameService.getPlayer().getHand());
        modelAndView.addObject("score", gameService.getPlayer().getScore());
        modelAndView.addObject("croupierhand", gameService.getCroupier().getHand());
        modelAndView.addObject("croupierscore", gameService.getCroupier().getScore());
        return modelAndView;
    }
}
