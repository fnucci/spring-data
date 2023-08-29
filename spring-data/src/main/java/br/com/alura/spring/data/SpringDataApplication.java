package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.BusinessUnitService;
import br.com.alura.spring.data.service.CargoService;
import br.com.alura.spring.data.service.FuncionarioService;
import br.com.alura.spring.data.service.RelatorioFuncionarioDinamicoService;
import br.com.alura.spring.data.service.RelatorioService;
import br.com.alura.spring.data.specification.FuncionarioSpecification;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private CargoService cargoService;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private BusinessUnitService unidadeService;

	@Autowired
	private RelatorioService relatorioService;

	@Autowired
	private RelatorioFuncionarioDinamicoService dinamicoService;

	private boolean system = true;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		while (system) {
			System.out.println("Escoha uma opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - BusinessUnit");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorios dinâmicos");

			Scanner scan = new Scanner(System.in);
			int opcao = scan.nextInt();

			switch (opcao) {
			case 0:
				system = false;
				break;
			case 1:
				cargoService.iniciar();
				break;
			case 2:
				funcionarioService.iniciar();
				break;
			case 3:
				unidadeService.iniciar();
				break;
			case 4:
				relatorioService.iniciar();
				break;
			case 5:
				dinamicoService.iniciar();
			default:
				system = false;
				break;

			}
		}
		System.out.println("Obrigado por utilizar nossa aplicacao.");
		System.exit(0);

	}
}
