package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.service.AddWordServiceModel;
import com.dictionaryapp.model.view.AllWordsViewModel;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final LanguageService languageService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public WordServiceImpl(WordRepository wordRepository, ModelMapper modelMapper, LanguageService languageService, UserService userService, CurrentUser currentUser) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.languageService = languageService;
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @Override
    public void addWord(AddWordServiceModel addWordServiceModel) {
        Word word = modelMapper.map(addWordServiceModel, Word.class);
        word.setLanguage(languageService.findByLanguageNameEnum(addWordServiceModel.getLanguage()));
        word.setAddedBy(userService.findById(currentUser.getId()));
        wordRepository.save(word);

    }

    @Override
    public List<AllWordsViewModel> findAllWords() {
        return wordRepository.findAll().stream().map(word -> {
            AllWordsViewModel allWordsViewModel = new AllWordsViewModel();
            allWordsViewModel.setExample(word.getExample());
            allWordsViewModel.setId(word.getId());
            allWordsViewModel.setTerm(word.getTerm());
            allWordsViewModel.setTranslation(word.getTranslation());
            allWordsViewModel.setInputDate(word.getInputDate());
            allWordsViewModel.setLanguage(word.getLanguage().getLanguageName());
            allWordsViewModel.setUser(word.getAddedBy());
            return allWordsViewModel;
        }).collect(Collectors.toList());

    }

    @Override
    public void removeWordById(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    @Override
    public void removeAll(List<Word> words) {
        wordRepository.deleteAll(words);
    }
}
