package one.digitalinnovation.gof.Service.impl;

import jakarta.persistence.metamodel.SingularAttribute;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
      return clienteRepository.findAll();
    }


    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserirCliente(Cliente cliente) {
        salvarClientecomCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
         //buscar cliente por id
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClientecomCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }


    private void salvarClientecomCep(Cliente cliente) {
        //Verificar se o endereco ja existe, pelo Cep.
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            //caso nao exista, integrar com o viaCEp e persistir o retorno
            Endereco novoEndereco = viaCepService.consultarcep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        //Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }

}
