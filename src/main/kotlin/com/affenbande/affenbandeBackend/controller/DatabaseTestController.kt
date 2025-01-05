package com.affenbande.affenbandeBackend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource


@RestController
class DatabaseTestController {
    @Autowired
    private val dataSource: DataSource? = null

    @GetMapping("/")
    fun testDatabaseConnection(): String {
        try {
            dataSource!!.connection.use { _ ->
                return "Database connection successful!"
            }
        } catch (e: Exception) {
            return "Database connection failed: " + e.message
        }
    }
}
