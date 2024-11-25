package com.coder.schoolsecondhandtradingplatform.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 分页接口实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Long count;
    private List<T> list;
}
