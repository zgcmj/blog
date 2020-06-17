package com.cmj.blog.controller.admin;


import com.cmj.blog.doamin.Type;
import com.cmj.blog.web.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cmj")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){
        //从第几页开始，每页多少条数
        PageHelper.startPage(pagenum,2);
        List<Type> allType = typeService.getAllType();
        System.out.println(allType);
//        分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(){
            return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable(value = "id",required = false) Long id, Model model){
            model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/add")
    public String addTypes(Type type,
                RedirectAttributes RedirectAttributes){
        Type t = typeService.getTypeByName(type.getName());
        /*判断是否已存在标签*/
        if(t !=null){
            //纯在,不能添加
            RedirectAttributes.addFlashAttribute("msg","已存在该标签,不能重复添加");
            return "redirect:/cmj/types/input";
        }else {
            //不纯在,给提示
            RedirectAttributes.addFlashAttribute("msg", "添加成功");
        }
        typeService.saveType(type);
        return "redirect:/cmj/types";
    }

    @PostMapping("/types/update/{id}")
    public String updateTypes(Type type,@PathVariable(value = "id",required = false) Long id,

                           RedirectAttributes RedirectAttributes){
        System.out.println(type);
        Type t = typeService.getTypeByName(type.getName());
        if(t!=null){
            RedirectAttributes.addFlashAttribute("msg","该分组已存在，修改失败!");
            //修改失败后，再把这次的对象返回过去
            RedirectAttributes.addFlashAttribute("type",type);
            return "redirect:/cmj/types/input";
        }else {
            RedirectAttributes.addFlashAttribute("msg","修改成功");
        }
        typeService.updateType(type);
        return "redirect:/cmj/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/cmj/types";
    }

}
