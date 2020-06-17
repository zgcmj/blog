package com.cmj.blog.doamin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private Long id;
    private String name; /*标签名*/

    private List<Blog> blogs = new ArrayList<>();
}
