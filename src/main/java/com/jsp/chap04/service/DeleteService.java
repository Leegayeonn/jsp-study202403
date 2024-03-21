package com.jsp.chap04.service;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class DeleteService implements IDancerService {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        String name = request.getParameter("name");
        DancerRepository.delete(name);// 삭제완료

        // 삭제가 완료된 후에 삭제가 적용된 댄서 목록을 list.jsp 에 보여주고 싶어요.
        List<Dancer> dancerList = DancerRepository.findAll();

        // list.jsp 를 그대로 활용할 예정이라 dl 이라는 이름을 똑같이 지정해야 합니다.
        request.setAttribute("dl", dancerList);

    }
}
