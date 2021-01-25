package com.example.mybatisdemo.Responese;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatisdemo.domin.Person;
import com.example.mybatisdemo.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        PrintWriter writer = null;
        Person person = (Person)request.getSession().getAttribute("person");
        logger.info("请求路径");
        logger.info(request.getRequestURL().toString());
        if (person == null) {
            ResultVO resultVO = MyResponese.error("用户未登录");
            returnJson(response, resultVO);
            return  false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("我是postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("我是afterCompletion");
    }
    private void returnJson(HttpServletResponse response, ResultVO res){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            writer = response.getWriter();
            writer.print(JSONObject.toJSONString(res));
        } catch (IOException e) {
            logger.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
