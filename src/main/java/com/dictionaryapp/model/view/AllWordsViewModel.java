package com.dictionaryapp.model.view;

import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.User;

import java.time.LocalDate;

public class AllWordsViewModel {
    private Long id;
    private String term;
    private String translation;
    private String example;
    private LocalDate inputDate;
    private LanguageNameEnum language;
    private User user;

    public AllWordsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageNameEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageNameEnum language) {
        this.language = language;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
