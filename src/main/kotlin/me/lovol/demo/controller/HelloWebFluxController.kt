package me.lovol.demo.controller

import me.lovol.demo.dao.User
import me.lovol.demo.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.MonoSink


@RestController(value = "helloWebFluxController")
class HelloWebFluxController {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    @Autowired
    private lateinit var redisTemplate: ReactiveRedisTemplate<String, *>

    @Autowired
    private lateinit var databaseClient: DatabaseClient

    @Autowired
    private lateinit var userRepository: UserRepository


    @GetMapping("/hello")
    fun hello(): Mono<String> {

        //val objectMono = Mono.create { serverResponseMonoSink: MonoSink<Any> -> serverResponseMonoSink.error(Exception("")) }
        return Mono.create { callback: MonoSink<String> ->

            val hashOps = redisTemplate.opsForHash<String, Any>()
            hashOps.put("111", "222", "333").subscribe { value: Boolean ->
                log.info("执行第一个${value}")
                hashOps.put("111", "222", "333").subscribe { value2: Boolean ->
                    log.info("执行第二个${value2}")
                    hashOps.put("apple", "xr", "5000").subscribe { value3: Boolean ->
                        log.info("执行第三个${value3}")
                        hashOps.put("apple", "xs max", "8000").subscribe { value4: Boolean ->
                            log.info("执行第四个${value4}")
                            callback.success("Hello World!")
                        }
                    }
                }
            }
//            hashOps.put("111", "222", "333").subscribe { value: Boolean ->
//                log.info("执行第一个${value}")
//            }
//            hashOps.put("111", "222", "333").subscribe{ value2: Boolean ->
//                log.info("执行第二个${value2}")
//            }
//            hashOps.put("apple", "xr", "5000").subscribe {value3: Boolean->
//                log.info("执行第三个${value3}")
//            }
//            hashOps.put("apple", "xs max", "8000").subscribe {value4: Boolean->
//                log.info("执行第四个${value4}")
//            }
//            callback.success("Hello World!")
        }

//        return Mono.fromSupplier {
//            System.out.println("test")
//            return@fromSupplier "Hello World!"
//        }
    }

    @GetMapping("/hello1")
    fun hello1(): Mono<String> {

        //val objectMono = Mono.create { serverResponseMonoSink: MonoSink<Any> -> serverResponseMonoSink.error(Exception("")) }
        return Mono.create { callback: MonoSink<String> ->

            userRepository.findByLastname(2).subscribe { u: User ->
                callback.success("User is [userId:${u.id},UserName:${u.username},Password:${u.password}]")

            }


        }

//        return Mono.fromSupplier {
//            System.out.println("test")
//            return@fromSupplier "Hello World!"
//        }
    }


}