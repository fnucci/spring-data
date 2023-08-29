package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.BusinessUnit;

@Repository
public interface BusinessUnitRepository extends PagingAndSortingRepository<BusinessUnit, Integer>{

}
