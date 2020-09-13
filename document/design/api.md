# API 설계

- [ ] POST /accounts/login : 로그인
- [x] POST /accounts/signup : 회원가입
  - [x] POST /accounts/signup/email : 이메일 인증코드 전송 요청
  - [x] POST /accounts/signup/code : 이메일 인증코드 인증 요청
- [ ] POST /accounts/edit : 회원정보수정
- [ ] POST /accounts/password/change : 비밀번호 수정
- [ ] POST /feeds : 피드 작성
- [ ] GET /feeds/{feedId} : 해당 피드 조회
- [ ] DELETE /feeds/{feedId} : 해당 피드 삭제
- [ ] PUT /feeds/{feedId} : 해당 피드 수정
- [ ] POST /feeds/{feedId}/comments : 해당 피드의 댓글 작성
- [ ] GET /feeds/{feedId}/comments : 해당 피드의 댓글 조회
- [ ] DELETE /feeds/{feedId}/comments/{commentId} : 해당 댓글을 삭제
- [ ] PUT /feeds/{feedIdd}/comments/{commentId} : 해당 댓글 수정
- [ ] GET /members/{memberName} : 해당 회원의 별명과 사진 조회
- [ ] GET /members/{memberName}/feeds : 해당 회원의 관심사 피드들 조회
- [ ] GET /members/{memberName}/profile : 해당 회원의 프로필 조회
- [ ] GET /members/{memberName}/following : 해당 회원 팔로우 조회
- [ ] GET /members/{memberName}/followers: 해당 회원의 팔로워 조회
- [ ] GET /members/{memberName}/history: 해당 회원의 활동들 조회
- [ ] POST /members/{memberName}/feeds/{feedId}/like : 해당 회원이 해당 피드의 좋아요 생성
- [ ] DELETE /members/{memberName}/feeds/{feedId}/like : 해당 회원이 해당 피드의 좋아요 삭제
- [ ] POST /members/{memberName}/comments/{commentId}/like : 해당 회원이 해당 댓글의 좋아요 생성
- [ ] DELETE /members/{memberName}/comments/{commentId}/like : 해당 회원이 해당 댓글의 좋아요 삭제
- [ ] GET /feeds : 최근에 올라온 순으로 피드들 조회
- [ ] GET /tags/{tagName}/feeds : 해당 태그의 게시물 조회

<br>

## POST /accounts/signup

회원가입

* **request**

  ```javascript
  {
    "email": "string",
    "name": "string",
    "password": "string"
    "interest": [
    	"string"
    ]
  }
  ```

* **response**

  * `200` : 성공

<br>

## POST /accounts/signup/email

이메일 인증코드 전송 요청

* **request**

  ```javascript
  {
    "email": "string"
  }
  ```

* **response**

  * `200` : 성공
  * `400` : 이메일 형식 오류

<br>

## POST /accounts/signup/code

이메일 인증코드 인증 요청

* **request**

  ```javascript
  {
    "email": "string",
    "code": "string"
  }
  ```

* **response**

  * `200` : 성공
  * `400` : 이메일 형식 에러, 코드 형식 에러
  * `401` : 인증 코드 비일치
  * `404` : 인증 코드를 찾을 수 없음

