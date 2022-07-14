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
@Table(name="LOAISACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiSach implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(name = "Tenloai")
	@NotBlank(message = "Không để trống tên loại")
	@Size(min=3, max=50, message = "Tên loại sách phải lớn hơn 3 và nhỏ hơn 50")
	private String tenLoai;
	
	
	@JsonIgnore@ToString.Exclude
	@OneToMany(mappedBy = "loaiSach")
	List<Sach> sach;
}
