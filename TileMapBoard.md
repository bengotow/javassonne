# Introduction #

TileMapBoard uses the TileBoard interface and stores tile by hashing them into a map based on the Point location of a Tile. (x,y) will be used to denote an Point.


# Details #

## Point ##

(0,0) is the homeTile - the first Tile placed in the board when it is constructed.
(x,y): a negative x is a location to the left of the homeTile. A negative y is a location below the homeTile. Conversely, a positive x is a location to the right of the homeTile and a positive y is a location above the homeTile.

## Iterators ##

homeTile() points to the first tile added to the board. It always returns (0,0).

upperLeft points to an empty location one space left and one space up from the upper-left-most corner determined by connecting the upper-most filled position's row with the left-most filled position's column. So, with just the homeTile at (0,0), uL points to (-1,1).

lowerRight points to an empty location one space right and one space down from the lower-right-most corner determined by connecting the lower-most filled position's row with the right-most filled position's column. So, with just the homeTile at (0,0), lR points to (1,-1).

getters for these iterators return a copy of the iterator since function calls on iterators modify the location stored in the iterator. **Important:** If you need to make a lot of comparisons to the corner iterators, call the getter once and save it locally, then compare with that iterator.