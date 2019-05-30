package com.rtmap.modules.app.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidUtils {

    public boolean notBlank(String ...values){
        String[] params = values;
        for (int i = 0;i<params.length;i++){
            if (StringUtils.isBlank(params[i])){
                return true;
            }
        }
        return false;
    }

}
