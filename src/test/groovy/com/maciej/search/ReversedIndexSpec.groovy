package com.maciej.search

import spock.lang.Specification

import java.util.stream.Collectors

import static org.hamcrest.Matchers.containsInAnyOrder
import static spock.util.matcher.HamcrestSupport.that

class ReversedIndexSpec extends Specification {

    def "GetDocuments"() {
        given:
        def index = new ReversedIndex()
        Document doc1 = new Document("doc1", "niebo jest niebieskie")
        Document doc2 = new Document("doc2", "niebo nie jest niebieskie")
        index.addDocument(doc1)
        index.addDocument(doc2)

        expect:
        index.getDocuments(word).stream().map {d -> d.documentName}.collect(Collectors.toList()) == documents

        where:
        word         || documents
        'niebo'      || ["doc1", "doc2"]
        'nie'        || ["doc2"]
        'jest'       || ["doc1", "doc2"]
        'niebieskie' || ["doc1", "doc2"]
        'coś'        || []
    }

    def "GetDocuments - example from test"() {
        given:
        def index = new ReversedIndex()

        Document doc1 = new Document("Document 1", "the brown fox jumped over the brown dog")
        Document doc2 = new Document("Document 2", "the lazy brown dog sat in the corner")
        Document doc3 = new Document("Document 3", "the red fox bit the lazy dog")
        index.addDocument(doc1)
        index.addDocument(doc2)
        index.addDocument(doc3)

        expect:
        index.getDocuments(word).stream().map {d -> d.documentName}.collect(Collectors.toList()) == documents

        where:
        word         || documents
        'brown'      || ["Document 1", "Document 2"]
        'fox'        || ["Document 1", "Document 3"]
    }

    def "GetDocumentsCount"() {
        given:
        def index = new ReversedIndex()
        def doc1 = new Document("doc1", "ala ma kota")
        def doc2 = new Document("doc2", "a kot ma ala")
        def doc3 = new Document("doc3", "dobrze się bawią i jest wspaniale")
        index.addDocument(doc1)
        index.addDocument(doc2)
        index.addDocument(doc3)
        when:
        Integer result = index.getDocumentsCount()
        then: 'expect for kot to find three docs'
        result == 3
    }


    def "AddDocument"() {
        given:
        def index = new ReversedIndex()
        def doc1 = new Document("doc1", "ala ma")
        def doc2 = new Document("doc2", "ala")
        when:
        index.addDocument(doc1)
        index.addDocument(doc2)

        then:
        index.getDocumentsCount().intValue() == 2

        that index.getDocuments("ma"), containsInAnyOrder(doc1)
        that index.getDocuments("ala"), containsInAnyOrder(doc1, doc2)

    }
}
