package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.service.AddWordServiceModel;
import com.dictionaryapp.model.view.AllWordsViewModel;

import java.util.List;
import java.util.Set;

public interface WordService {
    void addWord(AddWordServiceModel addWordServiceModel);

    List<AllWordsViewModel> findAllWords();

    void removeWordById(Long id);

    List<Word> findAll();

    void removeAll(List<Word> words);
}
