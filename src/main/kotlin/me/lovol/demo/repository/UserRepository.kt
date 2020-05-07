package me.lovol.demo.repository

import me.lovol.demo.dao.User
import org.springframework.context.annotation.Bean
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux


/**
 * @author Oliver Gierke
 * @author Mark Paluch
 */

internal interface UserRepository : R2dbcRepository<User, Long> {
    @Query("select * from users c where c.id = :id")
    fun findByLastname(id: Int): Flux<User>
}