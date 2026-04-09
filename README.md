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


