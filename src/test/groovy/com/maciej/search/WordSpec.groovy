package com.maciej.search

import static org.hamcrest.Matchers.containsInAnyOrder
import static spock.util.matcher.HamcrestSupport.that
import spock.lang.Specification

class WordSpec extends Specification {

    def "GetDocuments"() {
        given:
        def index = new ReversedIndex()
        def w = new Word("kalejdoskop", index)
        def doc1 = new Document("doc1", "Świat jest jak kalejdoskop")
        def doc2 = new Document("doc2", "kalejdoskop ludzkich spraw")
        def doc3 = new Document("doc3", "kalejdoskop szklany spawany")
        w.linkDocument(doc1)
        w.linkDocument(doc2)
        w.linkDocument(doc3)
        when:
        List<Document>  result = w.getDocuments()
        then: 'expect for kalejdoskop to find three docs'
            that result, containsInAnyOrder(doc1, doc2, doc3)
            result == [doc1, doc2,doc3] // with order
    }

    def "LinkDocument"() {
        given:
        def index = new ReversedIndex()
        def w = new Word("kalejdoskop", index)
        def doc1 = new Document("doc1", "Świat jest jak kalejdoskop")
        def doc2 = new Document("doc2", "kalejdoskop ludzkich spraw")
        when: 'when documents are linked multiple times'
        w.linkDocument(doc1)
        w.linkDocument(doc2)
        w.linkDocument(doc2)
        then: 'only unique values are stored'
        w.linkedDocuments.size() == 2
        w.linkedDocuments.contains(doc1)
        w.linkedDocuments.contains(doc2)
    }


    def "UnLinkAllDocuments"() {
        given:
        def index = new ReversedIndex()
        def w = new Word("sprząta", index)
        def doc1 = new Document("doc1", "komu przeszkadza ten sprząta")
        def doc2 = new Document("doc2", "kto nie sprząta ten do konta")
        w.linkDocument(doc1)
        w.linkDocument(doc2)
        when:
        w. unLinkAllDocuments()
        then:
        w.linkedDocuments.isEmpty()
    }

    def "GetLinkedDocuments"() {
      //  given:
      //  when:
      //  then:
    }


}
