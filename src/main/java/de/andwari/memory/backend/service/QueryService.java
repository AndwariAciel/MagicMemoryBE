package de.andwari.memory.backend.service;

import lombok.experimental.UtilityClass;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class QueryService {

    public static String getSetSearch(String setName) {
        return encode("set:" + setName, UTF_8);
    }
}
