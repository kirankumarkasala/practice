package com.excelra.glpg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class StringUtility {
	private static final Logger LOGGER = LoggerFactory.getLogger(StringUtility.class);

    public static boolean isNull(String value) {
        return value == null || value.trim().length() == 0 || value.equals("null");
    }

   

}