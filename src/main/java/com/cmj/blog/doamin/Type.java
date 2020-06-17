package com.cmj.blog.doamin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type  {
    private Long id;


    private String name; /*类型*/

    private List<Blog> blogs = new ArrayList<>();


}
