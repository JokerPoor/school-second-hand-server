package com.coder.schoolsecondhandtradingplatform.contants;


import lombok.Getter;

@Getter
public enum TipMessage {
    Login_Error("登录失败，请检查账号和密码是否输入正确"),
    NO_Login("没有权限，请先登录"),
    Email_Used("邮箱已被使用"),
    Register_Failed("注册失败"),
    Already_Exists("已存在"),
    Record_Is_Empty("字段值为空"),
    Insert_Failed("插入失败"),
    Auction_Not_PENDING("不允许编辑以及开拍或者拍过了的教材"),
    Response_Default_Error("对不起,操作失败,请联系管理员");

    // 提供一个方法来获取描述信息
    private final String description;  // 用于存储消息

    // 构造方法
    TipMessage(String description) {
        this.description = description;
    }

}
