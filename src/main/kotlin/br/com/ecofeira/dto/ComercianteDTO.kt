package br.com.ecofeira.dto

import br.com.ecofeira.domain.entity.Comerciante

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