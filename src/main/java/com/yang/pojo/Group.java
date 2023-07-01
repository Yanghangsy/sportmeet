package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系团队表
 * @author hang yang
 * @create 2022-01-04 20:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    /**
     * 系团队编号
     */
    private Integer gnum;
    /**
     * 领队
     */
    private String leader;
    /**
     * 系团队名称
     */
    private String gname;
}
