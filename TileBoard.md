# Introduction #

Describes methods to be implemented for all Board classes.


# Details #

homeTile() returns an iterator to the homeTile (first tile placed on the board)

addTile(TileBoardIterator iter, Tile tile) adds tile at iter's position

getTile(TileBoardIterator iter) gets the Tile at iter position

getUpperLeftCorner() returns an iterator pointing to an empty position up and left of the upper-left-most filled position in the board

getLowerRightCorner() returns an iterator pointing to an empty position down and right of the lower-right-most filled position in the board