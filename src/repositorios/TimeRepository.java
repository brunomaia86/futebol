package repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Jogador;
import modelo.Time;

public class TimeRepository {

private EntityManager manager;
	
	public TimeRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Time Time){
		this.manager.persist(Time);
	}
	
	public Time procura(Long id) {
		return this.manager.find(Time.class, id);
	}
	
	public Time atualiza(Time time) {
		return this.manager.merge(time);
	}
	
	public void remove(Long id) {
		Time time = this.procura(id);
		Query query = this.manager.createQuery("select x from jogador x where x.time = :time");
		query.setParameter("time", time);
		
		List<Jogador> jogadores = query.getResultList();
		for (Jogador jogador : jogadores) {
			jogador.setTime(null);
		}
		
		this.manager.remove(time);
	}
	
	public List<Time> getList() {
		Query query = this.manager.createQuery("select x from Time x");
		return query.getResultList();
	}
	
}
