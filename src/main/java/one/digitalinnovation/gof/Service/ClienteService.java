package one.digitalinnovation.gof.Service;

import one.digitalinnovation.gof.model.Cliente;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();
    //Cliente buscarPorId();

    Cliente buscarPorId(Long id);



    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);


}
