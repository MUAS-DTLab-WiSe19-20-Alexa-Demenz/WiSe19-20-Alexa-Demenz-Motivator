package motivator.model;

/**
 * Class that defines one hobby with name (identifier), place (indoor / outdoor)
 * and needed time.
 *
 * @author Clara
 *
 */

public class Hobby {
    // name of the hobby
    private String hobbyName;
    // whether the hobby is to be done inside or outside
    private boolean hobbyInside;
    // time needed for hobby
    private int hobbyMinutes;

    /**
     * Constructor that sets the place to inside and the needed time to 30 min as a default.
     */
    public Hobby() {
        setInside(true);
        setMinutes(30);
    }

    /**
     * Constructor that configures the hobby according to the input.
     * @param input inputString
     */
    public Hobby(String input) {
        String[] parts = input.split("; ");
        for (String data : parts) {
            if (data.startsWith("name: ")) {
                setName(data.substring(6));
            }
            if (data.startsWith("inside: ")) {
                setInside(Boolean.valueOf(data.substring(8)));
            }
            if (data.startsWith("minutes: ")) {
                setMinutes(Integer.parseInt(data.substring(9)));
            }
        }
    }

    /**
     *
     * @return name hobby name
     */
    public String getName() {
        return hobbyName;
    }

    /**
     * Returns the place of the hobby.
     *
     * @return inside 1 = inside, 0 = outside
     */
    public boolean getInside() {
        return hobbyInside;
    }

    /**
     * Returns the time needed for the hobby.
     *
     * @return minutes time needed
     */
    public int getMinutes() {
        return hobbyMinutes;
    }

    /**
     * Changes or sets the name of the Hobby.
     *
     * @param name name of hobby
     */
    public void setName(String name) {
        hobbyName = name;
    }

    /**
     * Changes or sets the place of the hobby.
     *
     * @param inside 0 = outside, 1 = inside
     */
    public void setInside(boolean inside) {
        hobbyInside = inside;
    }

    /**
     * Changes or sets the time needed for the hobby.
     *
     * @param minutes time needed
     */
    public void setMinutes(int minutes) {
        hobbyMinutes = minutes;
    }

    /**
     * Returns a String representing this hobby.
     * @return this hobby
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name: " + getName() + "; ");
        sb.append("inside: " + getInside() + "; ");
        sb.append("minutes: " + getMinutes());

        return sb.toString();
    }

}
