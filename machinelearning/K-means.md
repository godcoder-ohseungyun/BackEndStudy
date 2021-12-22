## [머신러닝]K-means

---

+ 군집화 알고리즘
+ 여러개의 데이터를 적절히 묶어서 나타내기 위한 목적으로 사용된다.



쉽게 말해 **데이터를 K개의 군집(Cluster)으로 묶는(Clusting) 알고리즘**이다.

K-means 알고리즘에서 K는 묶을 군집(클러스터)의 개수를 의미하고 means는 평균을 의미한다.

> 각 군집의 평균을 활용해 **K개의 군집으로 군집화**한다.
>
> 여기서 **평균은 각 클러스터의 중심과 데이터의 평균 거리를 의미**



K-means 알고리즘은 가깝게 위치하는 데이터를 비슷한 특성을 지닌 데이터로 여기고 같은 군집으로 군집화한다.

다음과 같은 데이터들이 주어졌을 때

<img src="https://user-images.githubusercontent.com/68331041/146630641-14319195-fbf4-4d08-9689-54d441cc9152.png" alt="image" style="zoom:25%;" />

아래와 같이 군집화 된다.

<img src="https://user-images.githubusercontent.com/68331041/146630649-81b635be-a60a-46b5-b025-765f4e23bc85.png" alt="image" style="zoom:25%;" />



### K-means 알고리즘 원리

~~~
1. 군집의 개수(K) 설정하기
2. 초기 중심점 설정
3. 데이터를 군집에 할당(배정)
4. 중심점 재설정(갱신)
5. 데이터를 군집에 재할당(배정)
6. 중심점 위치가 더 이상 변하지 않을때까지 4,5 과정 반복
~~~



#### step 1) 군집의 개수(K) 설정하기

데이터를 준비한 뒤 **몇 개의 군집으로 군집화할지**는 사람이 정해야한다. 

이는 K-means 알고리즘의 **한계점 중에 하나**로  군집의 개수 설정을 어떻게 하냐에 따라 결과가 크게 달라지며 터무니 없는 결과가 나올 수도 있다는 것이다. 

그래서 **군집의 개수를 설정하는 방법론**으로 다음과 같은 몇 가지 방법들이 존재한다.

1) Rule of thumb
2) Elbow Method
3) 정보 기준 접근법 (Information Criterion Approach)



#### step 2) 초기 중심점 설정하기

그 다음 **K개의 초기 중심점(Center of Cluster, Centroid)을 설정하는 단계**이다. 

k-means 알고리즘은 초기 중심점으로 어떤 값을 선택하는가에 따라 성능이 크게 달라지는 성질을 가지고 있다고 한다. 따라서 초기 중심 값을 잘 설정해야하며 다음과 같은 몇가지 방법들이 있다.

1) Randomly select
2) Manually assign
3) **K-means++**



#### step 3) 데이터를 군집에 할당(배정)하기

그 다음 거리 상 가장 가까운 군집(중심점)으로 주어진 모든 데이터를 할당 또는 배정한다.



#### step 4) 중심점 재설정(갱신)하기

모든 주어진 데이터의 군집 배정이 끝나면 군집의 중심점(Centroid)을 그 군집의 속하는 데이터들의 가장 중간(평균)에 위치한 지점으로 재설정한다.



#### step 5) 데이터를 군집에 재할당(배정)하기

step 3에서 했던 방법과 똑같이 시행하며, 더 이상 중심점의 이동이 없을 때까지 step 4와 step 5를 반복한다.



### 예제로 확인하기 

<img src="https://user-images.githubusercontent.com/68331041/146633489-fc90e838-a8c9-4b0f-8da1-6678a6263e17.png" alt="image" style="zoom:25%;" />

다음과 같은 6개의 데이터가 있다. 군집의 개수(K) = 3 으로 설정

<img src="https://user-images.githubusercontent.com/68331041/146633496-666f0372-2eae-4f3f-a968-a7a7d2466048.png" alt="image" style="zoom:25%;" />

초기 중심점을 설정한다. 

<img src="https://user-images.githubusercontent.com/68331041/146633504-cb4013a9-4a5b-4cdf-bf21-5e6e532e1b8e.png" alt="image" style="zoom:25%;" />

일반 적으로 유클리드 거리가 가장 가까운 데이터를 해당 군집으로 배정한다.

> 데이터1 은 C1 군집으로 배정된다.

나머지 데이터 2~6도 같은 방식으로 배정된다.

<img src="https://user-images.githubusercontent.com/68331041/146633516-64b6849c-a553-4e9d-8386-3a6bb0699b31.png" alt="image" style="zoom:25%;" />

중심점을 각 군집에 속하는 데이터들의 중간으로 재설정한다.

<img src="https://user-images.githubusercontent.com/68331041/146633521-b04ebc2a-997b-4d19-93c8-e570c2b14f7b.png" alt="image" style="zoom:25%;" />

중심점이 더 이상 변화하지 않을때까지 중심점 재설정과 재배정 과정을 반복해야 하지만 예제에서는 중심점이 더이상 변화하지 않기 때문에 클러스터링이 종료된다.

최종 군집화 결과이다.



### 실습

[실습정리문서링크](https://github.com/godcoder-ohseungyun/BackEndStudy/blob/master/machinelearning/kmean.ipynb)



## Reference

---

[머신러닝-k-means](https://velog.io/@jhlee508/%EB%A8%B8%EC%8B%A0%EB%9F%AC%EB%8B%9D-K-%ED%8F%89%EA%B7%A0K-Means-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)

https://www.youtube.com/watch?v=sXgQsJLDP8g
