# CSC120-FinalProject
Alternative design

Originally, we planned that there would be a class called Item, which records the name and location (xy axis) of certain items that can be interacted with throughout the game. However, after programming, we realized that the only valuable attribute for class Item is its name, and could be replaced by simply comparing the name in the string with input. This method can also make the program simpler since the printing of room descriptions can be combined: we planned to print the room description first and the item in the room second. But some of the items are in a container, which is not technically in the room. So this requires another comparison statement, which makes the codes more complicated, so we decided to abandon the Item class
