package jp.co.taxis.funsite.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "mail_address")
	private String mailAddress;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "birthday")
	private LocalDate birthday;

	@Column(name = "post_number")
	private String postNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "invalid_flg")
	private Boolean invalidFlg;

    @Column(name = "version")
    @Version
    private int version;
}
