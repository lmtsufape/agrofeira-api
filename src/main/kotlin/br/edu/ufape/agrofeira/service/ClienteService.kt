package br.edu.ufape.agrofeira.service

import br.edu.ufape.agrofeira.domain.entity.Cliente
import br.edu.ufape.agrofeira.domain.repository.ClienteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository
) {
    fun listarTodos(): List<Cliente> = clienteRepository.findAll()

    fun buscarPorId(id: String): Cliente =
        clienteRepository.findById(id).orElseThrow { RuntimeException("Cliente não encontrado") }

    @Transactional
    fun criar(cliente: Cliente): Cliente =
        clienteRepository.save(cliente)

    @Transactional
    fun atualizar(id: String, cliente: Cliente): Cliente {
        val existente = buscarPorId(id)
        return clienteRepository.save(
            existente.copy(
                nome = cliente.nome,
                telefone = cliente.telefone,
                descricao = cliente.descricao
            )
        )
    }
}