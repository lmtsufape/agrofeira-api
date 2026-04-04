package br.edu.ufape.agrofeira.controller

import br.edu.ufape.agrofeira.service.AuthService
import br.edu.ufape.agrofeira.service.LoginRequest
import br.edu.ufape.agrofeira.service.RegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
    ) = ResponseEntity.ok(authService.login(request))

    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterRequest,
    ) = ResponseEntity.ok(authService.register(request))
}
