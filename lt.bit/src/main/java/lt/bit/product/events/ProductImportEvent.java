package lt.bit.product.events;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lt.bit.validation.NotNegative;

public class ProductImportEvent implements Serializable{

	private int count;
	private LocalDateTime timestamp;
	@NotNegative(message="{NOT_NEGATIVE_SHORT}")
	private BigDecimal sum;
	
	public ProductImportEvent(int count, LocalDateTime timestamp, BigDecimal sum) {
		this.count = count;
		this.timestamp = timestamp;
		this.sum = sum;
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
