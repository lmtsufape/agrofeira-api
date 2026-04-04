package br.edu.ufape.agrofeira.service

import br.edu.ufape.agrofeira.config.JwtService
import br.edu.ufape.agrofeira.domain.entity.Usuario
import br.edu.ufape.agrofeira.domain.repository.UsuarioRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

data class LoginRequest(
    val username: String,
    val password: String,
)

data class LoginResponse(
    val token: String,
    val username: String,
)

data class RegisterRequest(
    val username: String,
    val password: String,
)

@Service
class AuthService(
    private val usuarioRepository: UsuarioRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
) {
    fun login(request: LoginRequest): LoginResponse {
        val usuario =
            usuarioRepository
                .findByUsername(request.username)
                .orElseThrow { RuntimeException("Usuário não encontrado") }

        if (!passwordEncoder.matches(request.password, usuario.senhaHash)) {
            throw RuntimeException("Senha inválida")
        }

        val token = jwtService.generateToken(usuario.username)
        return LoginResponse(token = token, username = usuario.username)
    }

    fun register(request: RegisterRequest): Usuario {
        if (usuarioRepository.findByUsername(request.username).isPresent) {
            throw IllegalArgumentException("Username já existe")
        }
        return usuarioRepository.save(
            Usuario(
                username = request.username,
                senhaHash =
                    passwordEncoder.encode(request.password)
                        // TODO revisar isso aqui pelo amor de Deus
                        ?: throw RuntimeException("Não foi possível criar a senha"),
                perfil = "ADMIN",
            ),
        )
    }
}
