package com.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PageUtil {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map putPageParam(HttpServletRequest request) throws Exception {
        Map parms = new HashMap();

        parms.put("pageIndex", request.getParameter("page"));
        parms.put("pageSize", request.getParameter("rows"));

        parms.put("sortField", request.getParameter("sort"));
        parms.put("sortOrder", request.getParameter("order"));

        /*int pageIndex = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        int start = pageIndex * pageSize, end = start + pageSize;

        parms.put("start", start + 1);
        parms.put("end", end);*/

        return parms;
    }

}
