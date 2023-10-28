package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageNameEnum languageName;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private Set<Word> words;

    public Language() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageNameEnum getLanguageName() {
        return languageName;
    }

    public void setLanguageName(LanguageNameEnum languageName) {
        this.languageName = languageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
