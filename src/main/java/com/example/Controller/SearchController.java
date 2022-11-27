package com.example.Controller;

import com.example.Service.SearchService;
import com.example.Vo.Params.SearchParams;
import com.example.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping
    public Result search(@RequestBody SearchParams searchParams){
        return searchService.search(searchParams);
    }
}
