package controle;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import modelo.Time;
import repositorios.TimeRepository;

@ManagedBean
public class TimeBean {

	private Time time = new Time();

	private List<Time> times;

	public void adiciona() {
		EntityManager manager = this.getManager();
		TimeRepository repository = new TimeRepository(manager);
		if (this.time.getId() == null)
			repository.adiciona(time);
		else
			repository.atualiza(time);
		this.time = new Time();
		this.times = null;
	}
	
	public void adicionaTeste(Time time) {
		EntityManager manager = this.getManager();
		TimeRepository repository = new TimeRepository(manager);
		if (this.time.getId() == null)
			repository.adiciona(time);
		else
			repository.atualiza(time);
		this.time = new Time();
		this.times = null;
	}

	public void preparaAlteracao() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manager = this.getManager();
		TimeRepository repository = new TimeRepository(manager);
		this.time = repository.procura(id);
	}

	public void remove() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap();
		Long id = Long.parseLong(params.get("id"));
		EntityManager manger = this.getManager();
		TimeRepository repository = new TimeRepository(manger);
		repository.remove(id);
	}

	public List<Time> getTimes() {
		if (this.times == null) {
			EntityManager manager = this.getManager();
			TimeRepository repository = new TimeRepository(manager);
			this.times = repository.getList();
		}
		return times;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}
}
