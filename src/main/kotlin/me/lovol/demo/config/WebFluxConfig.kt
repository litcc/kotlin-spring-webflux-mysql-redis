package me.lovol.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer


@Configuration(value = "webFluxConfigurer")
@EnableWebFlux
class WebFluxConfig : WebFluxConfigurer {

    /**
     * 开启跨域支持
     * @param registry
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }
}