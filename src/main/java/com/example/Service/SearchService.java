package com.example.Service;

import com.example.Utils.RequestInfo;
import com.example.Vo.Result;
import org.springframework.transaction.annotation.Transactional;

public interface SearchService {
    Result search(RequestInfo requestInfo);
}
