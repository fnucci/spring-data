package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.BusinessUnit;
import br.com.alura.spring.data.repository.BusinessUnitRepository;

@Service
public class BusinessUnitService {
	
	@Autowired
	private BusinessUnitRepository businessUnitRepository;

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

		System.out.println("Digite a descricao da unidade");

		Scanner scan = new Scanner(System.in);

		BusinessUnit unidade = new BusinessUnit();
		unidade.setDescricao(scan.next());
		
		System.out.println("Digite o endereco da unidade");
		unidade.setEndereco(scan.next());

		
		businessUnitRepository.save(unidade);
		System.out.println("Unidade salva com sucesso!");
	}

	public void atualizar() {

		System.out.println("Digite o id para atualizar");

		Scanner scan = new Scanner(System.in);

		BusinessUnit unidade = new BusinessUnit();

		unidade.setId(scan.nextInt());

		System.out.println("Digite a nova descricao");
		unidade.setDescricao(scan.next());
		
		System.out.println("Digite o endereco da unidade");
		unidade.setEndereco(scan.next());

		businessUnitRepository.save(unidade);
		System.out.println("Unidade atualizada com sucesso!");
	}

	public void visualizarTodos() {
		Iterable<BusinessUnit> unidades = businessUnitRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));

	}

	public void remover() {

		System.out.println("Digite o id para remover");

		Scanner scan = new Scanner(System.in);

		BusinessUnit unidade = new BusinessUnit();

		unidade.setId(scan.nextInt());

		businessUnitRepository.delete(unidade);
		System.out.println("Unidade deletada com sucesso!");
	}

	
	
}
