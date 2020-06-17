package com.cmj.blog.controller.admin;


import com.cmj.blog.doamin.Tag;
import com.cmj.blog.web.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cmj")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(value = "pagenum" , required = false ,defaultValue = "1") int pagnum,
                       Model model){

        PageHelper.startPage(pagnum,3);
        List<Tag> allTag = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo(allTag);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String toAddTag(Model model){
//        model.addAttribute("tag", new Tag());   //返回一个tag对象给前端th:object
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags/add")
    public String addTag(Tag tag, RedirectAttributes attributes){   //新增
        Tag t = tagService.getTagByName(tag.getName());
        System.out.println();
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/cmj/tags/input";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        tagService.saveTag(tag);
        return "redirect:/cmj/tags";   //不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)
    }

    @PostMapping("/tags/update/{id}")
    public String editTag(@PathVariable(value = "id",required = false)  Long id, Tag tag, RedirectAttributes attributes){  //修改
        Tag t = tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("tag", t);
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/cmj/tags/input";
        }else {
            attributes.addFlashAttribute("msg", "修改成功");
        }
        tagService.updateTag(tag);
        return "redirect:/cmj/tags";   //不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/cmj/tags";
    }

}
