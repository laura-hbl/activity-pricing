package unit;

import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.entities.FreeSaleActivity;
import com.activities.pricing.domain.usecases.CalculateActivityPrice;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatePriceTest {

    CalculateActivityPrice calculateActivityPrice = new CalculateActivityPrice();

    private final Activity freeSaleActivity = new FreeSaleActivity("111", "Museum",
            new HashSet<>(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY)), 20, 10);

    @Test
    public void shouldReturnCorrectPriceOnValidDay() {

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
    public void shouldReturnUndefinedPriceForNonOpenDay() {

        double price = calculateActivityPrice.calculateFreeSaleActivityPrice(freeSaleActivity, 2, 2,
                DayOfWeek.SUNDAY);

        assertEquals(-1, price);
    }
}
