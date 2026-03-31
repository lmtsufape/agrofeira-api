package br.com.ecofeira.dto

import br.com.ecofeira.domain.entity.Cliente

data class ClienteDTO(
    val id: String,
    val nome: String,
    val telefone: String?,
    val descricao: String?
) {
    companion object {
        fun from(cliente: Cliente) = ClienteDTO(
            id = cliente.id,
            nome = cliente.nome,
            telefone = cliente.telefone,
            descricao = cliente.descricao
        )
    }
}