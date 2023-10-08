package com.newpark.core.aop;


import com.newpark.base.annotation.AuthorityCheck;
import com.newpark.base.constant.Constant;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.exception.BizException;
import com.newpark.base.model.entity.UserDetailEntity;
import com.newpark.core.utils.LoginHelper;
import com.newpark.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 权限校验切面逻辑
 * @author jack
 * @since 2023/3/14
 */
@Aspect
@Component
@Slf4j
public class AuthorityCheckAspect {


    @Pointcut("execution(* com.newpark.*.controller..*.*(..))&&@annotation(com.zh.mustang.common.base.annotation.AuthorityCheck)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("切入执行");

        //获取注解信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        AuthorityCheck annotation = signature.getMethod().getAnnotation(AuthorityCheck.class);
        if (annotation == null) {
            return true;
        }
        //获取当前用户信息
        UserDetailEntity userDetailEntity = LoginHelper.getCurrentUserAndCheck();
        //校验权限码
        checkAuthority(annotation, userDetailEntity);

        //执行方法
        Object result = point.proceed();

        return result;
    }

    /**
     * 校验权限码
     *
     * @param annotation
     * @param userDetailEntity
     */
    private void checkAuthority(AuthorityCheck annotation, UserDetailEntity userDetailEntity) {

        String[] code = annotation.value();

        String role = userDetailEntity.getRoleCode();
        if(Constant.Role.SUPER_ADMIN.getCode().equals(role)) {
            // 超管默认拥有所有权限
        }else {
            //如果不为空，则要判断权限码 有没有
            if (code != null && code.length > 0) {
                //如果只有一个，并且是默认的权限码 空串，则不判断
                if (code.length == 1 && StringUtils.isEmpty(code[0])) {
                    return;
                }
                Boolean isCheck = LoginHelper.checkAuthority(userDetailEntity, annotation.logical(), code);
                if (isCheck == false) {
                    throw new BizException(ResponseCodeEnum.USER_UNAUTHORIZED.getCode(), ResponseCodeEnum.USER_UNAUTHORIZED.getMsg());
                }
            }
        }
    }

}
