# simple-search

ReverseIndex class is main entry point of this example

before using index it has to be prepared by adding documents using method 

`addDocument(Document doc)`

to search index use 

`getDocuments(Srting word)`

results should be sorted by frequency of word in document moderated by
frequency that word is found in other documents.

Test `ReverseIndexSpec`shows usage of index. 

# Structure Diagram
```

          +-----------+
          |Rev Index  |
          |  docCount |
          |           |
          |           |
          |           |
          +-----+-----+
                |                              +-----------------------+
                |                              | Document 1            |
                |                        +----->                       |
                |                        |     | 'ala ma'              |
                |   +-----------------+  |  +--> tf                    |
                +---+Word 'ala'    idf+--+  |  +-----------------------+
                |   +-----------------+  |  |
                |                        |  |  +-----------------------+
                |   +-----------------+  +-----> Document 2            |
                +---+Word 'ma'     idf+-----+  |                       |
                |   +-----------------+     |  | 'kota ala'            |
                |                       +----->+ tf                    |
                |   +-----------------+ |   |  +-----------------------+
                +---+Word 'kota'   idf+-+   |
                    +-----------------+ |   |  +-----------------------+
                                        |   |  | Document 3            |
                                        |   |  |                       |
                                        |   +--> 'ma kota'             |
                                        |      | tf                    |
                                        +----->+-----------------------+
```