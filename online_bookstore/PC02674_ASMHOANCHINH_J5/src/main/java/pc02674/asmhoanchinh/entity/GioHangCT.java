package pc02674.asmhoanchinh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GIOHANGCHITIET")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangCT  implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "Giohangid")
	private GioHang gioHang;
		

	@ManyToOne
	@JoinColumn(name = "Sachid")
	private Sach sach;
	
	
	@Column(name = "Soluong")
	@Min(value = 0)
	@NotNull(message = "Không để trống số lượng")
	private int soLuong;

	
	
}
