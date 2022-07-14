package pc02674.asmhoanchinh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="SACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sach implements Serializable{

	@Id
	private String id;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Loaisachid")
	private LoaiSach loaiSach;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Nxbid")
	private NhaXuatBan nxb;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Nccid")
	private NhaCungCap ncc;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Tacgiaid")
	private TacGia tacGia;
	
	@Column(name = "Tensach")
	@NotBlank(message = "Không để trống tên sách")
	private String tenSach;
	
	@Column(name = "Soluong")
	@NotNull(message = "Không để trống số lượng")
	@Min(value = 0)
	private int soLuong;
	
	@Column(name = "Gia")
	@NotNull(message = "Không để trống giá tiền")
	@Min(value = 0, message = "không để giá dưới 0")
	private float gia;
	
	@Column(name="Images", nullable = false )
	private String images;
	
	@Column(name = "Discount")
	@Min(value = 0, message = "không để Discount dưới 0")
	
	private float discount;
	

	@Column(name="Mota")
	@NotBlank(message = "Vui lòng ghi mô tả sản phẩm")
	private String mota;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="Ngaytao")
	@NotNull(message = "Không để trống ngày tạo")
	private Date ngayTao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="Ngaysua", nullable = false)
	private Date ngaySua;
	
	
	@Column(name="Dvt")
	private String dvt;
	
	@Column(name="Daxoa")
	private boolean daXoa;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sach")
	List<Galery> galery;
	
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sach")
	List<DonHangCT> donHangCT;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sach")
	List<Favorite> favorite;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sach")
	List<GioHangCT> gioHangCT;
}
