package com.example.projetoFacul.Model.Teste;

import com.example.projetoFacul.Exception.ExeptionUser.CargoNotFound;
import com.example.projetoFacul.Exception.ExeptionUser.StatusNotFound;
import com.example.projetoFacul.Model.Produto.Produto;
import com.example.projetoFacul.Model.Produto.StatusProduto;
import com.example.projetoFacul.Model.Usuario.UserCargo;
import com.example.projetoFacul.Model.Usuario.UserStatus;
import com.example.projetoFacul.Model.Usuario.Usuario;
import com.example.projetoFacul.Repository.Produto.ProdStatusRepository;
import com.example.projetoFacul.Repository.Produto.ProdutoRepository;
import com.example.projetoFacul.Repository.Usuario.UserCargoRepository;
import com.example.projetoFacul.Repository.Usuario.UserRepository;
import com.example.projetoFacul.Repository.Usuario.UserStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TesteDataConfig {

    @Bean
    public CommandLineRunner instanciarDados(UserRepository userRepository,
                                             UserStatusRepository userStatusRepository,
                                             UserCargoRepository userCargoRepository,
                                             ProdutoRepository produtoRepository,
                                             ProdStatusRepository prodStatusRepository) {

        return args -> {


            if (userStatusRepository.count() == 0) {
                UserStatus statusAtivo = new UserStatus();
                statusAtivo.setStatus("ATIVO");

                UserStatus statusInativo = new UserStatus();
                statusInativo.setStatus("INATIVO");

                userStatusRepository.save(statusAtivo);
                userStatusRepository.save(statusInativo);
            }


            if (userCargoRepository.count() == 0) {
                UserCargo cargoDev = new UserCargo();
                cargoDev.setCargo("DEV");

                UserCargo cargoAdm = new UserCargo();
                cargoAdm.setCargo("ADMINISTRADOR");

                userCargoRepository.save(cargoDev);
                userCargoRepository.save(cargoAdm);
            }


            if (userRepository.count() == 0) {
                UserCargo cargo = userCargoRepository.findById(1L)
                        .orElseThrow(() -> new CargoNotFound("Nenhum cargo encontrado"));
                UserStatus status = userStatusRepository.findById(1L)
                        .orElseThrow(() -> new StatusNotFound("Nenhum status encontrado"));

                Usuario user1 = new Usuario();
                user1.setNome("Gabriel");
                user1.setSenha("123");
                user1.setCargo(cargo);
                user1.setDescCargo(cargo.getCargo());
                user1.setStatus(status);
                user1.setStatusUser(status.getStatus());

                Usuario user2 = new Usuario();
                        user2.setNome("Klaiver");
                        user2.setSenha("123");
                        user2.setCargo(cargo);
                        user2.setDescCargo(cargo.getCargo());
                        user2.setStatus(status);
                        user2.setStatusUser(status.getStatus());


                userRepository.save(user1);
                userRepository.save(user2);
            }


            if (prodStatusRepository.count() == 0) {
                StatusProduto statusProdAtivo = new StatusProduto();
                statusProdAtivo.setStatus("ATIVO");

                StatusProduto statusProdInativo = new StatusProduto();
                statusProdInativo.setStatus("INATIVO");

                prodStatusRepository.save(statusProdAtivo);
                prodStatusRepository.save(statusProdInativo);
            }

            if (produtoRepository.count() == 0) {
                StatusProduto statusProduto = prodStatusRepository.findById(1L)
                        .orElseThrow(() -> new StatusNotFound("Nenhum produto com status ativo"));

                Produto produto1 = new Produto();
                produto1.setCodBarra("0000000000001");
                produto1.setDescProduto("Capinha Iphone X");
                produto1.setPrecoCusto(new BigDecimal("5.00"));
                produto1.setPrecoVenda(new BigDecimal("15.00"));
                produto1.setQuantidade(10);


                produto1.setStatusProduto(statusProduto);
                produto1.setStatus(statusProduto.getStatus());

                produtoRepository.save(produto1);
            }

            System.out.println("✅ Todos os dados de teste foram carregados com sucesso!");
        };
    }
}