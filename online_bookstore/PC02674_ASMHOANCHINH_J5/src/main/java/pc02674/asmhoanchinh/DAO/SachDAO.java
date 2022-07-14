package pc02674.asmhoanchinh.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pc02674.asmhoanchinh.entity.Sach;

public interface SachDAO extends JpaRepository<Sach, String>{
	@Query("SELECT o FROM Sach o WHERE o.loaiSach.id = ?1")
	Page<Sach> findAllByLoai(Integer Keyword, Pageable pageable);
	
	@Query("SELECT o FROM Sach o WHERE o.tenSach LIKE ?1")
	Page<Sach> findAllByName(String nameBook, Pageable pageable1);
	
	Page<Sach> findByOrderByTenSachAsc(Pageable pageable);
	
	Page<Sach> findByOrderByTenSachDesc(Pageable pageable);
	@Query("SELECT o FROM Sach o WHERE o.tenSach LIKE ?1")
	Page<Sach> findAllByZA(String nameBook, Pageable pageable1);
	
}
