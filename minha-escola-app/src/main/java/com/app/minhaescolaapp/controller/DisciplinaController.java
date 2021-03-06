package com.app.minhaescolaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Disciplina;
import com.app.minhaescolaapp.repository.DisciplinaRepository;
import com.app.minhaescolaapp.repository.EscolaRepository;

@Controller
public class DisciplinaController {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	@GetMapping("/disciplina/disciplina")
	public ModelAndView cadastrarDisciplina(Disciplina disciplina) {
		
		ModelAndView modelAndView = new ModelAndView("disciplina/disciplina");
		modelAndView.addObject("objdisciplina", new Disciplina());
		modelAndView.addObject("listaalunos", escolaRepository.findAll());
		modelAndView.addObject("listadisciplina", disciplinaRepository.findAll());
		return modelAndView;
	}
}
