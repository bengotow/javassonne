# Introduction #

Tiles must connect to each other based on what is on each side (for example, fort connects to fort, road to road, nothing to nothing). In addition, the score must be calculated from a contiguous region - whether this be a fort region, a road region, or a farmland region.


# Details #

Our information scheme has been divided into a foreground and background view. The background consists of whatever an empty tile would contain - farmland. The foreground consists of the other features - road, fort, etc.

## Foreground ##

First, we need to store data for each side. For shorthand, we have adopted F<sub>c</sub> for fort cap, F<sub>i</sub> for fort internal, R for road, and N for nothing. In addition, we must store what lies in the center of a tile. An M denotes a monastery in the center, an R denotes a road region continues through a center, and N denoting nothing otherwise.

## Background ##

In addition, we need a way to calculate farm regions. We settled on dividing a tile into quadrants. Quadrants that are part of the same farmland will be issued the same integer. To avoid the coupling of farm logic with foreground features, when a tile is created, any side that blocks a farmland from continuing (i.e. a fort side) will "draw a wall" across that side (stored as a boolean)

## Examples ##

A tabular and image view of some sample tiles appear below. Foreground and background views have been laid on top of one another - 4 sides and middle are foreground; 4 corners and "walls" are background.

<img src='http://javassonne.googlecode.com/svn/trunk/javassonne/tilesets/standard/tile_standard_4.jpg' height='100px' width='100px'>
<table><thead><th> 0 </th><th> F<sub>c</sub> </th><th> 1 </th></thead><tbody>
<tr><td> N </td><td> R </td><td> R </td></tr>
<tr><td> 1 </td><td> R </td><td> 1 </td></tr></tbody></table>

with "wall" on top edge<br>
<br>
<hr />

<img src='http://javassonne.googlecode.com/svn/trunk/javassonne/tilesets/standard/tile_standard_10.jpg' height='100px' width='100px'>
<table><thead><th> 0 </th><th> N </th><th> 0 </th></thead><tbody>
<tr><td> N </td><td> M </td><td> N </td></tr>
<tr><td> 0 </td><td> R </td><td> 0 </td></tr></tbody></table>

with no "walls"<br>
<br>
<hr />

<img src='http://javassonne.googlecode.com/svn/trunk/javassonne/tilesets/standard/tile_standard_19.jpg' height='100px' width='100px'>
<table><thead><th> 0 </th><th> R </th><th> 1 </th></thead><tbody>
<tr><td> R </td><td> N </td><td> R </td></tr>
<tr><td> 2 </td><td> R </td><td> 3 </td></tr></tbody></table>

with no "walls"