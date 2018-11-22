package lt.bit.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import lt.bit.auth.JwtTokenGenerator;
import lt.bit.product.events.ProductImportEvent;


@Component
public class ProductImporter {

	private static final Logger LOG = LoggerFactory.getLogger(ProductImporter.class);

	private final RestTemplate restTemplate;
	private final ProductJpaRepository repository;
	private final JwtTokenGenerator tokenGenerator;
	private final ApplicationEventPublisher eventPublisher;

	ProductImporter(RestTemplate restTemplate, ProductJpaRepository repository, JwtTokenGenerator tokenGenerator, ApplicationEventPublisher eventPublisher) {
		this.restTemplate = restTemplate;
		this.repository = repository;
		this.tokenGenerator = tokenGenerator;
		this.eventPublisher = eventPublisher;
	}

	@Scheduled(fixedRate = 10000l)
	public void importProducts() {
		LOG.info("Job called");
		ParameterizedTypeReference<List<Product>> typeRef = new ParameterizedTypeReference<List<Product>>() {
		};
		HttpHeaders heads = new HttpHeaders();
		heads.add("Authorization", tokenGenerator.generateAccessToken());
		ResponseEntity<List<Product>> result = restTemplate.exchange("http://localhost:8082/products", HttpMethod.GET,
				new HttpEntity(heads), typeRef);
		List<Product> imported = result.getBody();
		repository.saveAll(imported);
	
		BigDecimal sum = imported
				.stream()
				.map( p -> BigDecimal.valueOf( p.getPrice() ) )
				.reduce( BigDecimal.valueOf(0),  (a, b) -> a.add(b));
			eventPublisher.publishEvent(new ProductImportEvent(imported.size(), LocalDateTime.now(), sum));
		LOG.info("products {} were saved to DB", result.getBody());
	}
}
