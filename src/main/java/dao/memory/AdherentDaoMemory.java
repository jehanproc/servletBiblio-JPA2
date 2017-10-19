package dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AdherentDao;
import entity.Adherent;

public class AdherentDaoMemory implements AdherentDao {
	
	static Map<Integer, Adherent> adherents = new HashMap<>();
	
	public AdherentDaoMemory() {
		Adherent ad1 = new Adherent("Dupond", "Jean", "0234567812", "jean.dupont.@yahoo.fr");
		Adherent ad2 = new Adherent("Durant", "Jacques", "0223674512", "jacques.durant@free.fr");
		Adherent ad3 = new Adherent("Martin", "Bernadette", "0138792012", "m.bernadette@gmail.com");
		save(ad1);
		save(ad2);
		save(ad3);
	}

	@Override
	public Adherent save(Adherent entity) {
		int id = SequenceCounter.getId();
		entity.setId(id);
		adherents.put(id, entity);
		return entity;
	}

	@Override
	public void update(Adherent entity) {
		adherents.put(entity.getId(), entity);
	}

	@Override
	public Adherent findOne(Integer primaryKey) {
		// TODO Auto-generated method stub
		return adherents.get(primaryKey);
	}

	@Override
	public List<Adherent> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Adherent>(adherents.values());
	}

	@Override
	public void delete(Adherent entity) {
		adherents.remove(entity.getId());

	}

	@Override
	public void delete(Integer id) {
		adherents.remove(id);
	}

	@Override
	public void delete(Iterable<Adherent> entities) {
		for(Adherent l : entities)
			delete(l);

	}


	@Override
	public boolean isPresent(Adherent adherent) {
		for(Adherent l : adherents.values())
			if(l.equals(adherent))
				return true;
		return false;
	}

}
