~~~python
#서버가 내려줘야할 작업들

서버 기본 주소: https://domainAPI/

로그인: 
[POST] /login #사용자 인증검사 및 세션 생성
[POST] /logout #세션 제거

회원가입:
[POST] /join #중복검사 및 사용자 등록 절차

프로필:
[GET] /{LoginId} #해당 회원 프로필 데이터 반환
[PATCH] /{LoginId} #해당 회원 프로필 데이터 수정

게시판:
[GET] /posts # 게시물 리스트 반환
[GET] /posts/{postId} #해당 게시물 데이터 반환






~~~

