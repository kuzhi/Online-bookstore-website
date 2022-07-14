package pc02674.asmhoanchinh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "GIOHANG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHang  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Userid")
	private NguoiDung nguoiDung;
	
	@JsonIgnore@ToString.Exclude
	@OneToMany(mappedBy = "gioHang")
	List<GioHangCT> gioHangCT;
}
