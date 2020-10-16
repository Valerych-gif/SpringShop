package com.geekbrains.springshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Collection<User> users;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", name='" + name + '\'' +
				", users=" + users +
				'}';
	}
}
