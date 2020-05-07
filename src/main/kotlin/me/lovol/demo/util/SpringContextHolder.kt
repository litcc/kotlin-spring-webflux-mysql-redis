package me.lovol.demo.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext

@Suppress("JAVA_CLASS_ON_COMPANION", "UNCHECKED_CAST")
class SpringContextHolder {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this.javaClass)
        private lateinit var applicationContext: ApplicationContext

        /**
         * 设置静态变量中的ApplicationContext.
         */
        fun setApplicationContext(ctx: ApplicationContext) {
            if (!this::applicationContext.isInitialized) {
                log.info("----------------------------------------------------------")
                log.info("------------------SpringContextHolder---------------------")
                log.info("----------------------------------------------------------")
                applicationContext = ctx
                log.info(" => ApplicationContext配置成功")
            }
        }
        private fun checkContext() {
            checkNotNull(applicationContext) { "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder" }
        }

        /**
         * 取得存储在静态变量中的ApplicationContext.
         */
        fun getApplicationContext(): ApplicationContext {
            checkContext()
            return applicationContext
        }


        /**
         * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
         */
        fun <T> getBean(name: String?): T {
            checkContext()
            return applicationContext.getBean(name!!) as T
        }

        /**
         * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
         */
        fun <T> getBean(clazz: Class<T>): T {
            checkContext()
            return applicationContext.getBeansOfType(clazz) as T
        }
    }


}