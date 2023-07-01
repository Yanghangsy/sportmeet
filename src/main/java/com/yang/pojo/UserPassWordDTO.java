package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hang yang
 * @create 2022-04-03 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassWordDTO {
    private String userid;
    private String password;
    private String newPassword;
}
