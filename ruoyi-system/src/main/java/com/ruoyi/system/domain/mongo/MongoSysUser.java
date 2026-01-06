package com.ruoyi.system.domain.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users") // MongoDB集合名
public class MongoSysUser {
    @Id
    private String id; // MongoDB自动生成的唯一ID
    private String username; // 用户名
    private String password; // 密码
    private Date createdAt; // 创建时间
}