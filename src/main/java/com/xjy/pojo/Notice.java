package com.xjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 云
 * @create 2023/6/12 11:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private int noticeId;
    private String noticeName;
    private String noticeContent;
}
