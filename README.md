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
![image](https://user-images.githubusercontent.com/87312401/137659315-6db31234-856a-4849-b0c1-96150920ca92.png)

## 📃 SQL 📃
```mysql
CREATE TABLE `members` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `member_login_id` varchar(30) DEFAULT NULL,
  `member_password` varchar(100) DEFAULT NULL,
  `member_email` varchar(30) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `member_login_id` (`member_login_id`),
  UNIQUE KEY `member_email` (`member_email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--==============================================================================================================

CREATE TABLE `bbs` (
  `bbs_id` int NOT NULL,
  `bbs_title` varchar(100) NOT NULL,
  `member_id` int NOT NULL,
  `bbs_date` timestamp NOT NULL,
  `bbs_content` text NOT NULL,
  PRIMARY KEY (`bbs_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `bbs_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--==============================================================================================================

CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `bbs_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `comment_content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comments_bbs` (`bbs_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comments_bbs` FOREIGN KEY (`bbs_id`) REFERENCES `bbs` (`bbs_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--==============================================================================================================

CREATE TABLE `files` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `bbs_id` int DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_real_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  KEY `fk_files_bbs` (`bbs_id`),
  CONSTRAINT `fk_files_bbs` FOREIGN KEY (`bbs_id`) REFERENCES `bbs` (`bbs_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## 📖 To Be Updated 📖
* 현재는 members 테이블의 ``` Delete ``` 로직에 실제 데이터가 삭제되지만, 추후 ``` status column``` 을 두고 db에 삭제 데이터 보관하는 방식 고려 중
* ``` File update ``` 기능 구현 
* ``` Spring MVC ``` 모델로 전환
