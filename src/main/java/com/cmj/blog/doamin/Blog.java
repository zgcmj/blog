package com.cmj.blog.doamin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Blog {
    private Long id; /*ID*/
    private String title; /*标题*/
    private String content; /*文章内容*/
    private String firstPicture;  /*首图*/
    private String flag;    /*文章标签*/
    private Integer views;  /*阅览次数*/
    private boolean appreciation; /*是否开启赞赏*/
    private boolean shareStatement; /*是否开启版权*/
    private boolean commentabled; /*是否开启评论*/
    private boolean published; /*是否发布*/
    private boolean recommend; /*是否提交*/
    private Date createTime; /*创建时间*/
    private Date updateTime; /*修改時間*/

    /*用于连接查询的id*/
    private Long typeId;
    private Long userId;

    //获取多个标签的id
    private String tagIds;
//    类型
    private String description;

    //
    private Type type;

    // 一个博客很多标签   1:N
    private List<Tag> tags = new ArrayList<>();


    //属于谁
    private User user;

    private List<Comment> comments = new ArrayList<>();

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
