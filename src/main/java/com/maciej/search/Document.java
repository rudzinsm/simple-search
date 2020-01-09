package com.maciej.search;

import org.springframework.util.StringUtils;

import java.util.*;

public class Document {

    // name should simplify debugging
    private final String documentName;
    // text of document;
    private final String text;
    //since text is final we can calculate this in constructor
    private Map<String, Integer> termFrequency;

    public Document(String docName, String text) {
        documentName = docName;
        this.text = text;
        termFrequency = getWordsCount();
    }

    private List<String> tokenizeWords() {
        if (StringUtils.isEmpty(text)) {
            return Collections.emptyList();
        }
        return Arrays.asList(text.split("\\s+"));
    }

    /**
     * This function will provide tf (text frequency) for words.
     * @return map with words as keys and value is count in document.
     */
    private Map<String, Integer> getWordsCount() {
        Map<String, Integer> result = new HashMap<>();
        for (String word : tokenizeWords()) {
            if (!result.containsKey(word)) {
                result.put(word, 1);
            } else {
                result.put(word, result.get(word) + 1);
            }
        }
        return result;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Map<String, Integer> getTermFrequencyMap() {
        return termFrequency;
    }

    public Integer getTF(String term) {
        return termFrequency.getOrDefault(term,0);
    }



    @Override
    public String toString() {
        return "Document{" +
                "documentName='" + documentName + '\'' +
                ", text='" + text + '\'' +
                ", termFrequency=" + termFrequency +
                '}';
    }
}
