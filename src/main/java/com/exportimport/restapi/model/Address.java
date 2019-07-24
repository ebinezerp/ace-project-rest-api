package com.exportimport.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Door No, should not be null")
	@NotBlank(message = "Door No, should not be blank")
	@Column(unique = false, nullable = false)
	private String doorNo;
	
	@NotNull(message = "Street, should not be null")
	@NotBlank(message = "Street, should not be blank")
	@Column(unique = false, nullable = false)
	private String street;
	
	@NotNull(message = "Landmark, should not be null")
	@NotBlank(message = "Landmark, should not be blank")
	@Column(unique = false, nullable = false)
	private String landmark;
	
	@NotNull(message = "City, should not be null")
	@NotBlank(message = "City, should not be blank")
	@Column(unique = false, nullable = false)
	private String city;
	
	@NotBlank(message = "Country should not be blank")
	@NotNull(message = "Country should not be null")
	@Column(nullable = false, unique = false)
	private String country;
	
	@OneToOne
	private ExportAndImportInfo exportAndImportInfo;
	
}
