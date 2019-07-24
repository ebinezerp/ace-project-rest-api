package com.exportimport.restapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportAndImportInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Email should not be null")
	@NotBlank(message = "Email should not be blank")
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Enter valid email")
	@Column(nullable = false, unique = true)
	private String email;
	
	
	@NotBlank(message = "Mobile number should not be blank")
	@NotNull(message = "Mobile number should not be null")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter valid mobile number")
	@Column(nullable = false, unique = true)
	private String mobile;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "exportAndImportInfo")
	private Address address;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;
}
