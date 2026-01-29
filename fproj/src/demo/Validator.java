package demo;
import java.lang.reflect.Field;

public class Validator {
    public static void validate(Object obj) throws IllegalAccessException {
        // Get all fields of the class
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            // Check if the field has our @MaxLength annotation
            if (field.isAnnotationPresent(MaxLength.class)) {
                field.setAccessible(true); // Ensure we can read private fields

                String value = (String) field.get(obj);
                int limit = field.getAnnotation(MaxLength.class).value();

                if (value != null && value.length() > limit) {
                    throw new RuntimeException(
                        "Field " + field.getName() + " exceeds limit of " + limit
                    );
                }
            }
        }
    }
}