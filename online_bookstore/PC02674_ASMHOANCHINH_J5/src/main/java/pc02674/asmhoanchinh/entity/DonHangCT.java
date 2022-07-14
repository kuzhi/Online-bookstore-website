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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DONHANGCHITIET")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonHangCT implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	
	@ManyToOne
	@JoinColumn(name="Donhangid")
	private DonHang donHang;

	@ManyToOne
	@JoinColumn(name="Sachid")
	private Sach sach;
	
	@Column(name = "Soluong")
	@NotNull(message = "Không để trống số lượng")
	@Min(value = 0)
	private int soLuong;
	
	@Column(name = "Tien")
	private float tien;
}
