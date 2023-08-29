package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.BusinessUnit;
import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.BusinessUnitRepository;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private BusinessUnitRepository businessUnitRepository;

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void iniciar() {

		while (system) {
			System.out.println("Escoha uma opção:");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar Todos");
			System.out.println("4 - Remover");

			Scanner scan = new Scanner(System.in);
			int opcao = scan.nextInt();

			switch (opcao) {
			case 0:
				system = false;
				break;
			case 1:
				salvar();
				break;
			case 2:
				atualizar();
				break;
			case 3:
				visualizarTodos();
				break;
			case 4:
				remover();
				break;
			default:
				system = false;
				break;

			}
		}
	}

	private void salvar() {
		System.out.println("Digite o nome");
		Scanner scan = new Scanner(System.in);
		String nome = scan.next();

		System.out.println("Digite o cpf");
		String cpf = scan.next();

		System.out.println("Digite o salario");
		Double salario = scan.nextDouble();

		System.out.println("Digite a data de contracao");
		String dataContratacao = scan.next();

		System.out.println("Digite o cargoId");
		Integer cargoId = scan.nextInt();

		List<BusinessUnit> unidades = unidade();

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidades(unidades);

		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}

	private List<BusinessUnit> unidade() {
		Boolean isTrue = true;
		List<BusinessUnit> unidades = new ArrayList<>();

		while (isTrue) {
			System.out.println("Digite o unidadeId (Para sair digite 0)");
			Scanner scan = new Scanner(System.in);
			Integer unidadeId = scan.nextInt();

			if (unidadeId != 0) {
				Optional<BusinessUnit> unidade = businessUnitRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}

		return unidades;
	}

	private void atualizar() {

		Scanner scan = new Scanner(System.in);

		System.out.println("Digite o id");
		Integer id = scan.nextInt();

		System.out.println("Digite o nome");
		String nome = scan.next();

		System.out.println("Digite o cpf");
		String cpf = scan.next();

		System.out.println("Digite o salario");
		Double salario = scan.nextDouble();

		System.out.println("Digite a data de contracao");
		String dataContratacao = scan.next();

		System.out.println("Digite o cargoId");
		Integer cargoId = scan.nextInt();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Alterado");
	}

	private void visualizarTodos() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Pagina?");
		int page = scan.nextInt();

		Pageable pegeable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "nome"));

		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pegeable);
		
		System.out.println(funcionarios);
		System.out.println("Pagina atual " + funcionarios.getNumber());
		System.out.println("Total de elementos: " + funcionarios.getTotalElements());
		
		funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));
	}

	private void remover() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Id");
		int id = scan.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
