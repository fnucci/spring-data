package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	private boolean system = true;

	public void iniciar() {

		while (system) {
			System.out.println("Escoha uma opção:");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Buscar Funcionario por nome");
			System.out.println("2 - Buscar Funcionario Filtro");
			System.out.println("3 - Buscar Funcionario Data Contratacao");
			System.out.println("4 - Buscar Funcionario Salario");
//			System.out.println("4 - Remover");

			Scanner scan = new Scanner(System.in);
			int opcao = scan.nextInt();

			switch (opcao) {
			case 0:
				system = false;
				break;
			case 1:
				buscarFuncionarioPorNome();
				break;
			case 2:
				buscarFuncionarioFiltros();
				break;
			case 3:
				buscarFuncionarioDataContratacao();
				break;
			case 4:
				buscarFuncionarioSalario();
				break;
//			case 4:
//				remover();
//				break;
			default:
				system = false;
				break;

			}
		}
	}

	private void buscarFuncionarioSalario() {
		// TODO Auto-generated method stub
		List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionariosMaiorSalario();
		lista.forEach(f -> System.out.println("Id:" + f.getId() + ", Nome: " + f.getNome() + ", Salario: " + f.getSalario()));
	}

	private void buscarFuncionarioDataContratacao() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Digite a data de contratacao do funcionario");

		Scanner scan = new Scanner(System.in);
		String dataString = scan.next();

		LocalDate data = LocalDate.parse(dataString, format);

		List<Funcionario> funcionarios = funcionarioRepository.findFuncDataContratacao(data);

		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.getNome());
		}

	}

	private void buscarFuncionarioFiltros() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Digite o nome do funcionario");

		Scanner scan = new Scanner(System.in);
		String nome = scan.next();

		System.out.println("Digite a data de contratacao");
		String dataString = scan.next();

		LocalDate data = LocalDate.parse(dataString, format);

		System.out.println("Digite o valor do salario");
		Double salario = scan.nextDouble();

		List<Funcionario> funcionarios = funcionarioRepository.findNomeDataSalFilter(nome, salario, data);

		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.getNome());
		}

	}

	public void buscarFuncionarioPorNome() {

		System.out.println("Digite o nome do funcionario");

		Scanner scan = new Scanner(System.in);
		String nome = scan.nextLine();

		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);

		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.getNome());
		}

	}
}
