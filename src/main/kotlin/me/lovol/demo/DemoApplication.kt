package me.lovol.demo

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.lovol.demo.util.SpringContextHolder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import java.util.*
import javax.annotation.PostConstruct

//(exclude = [DataSourceAutoConfiguration::class])
@SpringBootApplication
@EnableR2dbcRepositories(basePackages = ["me.lovol.demo.repository.**"])
class DemoApplication {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    @Autowired
    lateinit var applicationContext: ApplicationContext

    /**
     * 设置全局 ApplicationContext
     */
    @PostConstruct
    @Throws(BeansException::class)
    fun setApplicationContext() {
        SpringContextHolder.setApplicationContext(applicationContext);
        //SpringContextHolder.setApplicationContext(applicationContext);
    }

    /**
     * 设置时区
     */
    @PostConstruct
    internal fun setDefaultTimezone() {
        log.info("设置java时区为 Asia/Shanghai")
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"))
        //TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

}


//fun main(args: Array<String>) {
//    //runApplication<DemoApplication>(*args)
//    val springApplication = SpringApplication(DemoApplication::class.java)
//    springApplication.setAddCommandLineProperties(false)
//    springApplication.run(*args)
//}

suspend fun main(args: Array<String>) = coroutineScope {
    launch {
        delay(1000)
        println("Kotlin Coroutines World!")
        //runApplication<DemoApplication>(*args)
        val springApplication = SpringApplication(DemoApplication::class.java)
        springApplication.setAddCommandLineProperties(false)
        springApplication.run(*args)
    }
    println("Hello")
}