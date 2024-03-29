package com.app.minhaescolaapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.minhaescolaapp.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	@Query("select p from Professor p where p.nome like %?1%")
	List<Professor> buscarProfessorPorNome(String nome);

	default Page<Professor> findProfessoresByNamePage(String nome, Pageable pageable) {

		Professor professor = new Professor();
		professor.setNome(nome);;

		// Estamos configurando a pesquisa para consultar por partes do nome no banco de
		// dados, igual ao Like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		// Une o objeto com o valor e a configuração para consultar
		Example<Professor> example = Example.of(professor, exampleMatcher);

		Page<Professor> professores = findAll(example, pageable);

		return professores;
	}

}
