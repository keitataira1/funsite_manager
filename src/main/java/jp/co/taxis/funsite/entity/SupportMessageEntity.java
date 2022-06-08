package jp.co.taxis.funsite.entity;

import java.time.LocalDateTime;

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
@Table(name = "support_message")
public class SupportMessageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="topic_id", referencedColumnName = "id")
	private TopicEntity topic;

	@ManyToOne
	@JoinColumn(name="member_id", referencedColumnName = "id")
	private MemberEntity member;

	@Column(name = "send_datetime")
	private LocalDateTime sendDatetime;

	@Column(name = "message")
	private String message;

}
