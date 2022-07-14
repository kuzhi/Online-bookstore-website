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
@Table(name="TACGIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacGia implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(name = "Ten")
	@NotBlank(message = "Không để trống tên tác giả")
	private String ten;
	
	@JsonIgnore@ToString.Exclude
	@OneToMany(mappedBy = "tacGia")
	List<Sach> sach;
}
