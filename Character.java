import java.util.ArrayList;
/**
 * Represents a character
 * @author Ashley Qian
 */
public class Character {
    ArrayList <String> currentItem;
    Boolean item1;
    Boolean item2;
    Boolean item3;
    int x;
    int y;
    Map map;

    /**
     * Constructor
     */
    public Character(){
        this.currentItem = new ArrayList<String>();
        this.item1 = false;
        this.item2 = false;
        this.item3 = false;
        this.x = 0;
        this.y = 0;
        this.map = new Map();
    }

    /**
     * Allows the user to open the drawer/refrigerator when the character is in the correct room
     * Displays what's in the drawer/refrigerator.
     * @param item name of the container
     */
    public void open(String container){
        if(container.equals("drawer")&& this.x==0&&this.y==1){
            this.map.drawer.print();
            this.map.drawer.open = true;
        }else if(container.equals("refrigerator")&& this.x==1&&this.y==1){
            this.map.refrigerator.print();
            this.map.refrigerator.open=true;
        }else{
            System.out.println("-- The wrong action, I'll say. ");
        }
    }

    /**
     * Allows the user to take the item when the character is in the correct room 
     * Will add the string into the currentItem array for further comparison
     * @param item name of the item
     */
    public void take(String item){
        if(this.map.drawer.open&&item.equals("match")&& this.x==0&&this.y==1){
            currentItem.add(item);
            this.map.drawer.open = true;
        }else if(this.map.refrigerator.open&&item.equals("cheese")&& this.x==1&&this.y==1){
            currentItem.add(item);
            this.map.refrigerator.open = true;
        }else if(item.equals("cup")&& this.x==1 &&this.y==0){
            currentItem.add(item);
            this.map.right.change("-- There is nothing on the table now. ");
        }else{
            System.out.println("-- I guess it's not possible. ");
        }
    }

    /**
     * Allows the user to walk around the map
     * Will output "bumping wall" when the user is trying to walk beyond the map
     * @param direction north/south/west/east
     */
    public void walk(String direction){
        if (direction.equals("north")){
            if(this.y < 1){
                this.y = this.y +1;
            }else{
                System.out.println("-- You bumped the wall");
            }
        }else if(direction.equals("south")) {
            if(this.y > -1){
                this.y = this.y -1;
            }else{
                System.out.println("-- You bumped the wall");
            }

        }else if(direction.equals("west")) {
            if(this.x > -1){
                this.x = this.x -1;
            }else{
                System.out.println("-- You bumped the wall");
            }
        }else if(direction.equals("east")) {
            if(this.x < 1){
                this.x = this.x +1;
            }else{
                System.out.println("-- You bumped the wall");
            }
        }else{
            System.out.println("!- Invalid input! Please try again.");
        }

        this.map.enter(this.x, this.y);

    }

    /**
     * Allows the user to use the item that is available in the current item list
     * Advances the "plot"
     * If the correct action is input, part of the password will be shown to the player.
     * Items can only be used once.
     * @param item name of the item
     */
    public void use(String item){
        if (this.currentItem.contains(item)){
            if (item.equals("cheese") && this.x == 1 && this.y == -1){
                System.out.println("-- The little mouse threw out a paper ball with something written on it.");
                this.map.bottomRight.change("-- The little mouse looks full. ");
                this.item1 = true;
                this.currentItem.remove("cheese");
            }else if(item.equals("cup") && this.x == -1 && this.y == 0){
                System.out.println("-- The ice melted. There is a wet paper ball left in the cup with something written on it. ");
                this.item2 = true;
                this.currentItem.remove("cup");
            }else if(item.equals("match") && this.x == -1 && this.y == -1){
                System.out.println("-- Now the torch is lit. It may be helpful, so you take one of the torches. ");
                this.currentItem.add("torch");
                this.currentItem.remove("match");
            }else if(item.equals("torch") && this.x == 0 && this.y == -1){
                System.out.println("-- Now you can see what's under the couch. There is a paper ball with something written on it.");
                this.item3 = true;
                this.currentItem.remove("torch");
            }else{
                System.out.println("-- Right action but at the wrong place.");
            }
        }else if (item.equals("paper ball")){
            System.out.println("-- Nah. The paper ball is not important. What is important is the thing written on it. ");
        }else{
            System.out.println("-- You don't have this item");
        }
    }

    /**
     * Allows the read the paper ball that has part of the password on it
     * Only available when the clues are solved
     * @param item paper ball
     */
    public void read(String item){
        if (item.equals("paper ball")){
            if (this.item1 && this.x == 1 && this.y == -1){
                System.out.println("First digit: 7");
            }else if(this.item2 && this.x == -1 && this.y == 0){
                System.out.println("Second digit: 9");
            }else if(this.item3 && this.x == 0 && this.y == -1){
                System.out.println("Third digit: 3");
            }else{
                System.out.println("There is no such thing in this section of the room.");
            }
        }else{
            System.out.println("-- You know that it is not something the read.");
        }
    }

    /**
     * Outputs possible commands that can be used in the game
     */
    public void help(){
        System.out.println("Possible commands:\n-- walk()\n-- take()\n-- use()\n-- read()");
    }

    
    public static void main(String[] args) {
        //Character character = new Character();
    }
}
