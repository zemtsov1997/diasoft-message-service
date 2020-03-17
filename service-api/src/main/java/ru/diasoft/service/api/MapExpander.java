package ru.diasoft.service.api;

import feign.Param;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class MapExpander implements Param.Expander {

    @Override
    public String expand(Object o) {
        UrlSeparator urlSeparator = new UrlSeparator();

        StringBuffer getParams = new StringBuffer();
        ((Map<String, Object>) o).forEach((k,v) -> {
            getParams.append(urlSeparator.get() + k + "=" + v.toString());
        });

        String finalGetUrlParams = getParams.toString().substring(1);

        try {
            return URLEncoder.encode(finalGetUrlParams, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
