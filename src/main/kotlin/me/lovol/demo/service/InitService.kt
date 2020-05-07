package me.lovol.demo.service

import me.lovol.demo.util.SpringContextHolder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service(value = "initService")
class InitService {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    }


    fun run() {
        log.info("项目跑完了\uD83D\uDC15")
//        val beanNames = SpringContextHolder.getApplicationContext().getBeanDefinitionNames();
//        //String[] beanNames = ctx.getBeanNamesForAnnotation(RestController.class);//所有添加该注解的bean
//        System.out.println("bean总数:${SpringContextHolder.getApplicationContext().getBeanDefinitionCount()}")
//
//
//        for (item in beanNames) {
//            System.out.println("beanName:${item}")
//        }

    }
}