package map.space;


/**
 * Plane space which is a normal space
 */
public class PlainSpace extends NormalSpace{
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";  // TODO: fetch this from config files

    /**
     * background color for this space
     * @return string
     */
    @Override
    public String bgColor() {
        return ANSI_BLUE_BACKGROUND;
    }
}
