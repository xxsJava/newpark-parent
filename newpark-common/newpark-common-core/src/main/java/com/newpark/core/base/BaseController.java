package com.newpark.core.base;


import com.newpark.base.model.entity.UserDetailEntity;
import com.newpark.core.utils.LoginHelper;

/**
 * @author jack
 * @since 2023/3/14
 */
public class BaseController {


    /**
     * 获取当前用户
     *
     * @return
     */
    protected UserDetailEntity getCurrentUser() {
        return LoginHelper.getCurrentUserAndCheck();
    }

    /**
     * 获取当前用户的 appId
     *
     * @return
     */
    protected Long getUserId() {
        UserDetailEntity clientLoginInfoDto = getCurrentUser();
        if (clientLoginInfoDto != null) {
            return clientLoginInfoDto.getUserId();
        }
        return 0L;
    }
}
