package com.cc.controller;

import com.cc.dao.IUserDao;
import com.cc.domain.Role;
import com.cc.domain.UserInfo;
import com.cc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private IUserService userService;

    //用户查询
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }

    //用户添加
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //查询指定ID的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(int id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo =userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //查询用户以及用户可以添加的角色
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)int userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户Id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户Id查询可以添加的角色
        List<Role> roleList = userService.findOtherRole(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)int userId,@RequestParam(name = "ids",required = true)int[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);

        return "redirect:findAll.do";
    }
}
