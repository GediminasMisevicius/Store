package lt.bit.product.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statistics")
public class ProductStatisticsEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="number")
	private int count;
	private LocalDateTime timestamp;
	private BigDecimal sum;
	
	public static ProductStatisticsEntity from(ProductImportEvent event) {
		ProductStatisticsEntity entity = new ProductStatisticsEntity();
		entity.setCount(event.getCount());
		entity.setSum(event.getSum());
		entity.setTimestamp(event.getTimestamp());
		return entity;
	}
	
	public Integer getId() {
		return id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
	
	
}
