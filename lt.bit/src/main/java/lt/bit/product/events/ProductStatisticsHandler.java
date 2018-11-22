package lt.bit.product.events;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@Validated
public class ProductStatisticsHandler {

	private StatisticsJpaRepository repo;

	public ProductStatisticsHandler(StatisticsJpaRepository repo) {
		this.repo = repo;
	}

	@EventListener
	@Async
	public void handleImportedProducts(@NotNull @Valid ProductImportEvent event) {
		repo.save(ProductStatisticsEntity.from(event));
	}
	
	

}
