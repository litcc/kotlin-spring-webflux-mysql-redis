package me.lovol.demo.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.net.URISyntaxException

@Configuration(value = "abstractR2dbcConfiguration")
@EnableTransactionManagement
class DatabaseConfiguration : AbstractR2dbcConfiguration() {

    @Autowired
    private lateinit var r2dbcProperties: R2dbcProperties

    @Bean("r2dbcConnectionFactory")
    @Throws(URISyntaxException::class)
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(r2dbcProperties.url)
    }


    @Bean("r2dbcTransaction")
    @Throws(URISyntaxException::class)
    internal fun transactionManager(properties: R2dbcProperties): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory())
    }


}



