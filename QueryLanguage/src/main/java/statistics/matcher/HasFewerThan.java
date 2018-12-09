
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {

    private int value;
    private String fieldName;

    public HasFewerThan(int value, String category) {
        this.value = value;
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length()); //metodin nimen rakentaminen, tyyliin getGoals
    }

    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod(fieldName); //etsitään luokalta player metodi, jonka nimi on äsken rakennettu, esim getGoals
            int playersValue = (Integer)method.invoke(p); //käytä metodia, ns aja se, mutta reflektion kautta ? ja pakotetaan integeriksi
            return playersValue<value;

        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }

    }

}
