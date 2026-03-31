package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsuarioRepository : JpaRepository<Usuario, String> {
    fun findByUsername(username: String): Optional<Usuario>
}