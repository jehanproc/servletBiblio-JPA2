package dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.LivreDao;
import entity.Livre;

public class LivreDaoMemory implements LivreDao {
	
	static Map<Integer, Livre> livres = new HashMap<>();
	
	public LivreDaoMemory() {
		Livre livre   = new Livre("Stupeur et tremblements",1999, "Amélie Nothomb");
		livre = save(livre);
		System.out.println(livre.getId());
		save(new Livre("L'étranger",1942, "Albert Camus"));
		save(new Livre("Réglez-lui son compte !",1949, "Frédéric Dard"));
		save(new Livre("Tintin au Tibet",1960, "Hergé"));
	}

	@Override
	public Livre save(Livre entity) {
		int id = SequenceCounter.getId();
		entity.setId(id);
		livres.put(id, entity);
		return entity;
	}

	@Override
	public void update(Livre entity) {
		livres.put(entity.getId(), entity);
	}

	@Override
	public Livre findOne(Integer primaryKey) {
		// TODO Auto-generated method stub
		return livres.get(primaryKey);
	}

	@Override
	public List<Livre> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Livre>(livres.values());
	}

	@Override
	public void delete(Livre entity) {
		livres.remove(entity.getId());

	}

	@Override
	public void delete(Integer id) {
		livres.remove(id);
	}

	@Override
	public void delete(Iterable<Livre> entities) {
		for(Livre l : entities)
			delete(l);

	}

	@Override
	public long getCount(Livre livre) {
		// TODO Auto-generated method stub
		int count = 0;
		for(Livre l : livres.values())
			if((l.getAuteur().equals(livre.getAuteur())) && (l.getTitre().equals(livre.getTitre())))
					count++;
		return count;
	}

}
