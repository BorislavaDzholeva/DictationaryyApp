package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.view.AllWordsViewModel;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final WordService wordService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, WordService wordService, UserService userService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        if (currentUser.getId() != null) {
            return "redirect:/home";
        }
        return "index";

    }

    @GetMapping("/home")
    public String home(Model model) {
        if (currentUser.getId() == null) {
            return "index";
       }
        List<AllWordsViewModel> allWords = wordService.findAllWords();
        HashMap<String, List<AllWordsViewModel>> wordsByLanguage = new HashMap<>();
        wordsByLanguage.put(LanguageNameEnum.FRENCH.name(), new ArrayList<>());
        wordsByLanguage.put(LanguageNameEnum.SPANISH.name(), new ArrayList<>());
        wordsByLanguage.put(LanguageNameEnum.GERMAN.name(), new ArrayList<>());
        wordsByLanguage.put(LanguageNameEnum.ITALIAN.name(), new ArrayList<>());


        int sizeAllWords = 0;
        for (AllWordsViewModel word : allWords) {
            wordsByLanguage.get(word.getLanguage().name()).add(word);
            sizeAllWords += 1;
        }

        model.addAttribute("wordsByLanguage", wordsByLanguage);
        model.addAttribute("sizeAllWords", sizeAllWords);

        return "home";
    }
    @GetMapping("/removeOneWord/{id}")
    public String removeOneWord(@PathVariable Long id) {
        wordService.removeWordById(id);
        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {
        List<Word> words = wordService.findAll();
        wordService.removeAll(words);
        return "redirect:/home";
    }




}

