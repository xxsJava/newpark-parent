package com.newpark.main.api.fallback;

import com.newpark.main.api.IMainApi;
import org.springframework.stereotype.Component;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/9 14:15
 **/
@Component
public class MainFallback implements IMainApi {

    @Override
    public String payTest() {
        return "乌拉";
    }
}
