package pc02674.asmhoanchinh.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pc02674.asmhoanchinh.entity.GioHang;

public interface GioHangDAO extends JpaRepository<GioHang, String>{
	@Query("SELECT o FROM GioHang o WHERE o.nguoiDung.id LIKE ?1")
	GioHang findByUser( String user);
	
	
}
