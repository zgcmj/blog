package com.cmj.blog.controller;

//import com.cmj.blog.exception.BlogNotFoundException;
import com.cmj.blog.doamin.Blog;
import com.cmj.blog.doamin.Tag;
import com.cmj.blog.doamin.Type;
import com.cmj.blog.exception.BlogNotFoundException;
import com.cmj.blog.web.service.BlogService;
import com.cmj.blog.web.service.TagService;
import com.cmj.blog.web.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jboss.logging.BasicLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum,5);
        //得到博客列表
        List<Blog> indexBlog = blogService.getIndexBlog();
        //得到类型列表
        List<Type> indexType = typeService.getBlogType();
        //得到标签
        List<Tag> indexTag = tagService.getBlogTag();
        //获取推荐博客
//        List<Blog> indexRecommendBlog = blogService.getAllRecommendBlog();
        //分页对象
        PageInfo<Blog> pageInfo = new PageInfo<>(indexBlog);

//        System.out.println(indexBlog);
        System.out.println(indexType);
//        System.out.println(indexTag);
//        System.out.println(indexRecommendBlog);
        //存入model
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("indexType",indexType);
        model.addAttribute("indexTag",indexTag);
//        model.addAttribute("indexRecommendBlog",indexRecommendBlog);

        return "index";
    }

    @GetMapping("/blog/{id}")
    public String toLogin(@PathVariable Long id, Model model){
        Blog blog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

}
