package pc02674.asmhoanchinh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Donhang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonHang implements Serializable{
	
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="Khachhangid")
	private KhachHang khachhang;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date ngay;
	
	@ManyToOne
	@JoinColumn(name="Userid")
	private NguoiDung nguoiDung;
	
	@Column(name="Trangthai")
	@NotNull(message = "Vui lòng chọn trạng thái đơn hàng")
	private boolean trangThai;
	
	@Column(name = "Tongtien")
	private float tongTien;
	
	@Column(name = "Daxoa")
	private boolean daXoa;
	
	@JsonIgnore
	@OneToMany(mappedBy = "donHang")
	List<DonHangCT> donHangCT;
}
