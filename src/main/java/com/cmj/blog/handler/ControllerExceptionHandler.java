package com.cmj.blog.handler;


import com.fasterxml.classmate.Annotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  //表示该方法可以处理所有类型异常
    public ModelAndView Exception(HttpServletRequest request, Exception e) throws Exception {

        //日志打印异常信息
        logger.error("Request url: {}, Exception: {}",request.getRequestURL(),e);

        /*当 标注有@ResponseStatus() 的异常，应该以特殊的方式处理*/
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURI());
        mv.addObject("Exception", e);
        mv.setViewName("error/error");
        return mv;
    }
}
