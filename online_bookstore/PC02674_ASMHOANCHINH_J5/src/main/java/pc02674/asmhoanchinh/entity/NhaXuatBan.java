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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="NHAXUATBAN")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class NhaXuatBan  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(name = "Tennxb")
	@NotBlank(message = "Không để trống tên nhà xuất bản")
	private String tenNXB;
	
	
	@JsonIgnore@ToString.Exclude
	@OneToMany(mappedBy = "nxb")
	List<Sach> sach;
}
