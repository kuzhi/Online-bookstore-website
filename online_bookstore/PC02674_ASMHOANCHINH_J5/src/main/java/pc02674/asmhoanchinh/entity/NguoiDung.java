package pc02674.asmhoanchinh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NGUOIDUNG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8283902241931165245L;

	@Id
	@NotBlank(message = "Không để trống username")
	@Size(min = 5, max = 10, message = "Username phải lớn hơn 5 và nhỏ hơn 10")
	private String id;

	@Column(name = "Password")
	@NotBlank(message = "Không để trống mật khẩu")
	@Length(min = 5, message = "Mật khẩu phải lớn hơn 5 và nhỏ hơn 10")

	private String password;

	@NotBlank(message = "Không để trống họ")
	@Size(min = 3, max = 10, message = "Họ phải lớn hơn 3 và nhỏ hơn 10")
	private String ho;

	@NotBlank(message = "Không để trống tên")
	@Size(min = 2, max = 50, message = "Tên phải lớn hơn 2 và nhỏ hơn 50")
	private String ten;

	@NotBlank(message = "Không để trống email")
	@Email(message = "Không đúng định dạng email")
	@Size(min = 10, max = 50, message = "email trong khoảng 10 đến 50 từ")
	private String email;

	@NotNull(message = "Vui lòng chọn giới tính")
	private Boolean gioitinh;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "Ngaysinh")
	private Date ngaySinh;

	@Column(name = "Chucvu")
	@NotNull(message = "Vui lòng chọn chức vụ")
	private Integer chucVu;

	@NotBlank(message = "Không để trống CMND")
	@Size(min = 9, max = 15)
	private String CMND;

	@NotBlank(message = "Không để trống số điện thoại")
	@Size(min = 10, max = 11, message = "Số điện thoại trong khoảng 10 đến 11 số")
	private String SDT;

	@Column(name = "Diachi")
	@NotBlank(message = "Không để trống địa chỉ")
	private String diaChi;

	@Column(name = "Trangthai")
	private Boolean trangThai;

	@Column(name = "Daxoa")
	private Boolean daXoa;

	@JsonIgnore
	@OneToMany(mappedBy = "nguoiDung")
	List<Favorite> favorite;

	@JsonIgnore
	@OneToMany(mappedBy = "nguoiDung")
	List<DonHang> donHang;

	@JsonIgnore
	@OneToMany(mappedBy = "nguoiDung")
	List<GioHang> gioHang;
}
