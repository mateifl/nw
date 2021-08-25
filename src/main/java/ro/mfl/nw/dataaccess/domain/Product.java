package ro.mfl.nw.dataaccess.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("Products")
public class Product {
	@Id
	@Column("productid")
	private Integer productId;
	@Column("productname")
	private String productName;
	@Column("unitsinstock")
	private Integer unitsInStock;
	@Column("categoryid")
	private Integer categoryId;
	private Integer unitsOnOrder;

	private Category category;

}
