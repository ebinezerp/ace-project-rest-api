package com.exportimport.restapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer  {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Username should not be blank")
	@NotNull(message = "Username should not be null")
	@Pattern(regexp = "^[a-z0-9]{4,15}$", message = "Min 4 and Max 15 characters are allowed, only small alphanumeric characters are allowed")
	@Column(unique = true, nullable = false)
	private String username;
	
	
	@NotNull(message = "Email should not be null")
	@NotBlank(message = "Email should not be blank")
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Enter valid email")
	@Column(unique = true, nullable = false)
	private String email;
	
	
	@NotBlank(message = "Mobile number should not be blank")
	@NotNull(message = "Mobile number should not be null")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter valid mobile number")
	@Column(unique = true, nullable = false)
	private String mobile;
	
	
	
	@NotBlank(message = "Password should not be blank")
	@NotNull(message = "Password should not be null")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{6,}$", message = "Password must contain one capital, one small alphabet, one special and one numeric character of min 6 characters")
	private String password;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Authorities> authorities;
	
	@NotNull(message = "Should not be null")
	private Boolean enabled;
	

}
