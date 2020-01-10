package com.maciej.search

import spock.lang.Specification

class DocumentSpec extends Specification {
    def "TokenizeWords"() {
        given:
            Document doc = new Document("doc1","ala lala ma kota ala")
        when:
           List<String> words = doc.tokenizeWords()
        then:
            words.size() == 5
            words[4] == "ala"
            words[0] == "ala"
    }

    def "GetWordsCount"() {
        given:
        Document doc = new Document("doc1","ala lala  ala ma kota  ")
        when:
        Map<String,Integer> count = doc.getWordsCount()
        then:
        count.size() == 4
        count['ala'] == 2
        count['kota'] == 1
    }
}
