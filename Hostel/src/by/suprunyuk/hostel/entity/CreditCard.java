package by.suprunyuk.hostel.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCard extends Entity {
	private String number;
	private CreditCardType type;
	private LocalDate expirationDate;
	private BigDecimal money;
}
