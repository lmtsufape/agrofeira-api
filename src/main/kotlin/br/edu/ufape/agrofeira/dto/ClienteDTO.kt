package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.Cliente

data class ClienteDTO(
    val id: String,
    val nome: String,
    val telefone: String?,
    val descricao: String?,
) {
    companion object {
        fun from(cliente: Cliente) =
            ClienteDTO(
                id = cliente.id,
                nome = cliente.nome,
                telefone = cliente.telefone,
                descricao = cliente.descricao,
            )
    }
}
