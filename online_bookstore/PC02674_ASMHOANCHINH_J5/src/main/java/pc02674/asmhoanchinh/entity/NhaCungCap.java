package pc02674.asmhoanchinh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="NHACUNGCAP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCap implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(name = "Tenncc")
	@NotBlank(message = "Không để trống tên nhà cung cấp")
	private String ten;
	
	@Column(name = "Diachi")
	@NotBlank(message = "Không để trống  địa chỉ")
	private String diaChi;
	
	@NotBlank(message = "Không để trống số điện thoại ")
	@Size(min=10,max=11, message = "Số điện thoại trong khoảng 10 đến 11 số")
	private String SDT;
	
	@JsonIgnore@ToString.Exclude
	@OneToMany(mappedBy = "ncc")
	List<Sach> sach;
}
