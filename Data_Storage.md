# Terrain Tile Sets #
  * Each tile set has its own directory (in %exe dir%/data/tilesets) containing any number of tile files and a tileset.xml file
  * Terrain tile images are stored as 100px X 100px JPEG files
  * Tile filenames are of the format tile\_id.jpg  where tile\_id is an integer referenced by tileset.xml

> ### tileset.xml ###
  * This XML file will be output by the Javassonne TileMaker Utility (via XStream<com.thoughtworks.xstream> or dom4j<org.dom4j>)
  * Below is a simplified example
```
     <javassonne_tiles>
        <title>Sample Tile Set</title>
        <version>1.0</version>
        <tilecount>15</tilecount>
        <tiles>
              <tile>
                  <tile_id>1</tile_id>
                  <rotation>0</rotation>
                  <side1_type>a</side1_type>
                  <side2_type>b</side2_type>
                  <side3_type>c</side3_type>
                  <side4_type>d</side4_type>
                  <center_type>e</center_type>
              </tile>
              <tile>
                  <tile_id>2</tile_id>
                  <rotation>0</rotation>
                  <side1_type>e</side1_type>
                  <side2_type>f</side2_type>
                  <side3_type>d</side3_type>
                  <side4_type>a</side4_type>
                  <center_type>a</center_type>
              </tile>
              ......
        </tiles>
     </javassonne_tiles>
```

# Saved Games #
  * Each saved game will have an XML file (in %exe dir%/data/saved\_games) detailing the state of the game
  * Saved game filenames are of the format yyyy-dd-mm\_hh\_mm\_ss.saved.xml
  * Below is a simplified example

```
     <javassonne_game>
        <version>1.0</version>
        <tileset_title>Sample Tile Set</tileset_title>
        <comments>Ben and I started this game after watching 24</comments>
        <board>%XStream Output for org.javassonne.board%</board>
        <players_turn>2</players_turn>
        <players>
              <player>
                 <player_id>1</player_id>
                 <name>Adam</name>
                 <color>FF00EE</color>
              </player>
              <player>
                 <player_id>2</player_id>
                 <name>Ben</name>
                 <color>00FF00</color>
              </player>
              ......
        </players>
     </javassonne_game>
```