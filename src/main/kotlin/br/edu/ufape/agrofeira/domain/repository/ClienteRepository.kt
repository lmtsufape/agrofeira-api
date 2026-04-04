package br.edu.ufape.agrofeira.domain.repository

import br.edu.ufape.agrofeira.domain.entity.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, String> {
    fun findByNomeContainingIgnoreCase(nome: String): List<Cliente>
}