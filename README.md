# π» JSP BOARD π»

> **2021-10-09 ~ 2021-10-15**   
> JSP Servletμ μ΄μ©νμ¬ MVC λͺ¨λΈ κΈ°λ°μΌλ‘ μ€κ³ λ° κ΅¬νν κ²μνμλλ€.    
> μ¬ννκ² CRUD κΈ°λ₯ + Naver κ²μ apiλ§ κ΅¬νν κ²μν Web νλ‘μ νΈμ΄μ§λ§  
> validation κ³Ό database μ€κ³, MVC λͺ¨λΈ, μμμ λν΄ λ§μ΄ κ³ λ―Όν΄λ³Ό μ μμμ΅λλ€. 

## β¨οΈ Project Intro β¨οΈ
### What I considered
* κ°μ²΄μ μλͺμ£ΌκΈ°μ λ°λ₯Έ νμ΄λΈ κ° μ μ ν μμ‘΄ κ΄κ³ μ€μ 
* ``` CRUD ``` κ΅¬ν μ μλ² κ°λ°μκ° κ³ λ €ν΄μΌ ν  ``` validation ``` ,``` validation ```μ ν¨ν€μ§, ν΄λμ€ λΆλ¦¬μ λν κ³ λ―Όμ΄ μμμ.
*  ``` servlet, jsp ``` κΈ°λ°μ κ°λ°μ΄κΈ° λλ¬Έμ ``` form data ``` λ₯Ό μ£Όκ³  λ°λ ννλ‘ κ°λ°ν  μ μμμ§λ§ ``` ajax ```λ₯Ό μ¬μ©ν΄μ ``` HTTP body ```μ μ λ¬λλ ``` raw data ```λ₯Ό μ²λ¦¬νλ λ°©λ²μ λν΄ κ³ λ―Ό λ° μ μ©νμμ. 
*  ``` POST ``` λ©μλ μ μ₯, μμ  λ‘μ§ μν ν ``` RequestDispatcher.forward(request, response)``` κ° μ¬μ©λλ€λ©΄ λ°μνλ μ€λ³΅ λ¬Έμ  ν΄κ²° λ°©μμ λν΄ κ³ λ―Όνκ³  κ°λ°νμμ.  
*  ``` HttpSession ```μ νμ©μ±μ λν΄ κ³ λ―Όνκ³  κ°λ°μ μ μ©νμμ <u>λ‘κ·ΈμΈ, νμ΄μ§ λ§€ν, λͺ©λ‘ λ±</u>
*  λΉλ°λ²νΈ μνΈν λ°©μμ λν΄ κ³ λ―Όνκ³  μ΅μ’μ μΌλ‘ ``` Sha-256 μκ³ λ¦¬μ¦ , Salt```λ₯Ό ν΅ν΄ μνΈνλ₯Ό μ€μνμμ.
*  Naver κ²μ apiλ₯Ό νμ©ν ``` search engine ``` κ΅¬νμ μμκ³Ό λ€νμ±μ κ³ λ €νμ¬ ν΄λμ€λ₯Ό μ€κ³νμμ.
*  ``` template engine ```κ³Ό ``` servlet ```μ μμ  λΆλ¦¬λ₯Ό μ§ν₯νμμ.

### Service
* Join / Login (CRUD)
* Board, Comment (CRUD)
* File Upload and Download
* My Page (Recent article, Info update)
* Naver Search api

## βοΈ Requirements βοΈ
* Java Version 8
* JSP
* MySQL Version 8.0.23
* Jars : ``` ojdbc8.jar ``` ,``` cos.jar ```,``` jstl-1.2.jar ```,``` json-20210307.jar ```

## π Project Directories π
```
ββdomain
β  ββbbs
β  β      Bbs.java
β  β      BbsRepository.java
β  β
β  ββcomment
β  β      Comment.java
β  β      CommentRepository.java
β  β
β  ββfile
β  β      MyFile.java
β  β      MyFileRepository.java
β  β
β  ββmember
β          Member.java
β          MemberRepository.java
β
ββservice
β  β  BbsService.java
β  β  CommentService.java
β  β  MemberService.java
β  β  MyFileService.java
β  β  SearchService.java
β  β
β  ββsearchServiceImpl
β          SearchBlogService.java
β          SearchCafeService.java
β          SearchKinService.java
β          SearchLocalService.java
β
ββutils
β      JdbcTemplate.java
β      MemberConst.java
β      SearchConst.java
β      ShaEncoder.java
β
ββweb
    β  MyView.java
    β
    ββcontroller
    β  β  MemberFrontController.java
    β  β  SearchController.java
    β  β
    β  ββbbs
    β  β      BoardDetailController.java
    β  β      BoardDownloadController.java
    β  β      BoardListController.java
    β  β      BoardsDeleteController.java
    β  β      BoardUpdateController.java
    β  β      BoardWriteController.java
    β  β
    β  ββcomments
    β  β      CommentDeleteController.java
    β  β      CommentSaveController.java
    β  β
    β  ββmember
    β          MemberController.java
    β          MemberIdCheckController.java
    β          MemberIdDuplicateCheckController.java
    β          MemberJoinController.java
    β          MemberLoginController.java
    β          MemberLoginCorrectController.java
    β          MemberLogoutController.java
    β          MemberMyPageController.java
    β          MemberSaveController.java
    β          MemberUpdateController.java
    β          MemberWelcomeController.java
    β          MemberWithdrawlController.java
    β
    ββform
            MemberForm.java
            SearchForm.java
```

## π  ERD π 
![image](https://user-images.githubusercontent.com/87312401/137659315-6db31234-856a-4849-b0c1-96150920ca92.png)

## π SQL π
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

## π To Be Updated π
* νμ¬λ members νμ΄λΈμ ``` Delete ``` λ‘μ§μ μ€μ  λ°μ΄ν°κ° μ­μ λμ§λ§, μΆν ``` status column``` μ λκ³  dbμ μ­μ  λ°μ΄ν° λ³΄κ΄νλ λ°©μ κ³ λ € μ€
* ``` File update ``` κΈ°λ₯ κ΅¬ν 
* ``` Spring MVC ``` λͺ¨λΈλ‘ μ ν
