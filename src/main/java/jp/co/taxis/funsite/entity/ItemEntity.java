package jp.co.taxis.funsite.entity;

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
@Table(name = "item")
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "item_explain")
	private String itemExplain;

	@Column(name = "price")
	private Integer price;
	
	@Column(name = "image")
	private String image;

	@ManyToOne
	@JoinColumn(name = "game_id", referencedColumnName = "id")
	private GameEntity gameId;

}
