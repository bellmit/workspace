/*
 * Copyright © 2013 Changsha jumore Network Technology Co., Ltd. All rights
 * reserved.
 * 
 * http://www.jumore.com
 */

package com.jumore.jmbi.common.util;

import org.springframework.web.multipart.MultipartFile;

import com.jumore.jmbi.common.util.PropertyUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Herbert
 * 
 */
public class MediaUtils {

    /**
     * 文件允许格式
     */
    public static String[]   FILE_TYPE  = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv" };

    /**
     * 图片允许格式
     */
    public static String[]   PHOTO_TYPE = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
    
    public static final  String Day_Format = "yyyy/MM/dd";
    
    public static final  String Photo_Type_Jpg = "jpg";

    /**
     * isFileType:校验文件类型.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午5:56:46
     * @param fileName
     * @param typeArray
     * @return
     */
    public static boolean isFileType(String fileName, String[] typeArray) {
        for (String type : typeArray) {
            if (fileName.toLowerCase().endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 递归获得目录的所有地址
     * 
     * @param realpath
     * @param files
     * @param fileType
     * @return
     */
    public static List<File> getFiles(String realpath, List<File> files, String[] fileType) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files, fileType);
                } else {
                    if (isFileType(file.getName(), fileType)) {
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }

    /**
     * 获取文件扩展名
     * 
     * @return string
     */
    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 删除物理文件
     * 
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(PropertyUtils.getRoot() + path);
        file.delete();
    }


    /**
     * @param multipartFile
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public static String save(MultipartFile multipartFile) throws IllegalStateException, IOException {
        SimpleDateFormat formater = new SimpleDateFormat(Day_Format);
        String path = "upload/" + formater.format(new Date()) + "/file/" + UUID.randomUUID().toString().replaceAll("-", "")
                + getFileExt(multipartFile.getOriginalFilename());
        File file = new File(PropertyUtils.getRoot() + "/" + path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        multipartFile.transferTo(file);
        return path;
    }

}
