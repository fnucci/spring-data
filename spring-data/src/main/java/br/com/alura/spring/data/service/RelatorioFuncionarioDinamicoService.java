package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.FuncionarioSpecification;

@Service
public class RelatorioFuncionarioDinamicoService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Boolean system = true;

	public void iniciar() {

		while (system) {
			System.out.println("Escoha uma opção:");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Consulta dinamica");

			Scanner scan = new Scanner(System.in);
			int opcao = scan.nextInt();

			switch (opcao) {
			case 0:
				system = false;
				break;
			case 1:
				consultar();
				break;
			default:
				system = false;
				break;

			}
		}
	}

	private void consultar() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o nome");
		String nome = scan.next();

		if (nome.equalsIgnoreCase("NULL"))
			nome = null;

		System.out.println("Digite o cpf");
		String cpf = scan.next();

		if (cpf.equalsIgnoreCase("NULL"))
			cpf = null;

		System.out.println("Digite o salario");
		Double salario = scan.nextDouble();

		if (salario == 0)
			salario = null;

		System.out.println("Digite a data de contratacao");
		String data = scan.next();

		LocalDate dataContratacao;

		if (data.equalsIgnoreCase("NULL"))
			dataContratacao = null;
		else
			dataContratacao = LocalDate.parse(data, format);

		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(FuncionarioSpecification
				.nome(nome).or(FuncionarioSpecification.cpf(cpf)).or(FuncionarioSpecification.salario(salario))
				.or(FuncionarioSpecification.dataContratacao(dataContratacao))));

		funcionarios.forEach(funcionario -> System.out.println(funcionario.toString()));
	}

}
