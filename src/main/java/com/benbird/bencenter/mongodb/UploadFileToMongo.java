package com.benbird.bencenter.mongodb;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述: 操作mongodb文件上传和下载
 */
@Controller
@RequestMapping("/file")
public class UploadFileToMongo {

    /**
     *     获得SpringBoot提供的mongodb的GridFS对象
     */
    @Autowired
    private GridFsTemplate gridFsTemplate;

    /**
     * 文件上传
     *
     * @param request request
     * @return  Object
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(HttpServletRequest request) throws Exception {
        Part part = request.getPart("file");
        // 获得提交的文件名
        String fileName = part.getSubmittedFileName();
        // 获得文件输入流
        InputStream ins = part.getInputStream();
        // 获得文件类型
        String contentType = part.getContentType();
        // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
        return gridFsTemplate.store(ins, fileName, contentType);
    }


    /**
     * 下载
     *
     * @param fileId   文件id
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(@RequestParam(name = "file_id") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Query query = Query.query(Criteria.where("_id").is(fileId));
        // 查询单个文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        if (gridFSFile == null) {
            return;
        }
        String fileName = gridFSFile.getFilename().replace(",", "");
        //处理中文文件名乱码
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 通知浏览器进行文件下载
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
        response.setContentType(resource.getContentType());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        InputStream inputStream = resource.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        IOUtils.closeQuietly(outputStream);
        IOUtils.closeQuietly(inputStream);
    }



}
