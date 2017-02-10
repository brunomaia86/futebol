package controle;

import java.util.List;

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
		if(this.time.getId() == null)
			repository.adiciona(time);
		else
			repository.atualiza(time);
		this.time = new Time();
		this.times = null;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
		}
}
