package com.example.Controller;

import com.example.Service.SearchService;
import com.example.Utils.RequestInfo;
import com.example.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
//@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    //@RequestMapping("/{musicName}")
    @PostMapping("/search")
    public Result search(@RequestBody RequestInfo requestInfo){
        return searchService.search(requestInfo);
    }
}
