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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="GALERY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Galery implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@ManyToOne
	@JoinColumn(name = "Sachid")
	private Sach sach;
	
	@Column(name = "Urlimage")
	private String urlImage;
	

}
