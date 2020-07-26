package cn.elasticsearch.controller;

import cn.elasticsearch.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parseHtml(@PathVariable("keyword") String keyword) throws Exception {
        return contentService.parseContent(keyword);
    }
}
