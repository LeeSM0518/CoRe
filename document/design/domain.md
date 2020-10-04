# 도메인 & 엔티티 & 테이블 설계

- [ ] 다이어그램 & 테이블 업데이트

<br>

## 도메인

![image](https://user-images.githubusercontent.com/43431081/92700070-59bf4d80-f389-11ea-9dfc-42a61f2e3714.png)

<br>

## 엔티티

### Ubiquitous Language(보편 언어)

* *회원 엔티티*

  | 한글               | 영어            |
  | ------------------ | --------------- |
  | 회원               | member          |
  | 이름               | name            |
  | 사진               | photo           |
  | 이메일             | email           |
  | 이메일 공개 여부   | isPublicEmail   |
  | 비밀번호           | password        |
  | 관심사             | interests       |
  | 팔로우             | following       |
  | 팔로워             | followers       |
  | 한줄 소개          | introduction    |
  | 총 받은 좋아요 수  | countOfLikes    |
  | 웹사이트           | website         |
  | 작성한 피드들      | writtenFeeds    |
  | 작성한 댓글들      | writtenComments |
  | 좋아요를 준 피드들 | likedFeeds      |
  | 좋아요를 준 댓글들 | likedComments   |

  <br>

* *피드 엔티티*

  | 한글               | 영어            |
  | ------------------ | --------------- |
  | 피드               | feed            |
  | 작성자             | writer          |
  | 요약               | title           |
  | 메인 코드 블록     | mainCode        |
  | 내용               | content         |
  | 관련 태그          | relatedTags     |
  | 작성 날짜/시간     | createdDateTime |
  | 댓글들             | comments        |
  | 좋아요를 준 회원들 | membersWhoLike  |

  <br>

* *댓글 엔티티*

  | 한글               | 영어                |
  | ------------------ | ------------------- |
  | 댓글               | comment             |
  | 댓글 단 코드 번호  | commentedCodeNumber |
  | 작성자             | writer              |
  | 댓글이 작성된 피드 | writtenFeed         |
  | 답글이 달린 댓글   | replied             |
  | 답글들             | replies             |
  | 작성 내용          | content             |
  | 좋아요를 준 회원들 | membersWhoLike      |
  | 작성 날짜/시간     | createdDateTime     |

  <br>

* *해시 태그 엔티티*

  | 한글      | 영어          |
  | --------- | ------------- |
  | 해시 태그 | hashtag       |
  | 이름      | name          |
  | 태깅 수   | countOfTagged |

<br>

### 다이어그램

![image](https://user-images.githubusercontent.com/43431081/93078465-7929e380-f6c5-11ea-8521-b44fd27eeae5.png)

<br>

## 테이블

![image](https://user-images.githubusercontent.com/43431081/93302203-903b1380-f834-11ea-98c4-9dd5d02595cd.png)