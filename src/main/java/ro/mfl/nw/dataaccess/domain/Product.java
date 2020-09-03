package ro.mfl.nw.dataaccess.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Product {
	@Id
	private Integer id;
	
}
