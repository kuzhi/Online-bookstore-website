package pc02674.asmhoanchinh.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pc02674.asmhoanchinh.entity.GioHangCT;

public interface GioHangCTDAO extends JpaRepository<GioHangCT, Long>{
	@Query("SELECT o FROM GioHangCT o WHERE o.gioHang.id = ?1 AND o.sach.id LIKE ?2")
	GioHangCT findAllByIDGioHangAndSach(Integer idGioHang, String idSach);
	
	@Query("SELECT o FROM GioHangCT o WHERE o.gioHang.id = ?1 ")
	List<GioHangCT> findAllByIDGioHang(Integer idGioHang );
	
}
