package br.edu.ufape.agrofeira.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {
    private val secretKey =
        Keys.hmacShaKeyFor(
            "ecofeira-secret-key-must-be-at-least-256-bits-long".toByteArray(),
        )

    private val expirationMs = 86400000L // 24 horas

    fun generateToken(username: String): String =
        Jwts
            .builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(secretKey)
            .compact()

    fun extractUsername(token: String): String =
        Jwts
            .parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject

    fun isTokenValid(token: String): Boolean =
        try {
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
}
