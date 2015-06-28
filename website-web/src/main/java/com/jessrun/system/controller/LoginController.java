package com.jessrun.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

 

    @RequestMapping(value = "/doGet.do", method = RequestMethod.GET)
    public void doGet(HttpServletRequest req,HttpServletResponse response)  throws Exception {
           String username = req.getParameter("username");
           String passward = req.getParameter("passward");
           String message = "username="+username+"-"+"passward="+passward;
           System.out.println(message);
           //response.setContentType("conten-Type:text/html;charset=utf-8");
           response.getOutputStream().write(message.getBytes());
       
        
    }
 
    @SuppressWarnings("all")
    @RequestMapping(value = "/doPost.do", method = RequestMethod.POST)
    public void doPost(HttpServletRequest req  ,HttpServletResponse response)  throws Exception {
        String username = req.getParameter("username");
        String passward = req.getParameter("passward");
        String message = "username="+username+"-"+"passward="+passward;
        System.out.println(message);
        response.getOutputStream().write(message.getBytes());
    }

}