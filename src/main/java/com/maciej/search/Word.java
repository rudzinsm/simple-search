package com.maciej.search;

import java.util.*;
import java.util.stream.Collectors;

public class Word {

    private final String term;
    private Set<Document> documentsWithTerm = new HashSet<>();
    private final ReversedIndex index;


    Word(String term, ReversedIndex myIndex) {
        this.term = term;
        this.index = myIndex;
    }

    void linkDocument(Document document) {
        documentsWithTerm.add(document);
    }

    private int documentsWithWordCount() {
        return documentsWithTerm.size();
    }

    private int documentsCount() {
        return index.getDocumentsCount().get();
    }

    private double getIdf() {
        return Math.log((double)documentsCount() / (double) documentsWithWordCount());
    }

    protected List<Result> getResultsWithWeights() {
        return documentsWithTerm.stream()
                .map(d -> new Result(d, d.getTF(this.term) * this.getIdf()))
                .sorted().collect(Collectors.toList());
    }

    public List<Document> getDocuments() {
        return getResultsWithWeights().stream()
                .map(r -> r.document)
                .collect(Collectors.toList());
    }

    public void unLinkAllDocuments() {
        documentsWithTerm.clear();
    }

    public Set<Document> getLinkedDocuments() {
        return documentsWithTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(term, word.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term);
    }


    public static class Result implements Comparable<Result> {
        final Document document;
        final Double tfIdf;

        Result(Document document, double tfIdf) {
            this.document = document;
            this.tfIdf = tfIdf;
        }

        @Override
        public int compareTo(Result o) {
            int comp = -1 * tfIdf.compareTo(o.tfIdf);  //reverse order higher number word is more important
            if (comp == 0) {
                // same value compare by document names
                return document.getDocumentName().compareTo(o.document.getDocumentName());
            } else
                return comp;
        }
    }

}
