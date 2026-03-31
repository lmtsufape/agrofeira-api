package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, String> {
    fun findByNomeContainingIgnoreCase(nome: String): List<Cliente>
}