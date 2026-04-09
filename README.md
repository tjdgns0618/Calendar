# ERD
<img width="881" height="294" alt="Image" src="https://github.com/user-attachments/assets/5a38d27f-d74f-4ba1-8445-907d63d083a0" />  
일정 - 댓글

### 일정 생성 API입니다.  
목적: 새로운 일정을 등록합니다.  
메서드: POST  
엔드포인트: http://localhost:8080/calendars  
요청 파라미터: 없음  
요청 바디: 
- calendarName: 일정 이름  
- calendarContents: 일정 내용  
- writerName: 작성자 이름  
- password: 일정 비밀번호  
  
성공 응답: 요청이 성공하면 생성된 일정 정보 또는 생성 완료 결과를 포함한 응답을 반환합니다.  

---

### 작성자 기준 일정 전체 조회 API입니다.  
목적: 해당 작성자가 등록한 전체 일정 목록을 조회합니다.  
메서드: GET  
엔드포인트: http://localhost:8080/calendars?writerName="Name"  
요청 파라미터: writerName (key) - 조회할 작성자의 Name  
요청 바디: 없음  
성공 응답: 요청이 성공하면 작성자가 작성한 전체 일정 목록이 포함된 응답을 반환합니다.  

---

### 일정 단 건 조회 API입니다.  
목적: ID를 기준으로 특정 일정 1건을 조회합니다.  
메서드: GET  
엔드포인트: http://localhost:8080/calendars/{calendarId}  
요청 파라미터: calendarId (path) - 조회할 일정의 ID  
요청 바디: 없음  
성공 응답: 요청이 성공하면 해당 ID의 일정 상세 정보를 반환합니다.  

---

### 일정 수정 API입니다. 
목적: ID를 기준으로 특정 캘린더 일정 1건을 수정합니다.  
메서드: PUT  
엔드포인트: http://localhost:8080/calendars/{id}  
요청 파라미터: id (path) - 수정할 캘린더 일정의 ID  
요청 바디:  
- calendarName: 일정 제목  
- writerName: 작성자 이름  
- password: 일정 비밀번호  
성공 응답: 요청이 성공하면 해당 ID의 캘린더 일정 상세 정보를 반환합니다.  

---

### 일정 삭제 API입니다.  
목적: ID를 기준으로 특정 캘린더 일정 1건을 삭제합니다.  
메서드: DELETE  
엔드포인트: http://localhost:8080/calendars/{id}  
요청 파라미터: id (path) - 삭제할 캘린더 일정의 ID  
요청 바디: 없음  
성공 응답: 요청이 성공하면 빈 응답을 반환합니다.  

