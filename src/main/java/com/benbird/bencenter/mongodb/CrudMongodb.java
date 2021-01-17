package com.benbird.bencenter.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述:
 */
@Controller
@RequestMapping("/crud")
public class CrudMongodb {

    @Autowired
    private MongoTemplate mongoTemplate;




}
