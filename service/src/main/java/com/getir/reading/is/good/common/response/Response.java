package com.getir.reading.is.good.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {
    private ResponseType responseType;

    private String message;

    private Object data;
}
