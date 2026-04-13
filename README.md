# ERD
<img width="1074" height="236" alt="Image" src="https://github.com/user-attachments/assets/b0e7f136-2a68-41db-947d-e3a6bef602aa" />  
일정 - 댓글

### 일정 생성 API입니다.  
목적: 새로운 일정을 등록합니다.  
메서드: POST  
엔드포인트: http://localhost:8080/schedules  
요청 파라미터: 없음  
요청 바디: 
- scheduleName: 일정 이름  
- scheduleContents: 일정 내용  
- author: 작성자 이름  
- password: 일정 비밀번호  
  
성공 응답: 요청이 성공하면 생성된 일정 정보 또는 생성 완료 결과를 포함한 응답을 반환합니다.  

---

### 작성자 기준 일정 전체 조회 API입니다.  
목적: 해당 작성자가 등록한 전체 일정 목록을 조회합니다.  
메서드: GET  
엔드포인트: http://localhost:8080/schedules?author="Name"  
요청 파라미터: author (key) - 조회할 작성자의 Name  
요청 바디: 없음  
성공 응답: 요청이 성공하면 작성자가 작성한 전체 일정 목록이 포함된 응답을 반환합니다.  

---

### 일정 단 건 조회 API입니다.  
목적: ID를 기준으로 특정 일정 1건을 조회합니다.  
메서드: GET  
엔드포인트: http://localhost:8080/schedules/{scheduleId}  
요청 파라미터: scheduleId (path) - 조회할 일정의 ID  
요청 바디: 없음  
성공 응답: 요청이 성공하면 해당 ID의 일정 상세 정보를 반환합니다.  

---

### 일정 수정 API입니다. 
목적: ID를 기준으로 특정 캘린더 일정 1건을 수정합니다.  
메서드: PUT  
엔드포인트: http://localhost:8080/schedules/{scheduleId}  
요청 파라미터: scheduleId (path) - 수정할 캘린더 일정의 ID  
요청 바디:  
- scheduleName: 일정 제목  
- author: 작성자 이름  
- password: 일정 비밀번호  
성공 응답: 요청이 성공하면 해당 ID의 캘린더 일정 상세 정보를 반환합니다.  

---

### 일정 삭제 API입니다.  
목적: ID를 기준으로 특정 캘린더 일정 1건을 삭제합니다.  
메서드: DELETE  
엔드포인트: http://localhost:8080/schedules/{scheduleId}  
요청 파라미터: scheduleId (path) - 삭제할 캘린더 일정의 ID  
요청 바디: 없음  
성공 응답: 요청이 성공하면 빈 응답을 반환합니다.  

---

### 댓글 생성 API입니다.
목적: 새로운 댓글을 등록합니다.  
메서드: POST  
엔드포인트: http://localhost:8080/schedules/{scheduleId}/comments  
요청 파라미터: 없음  
요청 바디:
- commentContents: 댓글 내용
- author: 작성자 이름
- password: 댓글 비밀번호

성공 응답: 요청이 성공하면 생성된 댓글 정보 또는 생성 완료 결과를 포함한 응답을 반환합니다.

---

### 프로젝트 구조
```
main
├─java
│  └─com
│      └─example
│          └─schedule
│              ├─controller
│              ├─dto
│              ├─entity
│              ├─exception
│              ├─repository
│              └─service
└─resources
      └─application.properties
```

# 3-Layer-Architecture
---
- Controller : 클라이언트의 요청을 받고 응답을 주는 역할(책임)
- Service : 클라이언트의 요청에 따른 비즈니스 로직을 수행하는 역할(책임)
- Repository : 데이터베이스와의 상호작용을 수행해주는 역할(책임)
만약에 문제가 발생하였을 경우 어디서 발생하였는지가 명확해지고, 어느 계층을 수정해도 다른 계층에 영향을 주지 않게 하기위해서
즉, 유지보수의 편의성이 높아지고 구조가 투명해지기때문에 이런 구조를 사용한다.

# 어노테이션들
---
- @RequestParam : 주소 끝에 쿼리 스트링에서 값을 뽑아내주는 역할을 한다.
- @PathVariable : 주소의 경로에 있는 식별자를 뽑아내주는 역할을 한다.
- @RequestBody : 요청 HTTP 본문에 있는 JSON 데이터를 DTO로 만들어주는 역할을 한다.