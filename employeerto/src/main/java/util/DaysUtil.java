package util;

import com.ing.employeerto.employeerto.enums.Days;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DaysUtil {
    public static boolean isValidDay(String inputDay) {
        try {
            Days.valueOf(inputDay.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
