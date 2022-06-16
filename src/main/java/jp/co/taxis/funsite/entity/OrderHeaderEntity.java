package jp.co.taxis.funsite.entity;

import java.time.LocalDate;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_header")
public class OrderHeaderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private MemberEntity member;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@Column(name = "send_address")
	private String sendAddress;

	@Column(name = "send_date")
	private LocalDate sendDate;

}
