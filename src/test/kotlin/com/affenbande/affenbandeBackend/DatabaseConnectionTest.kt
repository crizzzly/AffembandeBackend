package com.affenbande.affenbandeBackend

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class DatabaseConnectionTest {
    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null

    @PostConstruct
    fun testConnection() {
        try {
            jdbcTemplate!!.execute("SELECT 1")
            println("Successfully connected to the database!")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
