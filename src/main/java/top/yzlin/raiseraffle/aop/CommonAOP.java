package top.yzlin.raiseraffle.aop;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Aspect
@Component
public class CommonAOP {
    private Logger logger= LogManager.getLogger(CommonAOP.class);

    @Pointcut("execution(* top.yzlin.raiseraffle.servlet.*.*(..))"+
            " && !execution(* top.yzlin.raiseraffle.servlet.*.set*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public JSONObject aou(ProceedingJoinPoint pjp) {
        try {
            return Optional.ofNullable(pjp.proceed()).map(data1 -> new JSONObject()
                    .fluentPut("status", 200)
                    .fluentPut("message", "success")
                    .fluentPut("data", data1)).orElseGet(() -> new JSONObject()
                    .fluentPut("status", 401)
                    .fluentPut("message", "这里没有数据啊"));
        } catch (PersistenceException e) {
            logger.error(e);
            return new JSONObject()
                    .fluentPut("status", 503)
                    .fluentPut("message","数据库炸了啊")
                    .fluentPut("error",e.getCause().getMessage());
        } catch (NumberFormatException e) {
            return new JSONObject()
                    .fluentPut("status", 503)
                    .fluentPut("message","参数转换错误")
                    .fluentPut("error",e.getMessage());
        } catch (NoSuchBeanDefinitionException e) {
            return new JSONObject()
                    .fluentPut("status", 404)
                    .fluentPut("message","不存在资源")
                    .fluentPut("error",e.getMessage());
        } catch (Throwable e) {
            logger.error(e);
            return new JSONObject()
                    .fluentPut("status", 503)
                    .fluentPut("message","未知错误")
                    .fluentPut("error",e.getMessage());
        }


    }

}
