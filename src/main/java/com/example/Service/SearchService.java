package com.example.Service;

import com.example.Vo.Params.SearchParams;
import com.example.Vo.Result;

public interface SearchService {
    Result search(SearchParams searchParams);
}
