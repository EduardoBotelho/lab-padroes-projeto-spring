package one.digitalinnovation.gof.Service.impl;

import jakarta.persistence.metamodel.SingularAttribute;
import one.digitalinnovation.gof.model.Cliente;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId();

    Cliente buscarPorId(Long id);

    void inserirCliente(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);


}
