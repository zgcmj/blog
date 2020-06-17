package com.cmj.blog.doamin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname; /*昵称*/
    private String email;  /*邮箱*/
    private String content;  /*评论内容*/
    private boolean adminComment;  //是否为管理员评论


    private String avatar;    /*头像*/
    private Date createTime; /*创建时间*/

    private Long blogId;   /*博客id*/
    private Long parentCommentId;  //父评论id
    private String parentNickname;  /*父评论昵称*/

//    回复评论
    private List<Comment> replyComments = new ArrayList<>();

    //父评论
    private Comment parentComment;

    private Blog blog;

}
