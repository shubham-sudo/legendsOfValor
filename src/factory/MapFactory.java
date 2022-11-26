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

//    /**
//     * get list of spaces for the whole map
//     * @param inAccessible number of inaccessible
//     * @param market number of market
//     * @param common number of common space for battle
//     * @return List all these random spaces
//     */
//    private List<Space> getSpaceList(int inAccessible, int market, int common){
//        List<Space> spaceList = new ArrayList<>();
//        for (int i = 0; i < inAccessible; i++){
//            spaceList.add(new InaccessibleSpace());
//        }
//        for (int i = 0; i < market; i++){
//            spaceList.add(new MarketSpace());
//        }
//        for (int i = 0; i < common; i++){
//            spaceList.add(new CommonSpace());
//        }
//        return spaceList;
//    }
//
//    /**
//     * place a space on random tiles
//     * @param boardMap map to place a space on random tile
//     * @param spaceList spaces to place
//     */
//    private void addSpaceOnTiles(BoardMap boardMap, List<Space> spaceList){
//        List<Space> localList = new ArrayList<>(spaceList);
//        Collections.shuffle(localList);
//        int boardSize = boardMap.getSize();
//        int totalTiles = boardMap.getTotalTiles();
//
//        for (int row = 0; row < boardSize; row++){
//            for (int col = 0; col < boardSize; col++){
//                if (row == boardSize-1 && col == 0){
//                    boardMap.setTile(row, col, new CommonSpace());
//                    continue;
//                }
//                boardMap.setTile(row, col, localList.get(--totalTiles));
//            }
//        }
//    }

    public BoardMap createMap(){
        BoardMap map = new BoardMap();
        return map;
    }
//
//    /**
//     * Create a new map
//     * @param size give size of map to create
//     * @return Map object
//     */
//    public BoardMap createMap(int size){
//        BoardMap boardMap = new BoardMap(size);
//        int totalTiles = boardMap.getTotalTiles();
//        int numInAccessibleSpaceTiles = (int)(totalTiles * 0.20);
//        int numMarketSpaceTiles = (int)(totalTiles * 0.30);
//        int numCommonSpaceTiles = totalTiles - (numInAccessibleSpaceTiles + numMarketSpaceTiles);
//
//        List<Space> spaceList = getSpaceList(numInAccessibleSpaceTiles, numMarketSpaceTiles, numCommonSpaceTiles);
//        addSpaceOnTiles(boardMap, spaceList);
//
//        return boardMap;
//    }
}
