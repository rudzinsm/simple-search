package com.maciej.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ReversedIndex {
    private AtomicInteger documentsCount = new AtomicInteger();
    private Map<String, Word> words = new HashMap<>();

    AtomicInteger getDocumentsCount() {
        return documentsCount;
    }

    public List<Document> getDocuments(String word) {
        if(words.containsKey(word)) {
            return words.get(word).getDocuments();
        }
        return Collections.emptyList();
    }

    public void addDocument(Document document) {
        Map<String, Integer> termFrequencyMap = document.getTermFrequencyMap();

        for (Map.Entry<String, Integer> docTerm : termFrequencyMap.entrySet()) {
            if(!words.containsKey(docTerm.getKey())) {
               words.put(docTerm.getKey(), new Word(docTerm.getKey(),this));
            }
            words.get(docTerm.getKey()).linkDocument(document);

        }
        documentsCount.incrementAndGet();
    }

}
