package com.jsp.chap01;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.time.LocalDateTime;

// 역할: http 요청 응답 처리에서 필요한 공통적인 부분을 쉽게 해결해 주는 자바 API 를 이용한 클래스

// 언제 동작할지를 정해주어야함 @WebServlet
@WebServlet("/hello") // 우리 웹 서버에 /hello 라는 URL 요청이 오면 이 서블릿을 실행시켜라!
public class HelloServlet extends HttpServlet { // HttpServlet 을 상속받습니다


     public HelloServlet() {
         // 요청이 들어오면 서블릿 객체가 서버 내에서 자동 생성됩니다.
         System.out.println("\n\n\n HELLO 서블릿 작동시작! \n\n\n");
     }

     // 요청 정보 얻어보기
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청이 들어오면 자동으로 실행되는 메서드 입니다.
        // 매개값으로 요청에 대한 정보가 담긴 HttpServletRequest 와
        // 응답에 대한 정보 및 기능이 담긴 HttpServletResponse 를 (서버로부터) 전달 받습니다.

        // 클라이언트 요청 방식
        String method = request.getMethod();

        // 요청 URL
        String requestURI = request.getRequestURI();

        // 요청 헤더 읽기
        String header = request.getHeader("Cache-Control");

        System.out.println("method = " + method);
        System.out.println("requestURI = " + requestURI);
        System.out.println("header = " + header);

        // 요청과 함께 넘어온 데이터 (쿼리 파라미터) 읽기
        String nick = request.getParameter("nick");
        String age = request.getParameter("age");
        System.out.println("nick = " + nick);
        System.out.println("age = " + age);

        // 응답 메세지에 HTML 문서를 생성해서 응답하기
        // nick 님은 xxxx 년생입니다.

        // 출생 년도를 구하는 비즈니스 로직
        int year = LocalDateTime.now().getYear();
        int birthYear = year - Integer.parseInt(age);

        // 서블릿 클래스에서 HTML 파일 생성방법
        response.setContentType("text/html"); // text 로 이루어진 html 파일
        response.setCharacterEncoding("UTF-8");

        // HTML 문서를 작성할 writer 객체를 생성.
        PrintWriter w = response.getWriter(); // html 작성가능

        w.write("<!DOCTYPE html>\n");
        w.write("<html>\n");
        w.write("<head>\n");
        w.write("</head>\n");

        w.write("<body>\n");
        w.write("<h1>\n");
        w.write(String.format("%s님은 %d년생입니다.\n", nick, birthYear));
        w.write("</h1>\n");
        w.write("</body>\n");

        w.write("</html>\n");

        w.flush(); // 브라우저로 지금까지 작성한 내용들을 내보낸 후 메모리 비우기.
        w.close();

    }
}
