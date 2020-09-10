# 도메인 & 엔티티 & 테이블 설계

## 도메인

![image](https://user-images.githubusercontent.com/43431081/92700070-59bf4d80-f389-11ea-9dfc-42a61f2e3714.png)

<br>

## 엔티티

### Ubiquitous Language(보편 언어)

* *회원 엔티티*

  | 한글               | 영어            |
  | ------------------ | --------------- |
  | 회원               | Member          |
  | 이름               | Name            |
  | 사진               | Photo           |
  | 이메일             | Email           |
  | 비밀번호           | Password        |
  | 관심사             | Interests       |
  | 팔로우             | Following       |
  | 팔로워             | Followers       |
  | 한줄 소개          | Introduction    |
  | 총 받은 좋아요 수  | CountOfLikes    |
  | 작성한 피드들      | WrittenFeeds    |
  | 작성한 댓글들      | WrittenComments |
  | 좋아요를 준 피드들 | LikedFeeds      |
  | 좋아요를 준 댓글들 | LikedComments   |

  <br>

* *피드 엔티티*

  | 한글               | 영어            |
  | ------------------ | --------------- |
  | 피드               | Feed            |
  | 작성자             | Writer          |
  | 요약               | Summary         |
  | 메인 코드 블록     | MainCode        |
  | 내용               | Content         |
  | 관련 태그          | RelatedTags     |
  | 작성 날짜/시간     | CreatedDateTime |
  | 댓글들             | Comments        |
  | 좋아요를 준 회원들 | LikedUsers      |

  <br>

* *댓글 엔티티*

  | 한글               | 영어                |
  | ------------------ | ------------------- |
  | 댓글               | Comment             |
  | 댓글 단 코드 번호  | CommentedCodeNumber |
  | 작성자             | Writer              |
  | 답글들             | Replies             |
  | 작성 내용          | Content             |
  | 좋아요를 준 회원들 | LikedUsers          |
  | 작성 날짜/시간     | CreatedDateTime     |

  <br>

* *해시 태그 엔티티*

  | 한글      | 영어          |
  | --------- | ------------- |
  | 해시 태그 | Hashtag       |
  | 이름      | Name          |
  | 태깅 수   | CountOfTagged |

<br>

### 다이어그램

![image](https://user-images.githubusercontent.com/43431081/92700228-8b381900-f389-11ea-9466-c25d9eb6a637.png)

<br>

## 테이블

