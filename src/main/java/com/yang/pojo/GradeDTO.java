package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hang yang
 * @create 2022-04-04 22:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Project project;
    private Athlete athlete;
}
