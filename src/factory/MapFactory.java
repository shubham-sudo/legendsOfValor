package factory;

import map.BoardMap;

/**
 * MapFactory is to create a several type of maps
 * The idea was to use the easy, medium and hard level to generate different type of maps
 */
public class MapFactory {
    private final int easyLevel = 5;
    private final int mediumLevel = 10;
    private final int hardLevel =20;

    /**
     * Create a new map
     * @return return BoardMap
     */
    public BoardMap createMap(){
        BoardMap map = new BoardMap();
        return map;
    }
}
