package me.lovol.demo.config

import me.lovol.demo.service.InitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component(value = "applicationRunner")
@Order(value = 1)
class MyApplicationRunner : ApplicationRunner {
    @Autowired
    lateinit var initService: InitService

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        initService.run()
    }

}