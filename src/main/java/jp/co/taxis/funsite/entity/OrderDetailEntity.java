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
@Table(name = "order_detail")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private MemberEntity member;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private ItemEntity item;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private OrderHeaderEntity order;

	@Column(name = "quantity")
	private Integer quantity;

}
