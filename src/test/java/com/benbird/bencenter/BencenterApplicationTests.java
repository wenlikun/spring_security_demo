package com.benbird.bencenter;

import com.benbird.bencenter.mapper.ElasticJobConfigMapper;
import com.benbird.bencenter.models.DO.ElasticJobConfigDO;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.*;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BencenterApplicationTests {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Test
    public void test() throws FileNotFoundException {

        File file = new File("C:\\Users\\Admin\\Pictures\\Camera Roll\\01.jpg");
        // 获得提交的文件名
        String fileName = file.getName();
        // 获得文件输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 获得文件类型
        String contentType = fileName.substring(fileName.lastIndexOf("."));
        // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
        ObjectId objectId = gridFsTemplate.store(fileInputStream, fileName, contentType);
        // 6000087772ad166907737d69
        System.out.println(objectId);

    }


    @Test
    public void down() throws IOException {
        Query query = Query.query(Criteria.where("_id").is("6000087772ad166907737d69"));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
        InputStream inputStream = resource.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\images\\" + gridFSFile.getFilename()));
        IOUtils.copy(inputStream, fileOutputStream);
        IOUtils.closeQuietly(fileOutputStream);
        IOUtils.closeQuietly(inputStream);

    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void query(){
        Map one = mongoTemplate.findOne(Query.query(Criteria.where("_id").is("5fe9b76bc7a81c2f5068809d")),
                Map.class, "user");
        System.out.println(one);
    }





}
