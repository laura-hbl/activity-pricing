package unit;

import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.entities.FreeSaleActivity;
import com.activities.pricing.domain.entities.OnDemandActivity;
import com.activities.pricing.domain.usecases.CalculateActivityPrice;
import com.activities.pricing.domain.exception.InvalidPricingRequest;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatePriceTest {

    CalculateActivityPrice calculateActivityPrice = new CalculateActivityPrice();

    private final Activity freeSaleActivity = new FreeSaleActivity("111", "Museum",
            new HashSet<>(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY)), 20, 10);

    private final Activity onDemandActivity = new OnDemandActivity("222", "Excursion",
            new HashSet<>(List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)),
            10, 10);


    @Test
    public void shouldReturnCorrectPriceForFreeSaleActivityOnValidDay() {

        double price = calculateActivityPrice.calculateFreeSaleActivityPrice(freeSaleActivity, 2, 1,
                DayOfWeek.MONDAY);

        assertEquals(50.0, price);
    }

    @Test
    public void shouldReducePriceForThirdOrMoreChildren() {

        double price = calculateActivityPrice.calculateFreeSaleActivityPrice(freeSaleActivity, 2, 4,
                DayOfWeek.WEDNESDAY);

        assertEquals(70.0, price);
    }

    @Test
    public void shouldThrowExceptionForFreeSaleActivityForNonOpenDay() {

        var exception = assertThrows(InvalidPricingRequest.class, () ->
                calculateActivityPrice.calculateFreeSaleActivityPrice(freeSaleActivity, 2, 2, DayOfWeek.MONDAY));

        assertEquals(exception.getMessage(), "Activity not available on MONDAY");
    }

    @Test
    public void shouldReturnCorrectPriceForOnDemandActivityOnValidDay() {

        double price = calculateActivityPrice.calculateOnDemandActivityPrice(onDemandActivity, 2, 2,
                DayOfWeek.SATURDAY);

        assertEquals(40.0, price);
    }

    @Test
    public void shouldThrowExceptionForOnDemandActivityForNonOpenDay() {

        var exception = assertThrows(InvalidPricingRequest.class, () ->
                calculateActivityPrice.calculateOnDemandActivityPrice(onDemandActivity, 2, 2, DayOfWeek.MONDAY));

        assertEquals(exception.getMessage(), "Activity not available on MONDAY");
    }

    @Test
    public void shouldThrowExceptionForOnDemandActivityWhenLimitExceeded() {

        var exception = assertThrows(InvalidPricingRequest.class, () ->
                calculateActivityPrice.calculateOnDemandActivityPrice(onDemandActivity, 3, 2, DayOfWeek.SUNDAY));

        assertEquals(exception.getMessage(), "Places are limit to 4");
    }

}
