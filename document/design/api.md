# API 설계

- [x] **POST /api/accounts/login** : 로그인
- [x] **POST /api/accounts/logout** : 로그아웃
- [x] **POST /api/accounts/signup** : 회원가입
  - [x] **POST /api/accounts/signup/email** : 이메일 인증코드 전송 요청
  - [x] **POST /api/accounts/signup/code** : 이메일 인증코드 인증 요청
- [ ] **POST /api/accounts/edit/photo** : 프로필 사진 업로드(수정)
- [ ] **DELETE /api/accounts/edit/photo** : 프로필 사진 삭제
- [ ] **PUT /api/accounts/edit/profile** : 프로필 수정(이름, 웹사이트, 소개, 이메일 공개 여부)
- [x] **PUT /api/accounts/edit/password** : 비밀번호 수정
- [ ] **POST /api/feeds** : 피드 생성
- [ ] **GET /api/feeds/{feedId}** : 해당 피드 조회
- [ ] **DELETE /api/feeds/{feedId}** : 해당 피드 삭제
- [ ] **PUT /api/feeds/{feedId}** : 해당 피드 수정
- [ ] **POST /api/feeds/{feedId}/comments** : 해당 피드의 댓글 작성
- [ ] **GET /api/feeds/{feedId}/comments** : 해당 피드의 댓글 조회
- [ ] **DELETE /api/feeds/{feedId}/comments/{commentId}** : 해당 댓글을 삭제
- [ ] **PUT /api/feeds/{feedIdd}/comments/{commentId}** : 해당 댓글 수정
- [ ] **GET /api/members/{memberName}** : 해당 회원의 별명과 사진 조회
- [ ] **GET /api/members/{memberName}/feeds** : 해당 회원의 관심사 피드들 조회
- [ ] **GET /api/members/{memberName}/profile** : 해당 회원의 프로필 조회
- [ ] **GET /api/members/{memberName}/following** : 해당 회원 팔로우 조회
- [ ] **GET /api/members/{memberName}/followers** : 해당 회원의 팔로워 조회
- [ ] **GET /api/members/{memberName}/history** : 해당 회원의 활동들 조회
- [ ] **POST /api/members/{memberName}/feeds/{feedId}/like** : 해당 회원이 해당 피드의 좋아요 생성
- [ ] **DELETE /api/members/{memberName}/feeds/{feedId}/like** : 해당 회원이 해당 피드의 좋아요 삭제
- [ ] **POST /api/members/{memberName}/comments/{commentId}/like** : 해당 회원이 해당 댓글의 좋아요 생성
- [ ] **DELETE /api/members/{memberName}/comments/{commentId}/like** : 해당 회원이 해당 댓글의 좋아요 삭제
- [ ] **GET /api/feeds** : 최근에 올라온 순으로 피드들 조회
- [x] **POST /api/hashtags** : 해시태그 생성
- [ ] **GET /api/hashtags/{tagName}/feeds** : 해당 태그의 게시물 조회

<br>

## POST /api/accounts/login

로그인

* **request**

  ```javascript
  {
    "email": "string",    // 이메일
    "password": "string"  // 비밀번호
  }
  ```

* **response**

  * 성공 시

    * `200`

    * `JSON`

      ```javascript
      {
        "name": "string",  // 이름(별명)
        "photo": "string"  // 사진(프로필사진)
      }
      ```

  * `400` : POST 요청이 아닐때, 이메일이나 비밀번호가 비어서 요청되었을 때

  * `401` : 이메일이 존재하지 않을 때, 비밀번호가 일치하지 않을 때

<br>

## GET /api/accounts/logout

로그아웃

* **request**

* **response**
  * `200` : 성공
  * `403` : 로그인이 안되어있음

<br>

## POST /api/accounts/signup

회원가입

* **request**

  ```javascript
  {
    "email": "string",    // 이메일
    "name": "string",     // 이름(별명)
    "password": "string"  // 비밀번호
    "interest": [         // 관심사 리스트(해시태그 리스트)
    	"string"
    ]
  }
  ```

* **response**

  * `200` : 성공
  * `400` : 잘못된 형식 존재, 저장되어 있지 않은 해시태그 요청
  * `401` : 이메일이 인증이 안되어있거나, 인증받은지 5분이 지났을 때
  * `409` : 이메일이나 이름이 이미 존재

<br>

## POST /api/accounts/signup/email

이메일 인증코드 전송 요청

* **request**

  ```javascript
  {
    "email": "string"
  }
  ```

* **response**

  * `200` : 성공
  * `400` : 이메일 형식 에러
  * `409` : 이메일이 이미 존재

<br>

## POST /api/accounts/signup/code

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

<br>

## PUT /api/accounts/edit/password

비밀번호 수정

* **request**

  ```javascript
  {
    "beforePassword": "String",
    "newPassword": "String",
    "checkNewPassword": "String"
  }
  ```

* **response**

  * `200` : 성공
  * `400` : 데이터 형식 에러, 새 비밀번호와 새 비밀번호 확인 불일치
  * `401` : 비 로그인

<br>

## POST /api/hashtags

해시태그 생성

* **request**

  ```javascript
  {
    "name": "String"
  }
  ```

* **response**

  * `200` : 성공
  * `409` : 해시태그가 이미 존재