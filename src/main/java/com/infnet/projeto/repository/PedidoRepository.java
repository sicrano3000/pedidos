package com.infnet.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infnet.projeto.model.Cliente;
import com.infnet.projeto.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Optional<Pedido> findByCliente(Cliente cliente);

}
