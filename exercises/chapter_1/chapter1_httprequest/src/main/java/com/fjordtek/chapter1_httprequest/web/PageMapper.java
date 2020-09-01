// Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter1_httprequest.web;

import java.util.HashMap;
import java.util.Map;

// NOTE: This is not actually required, will possibly be removed
public class PageMapper {

    private Map<String, String> webPageNameMap = new HashMap<>();

    protected String mapWebPageNames(String keyName, String valueName) {
        webPageNameMap.put(keyName, valueName);
        return webPageNameMap.get(keyName);
    }

}