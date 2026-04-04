package br.edu.ufape.agrofeira.dto

import br.edu.ufape.agrofeira.domain.entity.Comerciante

data class ComercianteDTO(
    val id: String,
    val nome: String,
    val telefone: String?,
    val descricao: String?,
    val itens: List<ItemDTO>
) {
    companion object {
        fun from(comerciante: Comerciante) = ComercianteDTO(
            id = comerciante.id,
            nome = comerciante.nome,
            telefone = comerciante.telefone,
            descricao = comerciante.descricao,
            itens = comerciante.itens.map { ItemDTO.from(it) }
        )
    }
}