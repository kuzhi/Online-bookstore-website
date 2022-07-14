package pc02674.asmhoanchinh.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pc02674.asmhoanchinh.entity.Favorite;


public interface FavoriteDAO extends JpaRepository<Favorite, Long>{

	@Query("SELECT o FROM Favorite o WHERE o.nguoiDung.id = ?1")
	List<Favorite> findAllByUser(String Keyword);
	
	@Query("SELECT o FROM Favorite o WHERE o.nguoiDung.id = ?1 and o.sach.id LIKE ?2")
	Favorite findAllByUserAndSach(String Keyword, String idSach);
}
