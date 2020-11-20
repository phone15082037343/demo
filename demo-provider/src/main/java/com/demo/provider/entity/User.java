package com.demo.provider.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_info")
public class User {

	@Id
	@Column(name = "user_id", length = 32)
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@GeneratedValue(generator = "uuidGenerator")
	private String userId;

	@Column(name = "name", length = 64)
	private String name;

	@Column(name = "sex")
	private int sex;

	@Column(name = "age")
	private int age;

	@Column(name = "birthday")
	private Date birthday;

}
