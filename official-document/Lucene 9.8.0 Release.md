## 2023 9월 28일 Apache Lucene™ 9.8.0 available
> https://lucene.apache.org/core/corenews.html#apache-lucenetm-980-available

### Lucene 9.8.0 Release Highlights:

#### 최적화

1. **루씬 9.7 이후 버전에서 불리언(AND/OR) 쿼리의 상위 k개 검색 결과를 더 빠르게 계산하는 최적화** 
    - 루씬의 벤치마크 보고에서 분리 질의(OR 연산 결합)에 대해 20%-30%의 속도 향상이, 교차 질의(AND 연산 결합)에 대해 11-13% 속도 향상이 있었습니다.
    - AND/OR 조건 및 높은 빈도로 사용되는 용어가 포함된 분리 질의의 경우 특히 더 높은 속도 향상이 기대됩니다.

2. **필드별로 정렬할 때 상위 k개 검색 결과를 더 빠르게 계산하는 최적화**
    - 루씬 9.7 이후 버전에서 필드 유형 및 집합에 따라 7%~33%의 검색 속도 향상이 이루어졌습니다.

3. **인덱스 정렬이 활성화되었을 때 숫자형 문서 값의 빠른 색인화**
    - 인덱스 정렬이 활성화된 상태에서 숫자형 문서 값의 색인화가 빨라졌습니다.

4. **표현식 평가 최적화**
    - 이제 표현식은 모든 인수를 완전히 게으르게(full lazy) 평가하며, 이는 표현식을 사용하는 사용자에게 상당한 속도 향상과 처리량 향상을 제공할 수 있습니다.

#### API 변경 사항
- 최대 벡터 차원 제한(max vector dims limit)을 Codec으로 이동시켰습니다.(Mayya Sharipova)

#### 새로운 기능
1. LeafCollector#finish 도입: 리프에서 수집이 완료된 후 실행되는 후크를 도입했습니다.
2. "KnnCollector"를 "LeafReader" 및 "KnnVectorReader"에 추가하여 벡터 검색 결과의 사용자 정의 수집을 제공할 수 있도록 하였습니다
첫 번째 사용자 정의 수집기는 자식 벡터 문서를 부모 문서와 결합하는 "ToParentBlockJoin[Float|Byte]KnnVectorQuery"를 제공합니다.
3. 재귀 그래프 이분화(recursive graph bisection) 지원 추가, BP로도 알려진 이 알고리즘은 문서 ID 재정렬을 수행하여 더 압축된 포스팅(posting)과 빠른 질의(특히 교차 질의)를 할 수 있게 하였습니다.

#### 버그 수정
1. HNSW 그래프 검색 버그 수정, 잠재적으로 비승인된 문서를 누출할 수 있는 문제를 해결했습니다.
2. doc values 용어 열거형에서 IndexOutOfBoundException을 일으키는 버그를 수정했습니다.

새로운 기능과 변화된 부분에 대한 전체 목록을 확인하려면 CHANGES.txt를 읽어주세요.
> https://lucene.apache.org/core/9_8_0/changes/Changes.html
