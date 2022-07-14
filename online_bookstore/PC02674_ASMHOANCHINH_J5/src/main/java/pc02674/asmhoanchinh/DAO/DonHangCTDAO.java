package pc02674.asmhoanchinh.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pc02674.asmhoanchinh.entity.DonHangCT;
import pc02674.asmhoanchinh.entity.GioHangCT;
import pc02674.asmhoanchinh.entity.Report;

public interface DonHangCTDAO extends JpaRepository<DonHangCT, Long>{
	
	@Query("SELECT new Report(d.sach.loaiSach, sum(d.soLuong * d.tien), sum(d.soLuong)) FROM DonHangCT d GROUP BY d.sach.loaiSach")
	Page<Report> revenueByCate(Pageable pageable);
	
	@Query("SELECT o FROM GioHangCT o WHERE o.gioHang.id = ?1 ")
	List<GioHangCT> findAllByIDGioHang(Integer idGioHang );
	
	@Query("SELECT o FROM DonHangCT o WHERE o.donHang.id = ?1 ")
	List<DonHangCT> findAllBydonHang(String keyword);
}
