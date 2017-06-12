package com.youbi.app.core.utils;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/3/20.
 */

@Component
public class TableUtils {

//    private static final int maxPages = 7;
//    private static final int pageSize = 7;

    public List getList(int pageNumber, int pageSize, List list){
        List result = new ArrayList<>();
        if(pageNumber > (int)(Math.ceil(list.size() / 7.0)) || pageNumber < 1){
            return result;
        }
        result = list.subList((pageNumber - 1) * pageSize,pageNumber * pageSize < list.size() ?
                pageNumber * pageSize : list.size());
        return result;
    }

    public Map<String, Integer> getPageList(int pageNumber, int pageSize,List list){
        int size = (int)(Math.ceil(list.size() / (double)pageSize));
        Map<String, Integer> result = new HashedMap();
        if(pageNumber > size || pageNumber < 1){
            result.put("begin", 0);
            return result;
        }
        if(pageNumber <= 4){
            result.put("begin", 1);
            result.put("end", 7 > size? size: 7);
        }else if(pageNumber > 4){
            result.put("begin", (size > 7 ? ((size - pageNumber) < 4 ? (size - 7): (pageNumber - 4)): 0) + 1);
            result.put("end", size > 7 ? ((size - pageNumber) < 4 ? size: (pageNumber + 3)): size);
        }
        return result;
    }
}
