package com.stan.server.component;

import com.stan.server.model.vo.UserVO;
import com.stan.server.service.UserService;
import com.stan.server.utils.ResultVO;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * @author Ren
 * 微信OpenId验证切面
 */
@Component
@Aspect
public class WeChatAuthenticationAspect {

    @Autowired
    private UserService userService;

    @Pointcut("execution(public * com.stan.server.controller..WeChatController.*(..))")
    public void weChat(){}



//    @Before("weChat()")
//    public void deBefore(JoinPoint joinPoint) throws Throwable {
//        //获取目标方法参数信息
////        Object[] args = joinPoint.getArgs();
////        System.out.println("方法参数：" + args[0]);
////        args[0] = "哈哈哈";
//        //获取目标方法的参数信息
//
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "weChat()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        System.out.println("方法的返回值 : " + ret);
//    }
//
//    //后置异常通知
//    @AfterThrowing("weChat()")
//    public void throwss(JoinPoint jp){
//        System.out.println("方法异常时执行.....");
//    }
//
//    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
//    @After("weChat()")
//    public void after(JoinPoint jp){
//        System.out.println("方法最后执行.....");
//    }

    /**
     * 通过aop进行权限校验
     *
     * @param pjp
     * @return
     */
    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("weChat()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            String class_name = pjp.getTarget().getClass().getName();
            String method_name = pjp.getSignature().getName();
            if (method_name.contains("login") || method_name.contains("notAuth"))
                return pjp.proceed();
            // 获取方法参数名称
            String[] paramNames = getFieldsName(class_name, method_name);
            if (paramNames == null || paramNames.length == 0)
                return pjp.proceed();
            Object[] args = pjp.getArgs();
            for (int i = 0; i < paramNames.length; i++) {
                if (paramNames[i].equals("openId")) {
                    UserVO userVO = userService.getUserInfoByOpenId((String) args[i]);
                    if (userVO == null)
                        return ResultVO.result(212, "用户未注册");
                    break;
                }
            }
            return pjp.proceed(args);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    private String[] getFieldsName(String class_name, String method_name) throws Exception {
        Class<?> clazz = Class.forName(class_name);
        String clazz_name = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazz_name);
        CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if(attr == null){
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i=0;i<paramsArgsName.length;i++){
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }

}