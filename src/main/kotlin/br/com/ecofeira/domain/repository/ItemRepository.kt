package br.com.ecofeira.domain.repository

import br.com.ecofeira.domain.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, String> {
    fun findByNomeContainingIgnoreCase(nome: String): List<Item>
}