# 3-in-a-row with MiniMax
## Purpose
This game is only created to try out the MiniMax algorithm. Basic functionalities like input verification are not suported.

## Rules
- Minimax is the cross and you are the circle
- Minimax always starts
- first person with 3 in a row wins
- The computer exepects coorindates from the grid. First rownumber, then colomnumber. Both values are sepratated with a character in between. This can be any character. For example:
  - 1,1
  - 2.2

## open issues
- The algorithm doesn't take advantage of the symmetry characteristics of the gameboard which causes an overload of computed combinations. Currently, 549946 combinations are calculated. This is not really an issue on a 3x3 grid but can cause some performance problems on larger grids.

## Some variables clarified
### showBuildTree
Class MiniMax contains the boolean value showBuildTree. Once toggled to true, it will show the entire build tree while the program is calculating all possibilities. It looks cool but not pracitcal since this will take forever to build the tree. 

### Utility
The points that MiniMax recieves with the current gameboard that is displayed. During the game this is obviously zero. Only at the end of the game the value changes. <br>
0 = draw<br>
1 = MiniMax won<br>
-1 = MiniMax lost *(not possible if well-implemented)*<br>

### terminaltest
True when the game ends. This is when someone has a 3-in-a-row or the gameboard is full.


  

<br><br>
Please note, I'm a sysadmin and it has been a while since last time I programmed. I probably made some concept-mistakes. But hey, it works ¯\_(ツ)_/¯
