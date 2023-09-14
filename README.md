# lucene

- 루씬 공식문서
> https://lucene.apache.org/

- lucene 관련 종속성 (gradle)
2023.09.14 기준 최신 버전
```Groovy
implementation group: 'org.apache.lucene', name: 'lucene-core', version: '9.7.0'
implementation group: 'org.apache.lucene', name: 'lucene-queryparser', version: '9.7.0'
implementation group: 'org.apache.lucene', name: 'lucene-analyzers-common', version: '8.11.2'
implementation group: 'org.apache.lucene', name: 'lucene-analyzers-nori', version: '8.11.2'
implementation group: 'org.apache.lucene', name: 'lucene-highlighter', version: '8.11.2'
```
---
### index 순서
- 색인 파일을 넣을 Path 준비
- Directory.open(Path)
- IndexWriterConfig(Analyzer)
- IndexWriter(Directory, IndexWriterConfig)
- IndexWriter.close
- Directory.close

### search 순서
- Directory.open(Path)
- IndexReader.open(Directory) 
- Query 생성
- IndexSearcher.search(Query, int)
- Directory.close
---
### lucene Similarity 점수 커스텀
- SimilarityBase를 확장하여 사용하고 그 값을 IndexWriterConfig.setSimilarity("커스텀한 클래스") 그리고
IndexSearch.setSimilarity("커스텀한 클래스")를 적용한다.