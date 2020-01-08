package com.maciej.search;

import java.util.*;

public class Word {

    private final String value;
    private Set<Document> documentsWithWord = new HashSet<>();

    public Word(String value) {
        this.value = value;
    }

    public void linkDocument(Document document) {
        documentsWithWord.add(document);

    }

    public void unLinkAllDocuments() {
        documentsWithWord.clear();
    }

    public Set<Document> getLinkedDocuments() {
        return documentsWithWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
