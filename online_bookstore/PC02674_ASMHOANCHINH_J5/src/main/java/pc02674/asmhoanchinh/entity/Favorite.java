package pc02674.asmhoanchinh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="FAVORITE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	
	@ManyToOne @JoinColumn(name = "Userid")
	private NguoiDung nguoiDung;
	
	@ManyToOne @JoinColumn(name = "Sachid")
	private Sach sach;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="Likedate")
	private Date likeDate;

}
