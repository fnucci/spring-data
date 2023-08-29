package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
JpaSpecificationExecutor<Funcionario>{

	List<Funcionario> findByNome(String nome);
	
	List<Funcionario> findByNome(String nome, Pageable pageable);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario "
			+ "AND f.dataContratacao = :contratacao" )
	List<Funcionario> findNomeDataSalFilter(String nome, Double salario, LocalDate contratacao);
	
	@Query(value = "SELECT * from funcionarios"
			+ " WHERE data_contratacao >= :contratacao", nativeQuery = true)
	List<Funcionario> findFuncDataContratacao(LocalDate contratacao);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionariosMaiorSalario();
}
