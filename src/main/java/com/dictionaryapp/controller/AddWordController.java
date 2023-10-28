package com.dictionaryapp.controller;

import com.dictionaryapp.model.binding.AddWordBindingModel;
import com.dictionaryapp.model.service.AddWordServiceModel;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class AddWordController {
    private final WordService wordService;
    private final ModelMapper modelMapper;

    public AddWordController(WordService wordService, ModelMapper modelMapper) {
        this.wordService = wordService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public AddWordBindingModel addWordBindingModel(){
        return new AddWordBindingModel();
    }

    @GetMapping("/add")
    public String add(){
        return "word-add";
    }
    @PostMapping("/add")
    public String addConfirm(@Valid AddWordBindingModel addWordBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addWordBindingModel", addWordBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWordBindingModel", bindingResult);

            return "redirect:add";
        }
        wordService.addWord(modelMapper.map(addWordBindingModel, AddWordServiceModel.class));
        return "redirect:/home";

    }
}
