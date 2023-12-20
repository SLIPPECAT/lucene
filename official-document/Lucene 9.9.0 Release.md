## 2023 12월 4일 Apache Lucene™ 9.9.0 available
> https://lucene.apache.org/core/corenews.html#apache-lucenetm-990-available

### Lucene 9.9.0 Release Highlights:

#### 새로운 기능

1. HSNW 벡터 포맷에 int8 scalar quantization(양자화)을 추가하였습니다. 선택적으로 벡터의 더 컴팩트한 손실 압출을 허용하며, HNSW 검색을 위해 대략 4배 적은 메모리를 필요로 합니다.
2. 이제 HNSW 그래프는 여러 스레드와 병합할 수 있으며, 세그먼트 간 동시성에서 활용하는 동일한 인프라를 활용합니다.

#### 개선 사항
1. Panama 벡터 지원에 대한 속도 향상, FMA 사용 및 테스트를 개선하였습니다.
2. FST컴파일러는 이제 suffixRAMLimitMB 메서드를 사용하여, FST 구성 중에 suffix(접미사)를 공유하기 위해 사용하는 RAM을 대략적으로 제한할 수 있습니다.

#### 최적화
1. 톱 레벨 조건 검색에서 socre에 대해 내림차순 정렬을 할 때, 더 빨라진 텀쿼리 변경
2. Lucene99PostingFormat에서 Postings을 다시 FOR로 사용하도록 변경하였습니다. 빈도, 위치 그리고 오프셋은 계속해서 PFOR를 사용합니다.

새로운 기능과 변화된 부분에 대한 전체 목록을 확인하려면 CHANGES.txt를 읽어주세요.
> https://lucene.apache.org/core/9_9_0/changes/Changes.html

* FST : FST는 "Finite State Transducer"의 약어로, 한정된 상태 변환기를 나타냅니다. 
이는 유한 상태 오토마타(Finite State Automaton)의 변형으로, 입력과 출력 간의 상태 변환을 표현하는 자료 구조입니다.
주로 문자열 매칭, 텍스트 검색, 압축, 자연어 처리 등 다양한 응용 분야에서 사용됩니다.
* HNSW : HNSW는 "Hierarchical Navigable Small World"의 약자로, 고차원 벡터 데이터셋에서 효율적인 최근접 이웃 검색을 위한 
그래프 기반의 인덱스 구조를 나타냅니다. HNSW는 대량의 벡터를 저장하고 빠르게 검색할 수 있도록 설계된 알고리즘 중 하나입니다. 
따라서 "HNSW 벡터 형식"은 HNSW 알고리즘에서 사용되는 벡터 데이터의 표현 형식을 나타냅니다. 이 표현 형식은 int8 스칼라 
양자화와 같은 기술을 사용하여 벡터를 효율적으로 저장하고 검색하는 방식을 설명하고 있습니다.
