package unit;

import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.dtos.PriceResponseDto;
import com.activities.pricing.domain.entities.ActivityType;
import com.activities.pricing.domain.exceptions.InvalidPricingRequest;
import com.activities.pricing.domain.repositories.ActivityRepository;
import com.activities.pricing.domain.repositories.DefaultActivityRepository;
import com.activities.pricing.domain.usecases.ActivityPriceCalculator;
import com.activities.pricing.domain.usecases.ActivityPriceCalculatorManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatePriceTest {

    public static final String INVALID_CODE = "00000";
    public static final String FREE_SALE_CODE = "00001";
    public static final String ON_DEMAND_CODE = "00002";

    private static ActivityPriceCalculator activityPriceCalculator;

    @BeforeAll
    static void beforeAll() {
        ActivityRepository activityRepository = new DefaultActivityRepository();
        ActivityPriceCalculatorManager priceCalculatorManager = new ActivityPriceCalculatorManager(activityRepository);
        activityPriceCalculator = new ActivityPriceCalculator(priceCalculatorManager);
    }

    @Test
    public void shouldReturnCorrectPriceForFreeSaleActivityOnValidDay() {

        PriceRequestDto priceRequestDto = new PriceRequestDto(FREE_SALE_CODE, DayOfWeek.MONDAY,
                ActivityType.FREE_SALE, 2, 1);

        PriceResponseDto price = activityPriceCalculator.calculateActivityPrice(priceRequestDto);

        assertEquals(50.0, price.getPrice());
    }

    @Test
    public void shouldReduceFreeSaleActivityPriceForThirdOrMoreChildren() {

        PriceRequestDto priceRequestDto = new PriceRequestDto(FREE_SALE_CODE, DayOfWeek.WEDNESDAY,
                ActivityType.FREE_SALE, 2, 4);

        PriceResponseDto price = activityPriceCalculator.calculateActivityPrice(priceRequestDto);

        assertEquals(70.0, price.getPrice());
    }

    @Test
    public void shouldThrowExceptionForFreeSaleActivityForNonOpenDay() {

        PriceRequestDto priceRequestDto = new PriceRequestDto(FREE_SALE_CODE, DayOfWeek.SUNDAY,
                ActivityType.FREE_SALE, 2, 2);

        var exception = assertThrows(InvalidPricingRequest.class, () ->
                activityPriceCalculator.calculateActivityPrice(priceRequestDto));

        assertEquals("Free sale activity 'Museum' is not available on SUNDAY.", exception.getMessage());
    }

    @Test
    public void shouldReturnCorrectPriceForOnDemandActivityOnValidDay() {

        PriceRequestDto priceRequestDto = new PriceRequestDto(ON_DEMAND_CODE, DayOfWeek.SATURDAY,
                ActivityType.ON_DEMAND, 2, 2);

        PriceResponseDto price = activityPriceCalculator.calculateActivityPrice(priceRequestDto);

        assertEquals(40.0, price.getPrice());
    }

    @Test
    public void shouldThrowExceptionForOnDemandActivityForNonOpenDay() {

        PriceRequestDto priceRequestDto = new PriceRequestDto(ON_DEMAND_CODE, DayOfWeek.MONDAY,
                ActivityType.ON_DEMAND, 2, 2);

        var exception = assertThrows(InvalidPricingRequest.class, () ->
                activityPriceCalculator.calculateActivityPrice(priceRequestDto));

        assertEquals("On demand activity 'Excursion' is not available on MONDAY.", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionForOnDemandActivityWhenLimitExceeded() {

        PriceRequestDto priceRequestDto = new PriceRequestDto(ON_DEMAND_CODE, DayOfWeek.SUNDAY,
                ActivityType.ON_DEMAND, 3, 2);

        var exception = assertThrows(InvalidPricingRequest.class, () ->
                activityPriceCalculator.calculateActivityPrice(priceRequestDto));

        assertEquals(exception.getMessage(), "Places are limit to 4");
    }

    @Test
    public void shouldThrowExceptionForInvalidActivityCode() {

        PriceRequestDto priceRequestDto = new PriceRequestDto(INVALID_CODE, DayOfWeek.MONDAY,
                ActivityType.ON_DEMAND, 2, 2);

        var exception = assertThrows(InvalidPricingRequest.class, () ->
                activityPriceCalculator.calculateActivityPrice(priceRequestDto));

        assertEquals("No activity found for code: 00000", exception.getMessage());
    }
}
