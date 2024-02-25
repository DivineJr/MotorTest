package frc.robot.ShuffleboardStuff;

import java.util.ArrayList;

import frc.robot.ShuffleboardStuff.ShuffleList.ShuffleListNode;

/**
 * Handles initialization of shuffleboard
 * 
 * @author William Hiers
 * @since 2/15/24
 */
public class ShuffleboardHandler {
    /*
     * TODO:
     * Take in a list of planned components
     *      - Linked List of planned components (ShuffleList)
     *          - Title of Component
     *          - Data type of Component
     *          - Widget Type (object type it will show up as)
     * Check for existance of component on initialization
     *      - If it doesn't exist, create it!
     *      - Make a new list with all the GenericEntry's of the widgets (EntryList)
     * Then, use this class in order to interact with Shuffleboard
     *      - Search the list for an object of a specified title and return the GenericEntry with that title
     *      - Return null if object not found
     * 
     * What's the point of this class? To eliminate errors caused by existing widgets in the Shuffleboard on creation
     * 
     */

    private ShuffleList shuffleList;
    private ArrayList<String> titleList = new ArrayList<String>();

    public ShuffleboardHandler(ShuffleList shuffleList) {
        this.shuffleList = shuffleList;
        constructorLogic();
    }

    public ShuffleboardHandler(ShuffleListNode[] nodeList) {
        shuffleList = new ShuffleList(nodeList);
        constructorLogic();
    }

    private void constructorLogic() {
        ShuffleListNode<?> test = shuffleList.getListHead();

        while(test != null) {
            titleList.add(test.getTitle());
            test = test.getNextNode();
        }

        for(int i = 0 ; i < titleList.size(); i++) {
            System.out.print(titleList.get(i) + " | ");
        }
        System.out.print("\n");
    }


    
    
}
