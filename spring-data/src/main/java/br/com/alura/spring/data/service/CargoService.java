package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepoitory;

	private boolean system = true;

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

	public void salvar() {

		System.out.println("Digite a descricao do cargo");

		Scanner scan = new Scanner(System.in);

		Cargo cargo = new Cargo();
		cargo.setDescricao(scan.next());

		cargoRepoitory.save(cargo);
		System.out.println("Cargo salvo com sucesso!");
	}

	public void atualizar() {

		System.out.println("Digite o id para atualizar");

		Scanner scan = new Scanner(System.in);

		Cargo cargo = new Cargo();

		cargo.setId(scan.nextInt());

		System.out.println("Digite a nova descricao");
		cargo.setDescricao(scan.next());

		cargoRepoitory.save(cargo);
		System.out.println("Cargo atualizado com sucesso!");
	}

	public void visualizarTodos() {
		Iterable<Cargo> cargos = cargoRepoitory.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));

	}

	public void remover() {

		System.out.println("Digite o id para remover");

		Scanner scan = new Scanner(System.in);

		Cargo cargo = new Cargo();

		cargo.setId(scan.nextInt());

		cargoRepoitory.delete(cargo);
		System.out.println("Cargo deletado com sucesso!");
	}

}
