package br.com.ecofeira.service

import br.com.ecofeira.domain.entity.Item
import br.com.ecofeira.domain.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    fun listarTodos(): List<Item> = itemRepository.findAll()

    fun buscarPorId(id: String): Item =
        itemRepository.findById(id).orElseThrow { RuntimeException("Item não encontrado") }

    @Transactional
    fun criar(item: Item): Item =
        itemRepository.save(item)

    @Transactional
    fun atualizar(id: String, item: Item): Item {
        val existente = buscarPorId(id)
        return itemRepository.save(
            existente.copy(
                nome = item.nome,
                unidadeMedida = item.unidadeMedida,
                precoBase = item.precoBase
            )
        )
    }
}