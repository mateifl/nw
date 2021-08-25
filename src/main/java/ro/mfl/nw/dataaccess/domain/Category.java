package ro.mfl.nw.dataaccess.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@NoArgsConstructor
@Table("Categories")
public class Category {
	@Id
	@Column("categoryid")
	private Integer categoryId;
	@Column("categoryname")
	private String categoryName;
	private String description;
	private String picture;
}
