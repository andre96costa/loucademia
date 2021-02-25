package br.com.sofblue.loucademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sofblue.loucademia.application.service.AlunoService;
import br.com.sofblue.loucademia.application.util.StringUtils;
import br.com.sofblue.loucademia.application.util.ValidationException;
import br.com.sofblue.loucademia.domain.acesso.Acesso;

@Named
@RequestScoped
public class RelatorioEntradaSaidaBean implements Serializable {
	
	@EJB
	private AlunoService alunoService;
	@Inject
	private FacesContext facesContext;
	
	private String matricula;
	private LocalDate dataIncial;
	private LocalDate dataFinal;
	private List<Acesso> acessos;
	
	public String gerarRelatorio() {
		
		try {
			acessos = alunoService.listAcessosAlunos(matricula, dataIncial, dataFinal);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public void carregarAluno() {
		if(!StringUtils.isEmpty(matricula)) {
			gerarRelatorio();
		}
	}
	
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public LocalDate getDataIncial() {
		return dataIncial;
	}
	public void setDataIncial(LocalDate dataIncial) {
		this.dataIncial = dataIncial;
	}
	public LocalDate getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	public List<Acesso> getAcessos() {
		return acessos;
	}
	
	
	
	
	
	
}
