package pc02674.asmhoanchinh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="KHACHHANG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(name = "Fullname")
	@NotBlank(message = "Không để trống tên ")
	private String ten;
	
	@Column(name = "Diachi")
	@NotBlank(message = "Không để trống  địa chỉ")
	private String diaChi;
	
	@NotBlank(message = "Không để trống số điện thoại ")
	private String SDT;
	
	@Column(name = "City")
	@NotBlank(message = "Không để trống tỉnh/thành phố ")
	private String city;
	
	
	@JsonIgnore@ToString.Exclude
	@OneToMany(mappedBy = "khachhang",cascade = CascadeType.ALL, orphanRemoval = true)
	List<DonHang> donHang;
}
