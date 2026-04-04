package br.edu.ufape.agrofeira.service

import br.edu.ufape.agrofeira.domain.entity.Comerciante
import br.edu.ufape.agrofeira.domain.entity.Item
import br.edu.ufape.agrofeira.domain.repository.ComercianteRepository
import br.edu.ufape.agrofeira.domain.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ComercianteService(
    private val comercianteRepository: ComercianteRepository,
    private val itemRepository: ItemRepository
) {
    fun listarTodos(): List<Comerciante> = comercianteRepository.findAll()

    fun buscarPorId(id: String): Comerciante =
        comercianteRepository.findById(id).orElseThrow { RuntimeException("Comerciante não encontrado") }

    @Transactional
    fun criar(comerciante: Comerciante): Comerciante =
        comercianteRepository.save(comerciante)

    @Transactional
    fun atualizar(id: String, comerciante: Comerciante): Comerciante {
        val existente = buscarPorId(id)
        return comercianteRepository.save(
            existente.copy(
                nome = comerciante.nome,
                telefone = comerciante.telefone,
                descricao = comerciante.descricao
            )
        )
    }

    @Transactional
    fun atualizarItens(id: String, itemIds: List<String>): Comerciante {
        val comerciante = buscarPorId(id)
        val itens = itemRepository.findAllById(itemIds)
        return comercianteRepository.save(
            comerciante.copy(itens = itens.toMutableList())
        )
    }
}