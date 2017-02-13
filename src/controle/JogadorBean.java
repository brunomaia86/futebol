package controle;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import modelo.Jogador;
import modelo.Time;
import repositorios.JogadorRepository;
import repositorios.TimeRepository;

@ManagedBean
public class JogadorBean {

	private Jogador jogador;
	private List<Jogador> listaDeJogadores;

	private Long timeID;

	public void adiciona() {
		EntityManager manager = this.getManager();
		JogadorRepository jogadorRepository = new JogadorRepository(manager);
		TimeRepository timeRepository = new TimeRepository(manager);

		if (timeID != null) {
			Time time = timeRepository.procura(timeID);
			this.jogador.setTime(time);
		}

		if (this.jogador.getId() == null)
			jogadorRepository.adiciona(jogador);
		else
			jogadorRepository.atualiza(jogador);

		this.jogador = new Jogador();
		this.listaDeJogadores = null;
	}

	public void preparaAlteracao() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));

		EntityManager manager = this.getManager();
		JogadorRepository repository = new JogadorRepository(manager);

		this.jogador = repository.procura(id);
	}

	public void remove() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));

		EntityManager manager = this.getManager();
		JogadorRepository repository = new JogadorRepository(manager);
		repository.remove(id);
		this.listaDeJogadores = null;

	}

	public List<Jogador> getJogadores() {
		if (listaDeJogadores == null) {
			EntityManager manager = this.getManager();
			JogadorRepository repository = new JogadorRepository(manager);
			this.listaDeJogadores = repository.getLista();
		}

		return this.listaDeJogadores;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public List<Jogador> getListaDeJogadores() {
		return listaDeJogadores;
	}

	public void setListaDeJogadores(List<Jogador> listaDeJogadores) {
		this.listaDeJogadores = listaDeJogadores;
	}

	public Long getTimeID() {
		return timeID;
	}

	public void setTimeID(Long timeID) {
		this.timeID = timeID;
	}

}
