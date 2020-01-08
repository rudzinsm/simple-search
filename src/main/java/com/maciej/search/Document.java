package com.maciej.search;

import org.springframework.util.StringUtils;

import java.util.*;

public class Document {
    private String text;

    public Document(String text) {
        this.text = text;
    }

    public List<String> tokenizeWords() {
        if (StringUtils.isEmpty(text)) {
            return Collections.emptyList();
        }
        return Arrays.asList(text.split("\\s+"));
    }

    public Map<String, Integer> getWordsCount() {
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

}
