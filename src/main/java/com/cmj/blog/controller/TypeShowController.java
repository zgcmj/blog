package com.cmj.blog.controller;

import com.cmj.blog.doamin.Blog;
import com.cmj.blog.doamin.Type;
import com.cmj.blog.web.service.BlogService;
import com.cmj.blog.web.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum, 100);  //开启分页
        List<Type> types = typeService.getBlogType();
        //-1从导航点过来的
        if (id == -1){
            id = types.get(0).getId();
        }
        List<Blog> blogs = blogService.getByTypeId(id);
//        List<Blog> indexBlog = blogService.getIndexBlog();
        System.out.println(blogs);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);

        return "types";
    }
}
