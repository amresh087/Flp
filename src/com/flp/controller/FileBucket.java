package com.flp.controller;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author Amresh
 *
 */
public class FileBucket {
	 
    MultipartFile file;
     
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
