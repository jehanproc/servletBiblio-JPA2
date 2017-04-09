package dao;
import entity.Livre;

public interface LivreDao extends Dao<Integer, Livre> {
	public long getCount(Livre livre);
}
