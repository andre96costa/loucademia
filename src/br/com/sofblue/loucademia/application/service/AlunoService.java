package br.com.sofblue.loucademia.application.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.sofblue.loucademia.application.util.StringUtils;
import br.com.sofblue.loucademia.application.util.Validation;
import br.com.sofblue.loucademia.application.util.ValidationException;
import br.com.sofblue.loucademia.domain.acesso.Acesso;
import br.com.sofblue.loucademia.domain.aluno.Aluno;
import br.com.sofblue.loucademia.domain.aluno.Aluno.Situacao;
import br.com.sofblue.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {
	
	@EJB
	private AlunoRepository alunoRepository;
	
	public void createOrUpdate(Aluno aluno) {
		if(StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		}else {
			update(aluno);
		}
	}
	
	
	private void create(Aluno aluno) {
		try {
			Validation.assertNotEmpty(aluno);
			String maxMatricula = alunoRepository.gerMaxMatriculaAno();
			aluno.gerarMatricula(maxMatricula);
			alunoRepository.store(aluno);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	private void update(Aluno aluno) {
		try {
			Validation.assertNotEmpty(aluno);
			Validation.assertNotEmpty(aluno.getMatricula());
			
			alunoRepository.upDate(aluno);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
	}
	
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	public List<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone) throws ValidationException{
		if(StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Pelo menos um crit�rio de pesquisa deve ser fornecido");
		}
		return alunoRepository.lisAlunos(matricula, nome, rg, telefone);
	}
	
	public void delete(String matricula) {
		alunoRepository.delete(matricula);
	}
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao) throws ValidationException{
		Validation.assertNotEmpty(situacao);
		return alunoRepository.listSituacoesAlunos(situacao);
	}
	
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal) throws ValidationException{
		if(StringUtils.isEmpty(matricula) && dataInicial == null && dataFinal == null) {
			throw new ValidationException("Pelo menos um crit�rio de pesquisa deve ser fornecido");
		}
		
		return alunoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
	}

}
