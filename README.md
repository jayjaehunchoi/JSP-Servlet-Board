# 💻 JSP BOARD 💻

> **2021-10-09 ~ 2021-10-15**   
> JSP Servlet을 이용하여 MVC 모델 기반으로 설계 및 구현한 게시판입니다.    
> 심플하게 CRUD 기능 + Naver 검색 api만 구현한 게시판 Web 프로젝트이지만  
> validation 과 database 설계, MVC 모델, 상속에 대해 많이 고민해볼 수 있었습니다. 

## ⌨️ Project Intro ⌨️
### What I considered
* 객체의 생명주기에 따른 테이블 간 적절한 의존 관계 설정
* ``` CRUD ``` 구현 시 서버 개발자가 고려해야 할 ``` validation ``` ,``` validation ```의 패키지, 클래스 분리에 대한 고민이 있었음.
*  ``` servlet, jsp ``` 기반의 개발이기 때문에 ``` form data ``` 를 주고 받는 형태로 개발할 수 있었지만 ``` ajax ```를 사용해서 ``` HTTP body ```에 전달되는 ``` raw data ```를 처리하는 방법에 대해 고민 및 적용하였음. 
*  ``` POST ``` 메서드 저장, 수정 로직 수행 후 ``` RequestDispatcher.forward(request, response)``` 가 사용된다면 발생하는 중복 문제 해결 방안에 대해 고민하고 개발하였음.  
*  ``` HttpSession ```의 활용성에 대해 고민하고 개발에 적용하였음 <u>로그인, 페이지 매핑, 목록 등</u>
*  비밀번호 암호화 방안에 대해 고민하고 최종적으로 ``` Sha-256 알고리즘 , Salt```를 통해 암호화를 실시하였음.
*  Naver 검색 api를 활용한 ``` search engine ``` 구현에 상속과 다형성을 고려하여 클래스를 설계하였음.
*  ``` template engine ```과 ``` servlet ```의 완전 분리를 지향하였음.

### Service
* Join / Login (CRUD)
* Board, Comment (CRUD)
* File Upload and Download
* My Page (Recent article, Info update)
* Naver Search api

## ⚙️ Requirements ⚙️
* Java Version 8
* JSP
* MySQL Version 8.0.23
* Jars : ``` ojdbc8.jar ``` ,``` cos.jar ```,``` jstl-1.2.jar ```,``` json-20210307.jar ```

## 📁 Project Directories 📁
```
├─domain
│  ├─bbs
│  │      Bbs.java
│  │      BbsRepository.java
│  │
│  ├─comment
│  │      Comment.java
│  │      CommentRepository.java
│  │
│  ├─file
│  │      MyFile.java
│  │      MyFileRepository.java
│  │
│  └─member
│          Member.java
│          MemberRepository.java
│
├─service
│  │  BbsService.java
│  │  CommentService.java
│  │  MemberService.java
│  │  MyFileService.java
│  │  SearchService.java
│  │
│  └─searchServiceImpl
│          SearchBlogService.java
│          SearchCafeService.java
│          SearchKinService.java
│          SearchLocalService.java
│
├─utils
│      JdbcTemplate.java
│      MemberConst.java
│      SearchConst.java
│      ShaEncoder.java
│
└─web
    │  MyView.java
    │
    ├─controller
    │  │  MemberFrontController.java
    │  │  SearchController.java
    │  │
    │  ├─bbs
    │  │      BoardDetailController.java
    │  │      BoardDownloadController.java
    │  │      BoardListController.java
    │  │      BoardsDeleteController.java
    │  │      BoardUpdateController.java
    │  │      BoardWriteController.java
    │  │
    │  ├─comments
    │  │      CommentDeleteController.java
    │  │      CommentSaveController.java
    │  │
    │  └─member
    │          MemberController.java
    │          MemberIdCheckController.java
    │          MemberIdDuplicateCheckController.java
    │          MemberJoinController.java
    │          MemberLoginController.java
    │          MemberLoginCorrectController.java
    │          MemberLogoutController.java
    │          MemberMyPageController.java
    │          MemberSaveController.java
    │          MemberUpdateController.java
    │          MemberWelcomeController.java
    │          MemberWithdrawlController.java
    │
    └─form
            MemberForm.java
            SearchForm.java
```

## 🛠 ERD 🛠
![image](https://user-images.githubusercontent.com/87312401/137659000-72a44bf3-8f6f-46d3-a2c2-4225ae4d7bd5.png)

## 📖 To Be Updated 📖
* 현재는 members 테이블의 ``` Delete ``` 로직에 실제 데이터가 삭제되지만, 추후 ``` status column``` 을 두고 db에 삭제 데이터 보관하는 방식 고려 중
* ``` File update ``` 기능 구현 
* ``` Spring MVC ``` 모델로 전환
