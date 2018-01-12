package com.test.aspect;

import org.apache.log4j.Logger;
import sun.reflect.Reflection;

/**
 * Created by Doublestar on 2017/12/13 10:01).
 * 在没有AOP时，每个类各自调用此工具类，可以记录调用的类名称
 */
public class Log4JUtils{

    private static Logger logger =  null;

    public static Logger getLogger(){
        if (null == logger){
            //Java8 废弃了Reflection.getCallerClass()
            logger = Logger.getLogger(Reflection.getCallerClass().getName());
//            logger.debug("调用者类名"+Reflection.getCallerClass().getName());
        }
        return logger;
    }
}
