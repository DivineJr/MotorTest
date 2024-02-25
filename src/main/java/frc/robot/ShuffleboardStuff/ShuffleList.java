package frc.robot.ShuffleboardStuff;

/**
 * List handler for the shuffleboard widgets
 * 
 * @author William Hiers
 * @since 2/15/24
 */
public class ShuffleList {
	/**
	 * A linked list that takes in:
	 * - Title of widget
	 * - Data type of widget
	 * - EntryProperties
	 * 
	 * I don't see any reason to support ComplexWidgets,
	 * but maybe I'll find a reason in the future.
	 * 
	 * As of right now, just convert whatever type you get
	 * into a supported primitive type (or String) and use that
	 */

	public static enum DataType {
		String,
		Boolean,
		Double,
		Integer,
		Float;
	} // To be used in a switch that changes the data type expected from a
		// shuffleboard entry

	private ShuffleListNode<?> listHead;

	public ShuffleList() {

	}

	public ShuffleList(ShuffleListNode<?> listHead) {
		this.listHead = listHead;
	}

	public ShuffleList(ShuffleListNode<?>[] nodeArray) {
		ShuffleListNode<?> pointer = null;

		for(int i = 0; i < nodeArray.length; i++) {
			if(i == 0) {
				listHead = nodeArray[i];
				pointer = listHead;
			} else {
				pointer.setNextNode(nodeArray[i]);
				pointer = pointer.getNextNode();
			}
		}
	}

	public void addNode(ShuffleListNode<?> newNode) {
		if(listHead == null) {
			listHead = newNode;
			return;
		}
		
		ShuffleListNode<?> pointer = listHead;

		while(pointer.getNextNode() != null) {
			pointer = pointer.getNextNode();
		}

		pointer.setNextNode(newNode);
	}

	public void addNode(ShuffleListNode<?>[] nodeList) {
		if(listHead == null) {
			listHead = nodeList[0];

			ShuffleListNode<?>[] newNodeList = new ShuffleListNode<?>[nodeList.length - 1];

			for(int i = 1; i < nodeList.length; i++) {
				newNodeList[i-1] = nodeList[i];
			}

			nodeList = newNodeList;
		}
		
		ShuffleListNode<?> pointer = listHead;

		while(pointer.getNextNode() != null) {
			pointer = pointer.getNextNode();
		}

		for(int i = 0; i < nodeList.length; i++) {
			pointer.setNextNode(nodeList[i]);
			pointer = pointer.getNextNode();
		}
	}

	public ShuffleListNode<?> getListHead() {
		return listHead;
	}

	/**
	 * The actual nodes that make up the linked list
	 */
	public static class ShuffleListNode<defaultDataType> {
		private ShuffleListNode nextNode;
		String title;
		DataType dataType;
		EntryProperties properties;
		defaultDataType defaultData;

		public ShuffleListNode(String title, DataType dataType, defaultDataType defaultData, EntryProperties properties) {
			this.title = title;
			this.dataType = dataType;
			this.defaultData = defaultData;
			this.properties = properties;
		}

		public ShuffleListNode(String title, DataType dataType, defaultDataType defaultData, EntryProperties properties, ShuffleListNode nextNode) {
			this.title = title;
			this.dataType = dataType;
			this.defaultData = defaultData;
			this.properties = properties;
			this.nextNode = nextNode;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setDataType(DataType dataType) {
			this.dataType = dataType;
		}

		public void setDefaultData(defaultDataType defaultData) {
			this.defaultData = defaultData;
		}

		public void setEntryProperties(EntryProperties properties) {
			this.properties = properties;
		}

		public void setNextNode(ShuffleListNode nextNode) {
			this.nextNode = nextNode;
		}

		public String getTitle() {
			return title;
		}

		public DataType getDataType() {
			return dataType;
		}

		public defaultDataType getDefaultData() {
			return defaultData;
		}

		public EntryProperties getEntryProperties() {
			return properties;
		}

		public ShuffleListNode getNextNode() {
			return nextNode;
		}
	}
}
