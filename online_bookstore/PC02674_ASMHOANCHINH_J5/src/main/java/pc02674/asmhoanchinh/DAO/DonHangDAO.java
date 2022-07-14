package pc02674.asmhoanchinh.DAO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pc02674.asmhoanchinh.entity.DonHang;

public interface DonHangDAO extends JpaRepository<DonHang, String> {
	@Query(value="SELECT count(*) FROM DonHang o WHERE o.trangThai = ?1", nativeQuery = true)
	Long countDonHang( double user);
	
	@Query("SELECT o FROM DonHang o WHERE o.nguoiDung.id = ?1")
	Page<DonHang> findAllByIdUser(String idUser, Pageable pageable);
}
