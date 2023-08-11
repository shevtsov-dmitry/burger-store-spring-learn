package com.videoplayer;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Optional;

@Controller
@RequestMapping("/video")
public class ProcessGetVideoRequest {

//    @GetMapping
//    public ResponseEntity<InputStreamResource> sendVideo(
//            @PathVariable String filename, @RequestHeader(value = "Range", required = false) String rangeHeader){
//
//    }
    @GetMapping
    public ResponseEntity<byte[]> sendVideo(@PathVariable String filename){
        byte[] videoBytes = new byte[(int) new File(filename).length()];
        return ResponseEntity.of()
    }
}
