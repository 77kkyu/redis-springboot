# Description
- auth : 77kkyu
- development environment : java
- technology : redis, docker
  
> 강대명님의 [우아한 레디스](https://www.youtube.com/watch?v=mPB2CZiAkKM) 를 보시는걸 추천합니다
---

## Redis(Remote Dictionary Server)

``` text
Redis는 모든 데이터를 key-value 로 메모리에 저장하고 조회하는 인 메모리 비관계형 데이터 베이스 관리 시스템 (DBMS)입니다
Redis를 공부하다보면 Memcached랑 많이 비교하게 된다
Memcached에서 제공하는 거의 모든 기능들을 Redis에서도 제공하지만
가장 큰 차이점은 Redis는 다양한 컬렉션을 제공합니다
그리고 Redis서버가 죽어도 Restart될 때 Disk 에 저장해 놓은 데이터를 다시 읽어서 메모리에 로딩하기 때문에 데이터가 유실되지 않습니다
```

### Redis의 기능
- In-Memory 캐싱
- Pub/Sub 메세지 큐
- 세션 스토어

### 데이터 형식
redis는 자료구조를 제공하기 때문에 개발의 편이성이 좋습니다!
- Binary-safe strings
- Lists
- Sets
- Sorted Sets
- Hashes
- Bit array
- HyperLogLogs
- Streams

### 주의사항
- ### **_컬렉션 안에 너무 많은 아이템을 사용하지 않는다_**
    - 너무 많은 아이템을 사용할 시 서능이 저하 된다(속도저하)
- ### **_메모리를 관리하자 (모니터링 필수)_**
    - redis는 자기의 메모리를 정확하게 알지 못합니다
    - 피지컬 메모리 이상으로 사용하면 스왑 메모리를 사용합니다
    - swap 사용하면 메모리 page 접근시마다 읽고 쓰기 때문에 엄청 느려집니다
    - redis가 보통 갑자기 느려졌다하면 이 이유! 
    - max 메모리를 설정하더라도 이보다 더 사용하게됩니다(다른 프로세스들도 메모리를 사용하기 때문)
    - RSS 값을 모니터링 해야합니다
    - 메모리 파편화가 발생하므로 모니터링을 통해서 메모리 관리를 해줘야 합니다
- ### **_Hash, Sorted Set, Set 은 메모리 사용이 많다. Ziplist 를 사용해야 합니다_**
    - 자료구조보다 메모리를 적게 사용합니다!
- ### **_O(n) 관련 명령어에 주의해야합니다_**
    - 오래걸리는 명령어들
      - KEYS
      - FLUSHALL, FLUSHDB
      - Delete Collections
      - Get All Collections

**_등등 많은 주요사항들이 있습니다..._**
  





