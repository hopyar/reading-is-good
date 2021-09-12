package com.getir.reading.is.good.common.util;

import com.getir.reading.is.good.common.response.Response;
import com.getir.reading.is.good.common.response.ResponseType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {

    public static Response successWithMessage(String message) {
        return new Response(ResponseType.SUCCESS, message, null);
    }

    public static Response success(Object data) {
        return new Response(ResponseType.SUCCESS, null, data);
    }

    public static Response fail(int code, String message, HttpServletResponse response) {
        response.setStatus(code);
        return new Response(ResponseType.ERROR, message, null);
    }
}
